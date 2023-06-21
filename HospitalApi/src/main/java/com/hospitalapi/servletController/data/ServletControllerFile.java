/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hospitalapi.servletController.data;

import com.hospitalapi.model.ExamenConsulta;
import com.hospitalapi.model.ExamenSolicitado;
import com.hospitalapi.model.ResultadoConsulta;
import com.hospitalapi.model.ResultadoLaboratorio;
import com.hospitalapi.service.laboratorio.ServiceResultadosLab;
import com.hospitalapi.service.medico.ServiceConsultas;
import com.hospitalapi.service.medico.ServiceResultadosConsulta;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author luis
 */
@WebServlet(name = "ServletControllerFile", urlPatterns = {"/ServletControllerFile"})
@MultipartConfig
public class ServletControllerFile extends HttpServlet {

    private ServiceResultadosLab serviceResultadosLab;
    private ServiceConsultas serviceConsultas;
    private ServiceResultadosConsulta serviceResultadosConsulta;
    private File home;

    public ServletControllerFile() {
        this.serviceResultadosLab = new ServiceResultadosLab();
        this.serviceConsultas = new ServiceConsultas();
        this.serviceResultadosConsulta = new ServiceResultadosConsulta();
        this.home = FileSystemView.getFileSystemView().getHomeDirectory();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=resultados.pdf");
        String accion = request.getParameter("accion");
        switch (accion) {
            case "download":
                downloadPDF(request, response);
                break;
            default:
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        response.setContentType("application/json");
        //response.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");
        switch (accion) {
            case "1":
                guardarResultadoLaboratorio(request, response);
                break;
            case "2":
                guardarResultadosExamenesConsulta(request, response);
                break;
            default:
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        String accion = request.getParameter("accion");
        switch (accion) {
            case "":
                break;
            default:
        }

    }

    private void guardarResultadoLaboratorio(HttpServletRequest request, HttpServletResponse response) {
        try {
            Part pdfPart = request.getPart("pdf");
            String examen = request.getParameter("examen");
            int idExamen = Integer.parseInt(request.getParameter("idExamen"));
            int solicitud = Integer.parseInt(request.getParameter("solicitud"));
            String username = request.getParameter("username");

            String nombreArchivo = idExamen + " " + examen + " " + solicitud + " " + username + " ";

            // Guardar el archivo PDF en la carpeta
            //String folder = "/tmp/laboratorio/";
            String folder = this.home + File.separator + "laboratorio" + File.separator;
            Path fileName = Paths.get(pdfPart.getSubmittedFileName()).getFileName();

            String filePath = folder + nombreArchivo + fileName.toString();

            ResultadoLaboratorio resultado = new ResultadoLaboratorio(0, solicitud, idExamen, nombreArchivo + fileName.toString(), "");
            System.out.println("ruta " + filePath);
            ResultadoLaboratorio buscado = this.serviceResultadosLab.getResultadoLaboratorioDB().get(solicitud, idExamen);
            if (buscado == null) {
                this.serviceResultadosLab.insert(resultado);
                ExamenSolicitado solicitado = new ExamenSolicitado(idExamen, solicitud, 0, true);
                this.serviceResultadosLab.updateExamenSolicitado(solicitado);
            } else {
                buscado.setNombreArchivo(resultado.getNombreArchivo());
                this.serviceResultadosLab.getResultadoLaboratorioDB().update(buscado);
            }

            File pdfFile = new File(filePath);
            FileOutputStream outputStream = new FileOutputStream(pdfFile, false);
            pdfPart.write(filePath);

        } catch (IOException | ServletException ex) {
            System.out.println("fallo");
            Logger.getLogger(ServletControllerFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void guardarResultadosExamenesConsulta(HttpServletRequest request, HttpServletResponse response) {
        try {
            Part pdfPart = request.getPart("pdf");
            String examen = request.getParameter("examen");
            int idExamen = Integer.parseInt(request.getParameter("idExamen"));
            int consulta = Integer.parseInt(request.getParameter("solicitud"));
            String username = request.getParameter("username");

            String nombreArchivo = idExamen + " " + examen + " " + consulta + " " + username;

            // Guardar el archivo PDF en la carpeta
            //String folder = "/tmp/paciente/";
            String folder = this.home + File.separator + "paciente" + File.separator;
            Path fileName = Paths.get(pdfPart.getSubmittedFileName()).getFileName();

            String filePath = folder + nombreArchivo + fileName.toString();

            ResultadoConsulta resultado = new ResultadoConsulta(0, consulta, idExamen, nombreArchivo + fileName.toString(), examen);
            ResultadoConsulta buscado = this.serviceResultadosConsulta.get(idExamen, consulta);
            if (buscado == null) {
                this.serviceResultadosConsulta.insert(resultado);
                ExamenConsulta examenConsulta = new ExamenConsulta(idExamen, consulta, true, "");
                this.serviceConsultas.updateExamen(examenConsulta);
            } else {
                buscado.setNombreArchivo(resultado.getNombreArchivo());
                this.serviceResultadosConsulta.update(buscado);
            }

            File pdfFile = new File(filePath);
            FileOutputStream outputStream = new FileOutputStream(pdfFile, false);
            pdfPart.write(filePath);

        } catch (IOException | ServletException ex) {
            Logger.getLogger(ServletControllerFile.class.getName()).log(Level.SEVERE, null, ex);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void downloadPDF(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
        String folder = request.getParameter("folderName");
        String path = request.getParameter("path");
        String pathFile = "";
        switch (folder) {
            case "lab":
                //pathFile = "/tmp/laboratorio/" + path;
                pathFile = this.home + File.separator + "laboratorio" + File.separator + path;
                download(response, pathFile);
                break;
            case "paciente":
                //pathFile = "/tmp/paciente/" + path;
                pathFile = this.home + File.separator + "paciente" + File.separator + path;
                download(response, pathFile);
                break;
            default:
                throw new AssertionError();
        }
    }

    private void download(HttpServletResponse response, String pathFile) throws FileNotFoundException, IOException {
        try (BufferedInputStream fileStream = new BufferedInputStream(new FileInputStream(pathFile))) {
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "attachment; filename=resultados.pdf");
            int data = fileStream.read();
            while (data > -1) {
                response.getOutputStream().write(data);
                data = fileStream.read();
            }
        }
    }

}
