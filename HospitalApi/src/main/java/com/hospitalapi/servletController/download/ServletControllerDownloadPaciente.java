/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hospitalapi.servletController.download;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hospitalapi.data.modelDB.RecargaDB;
import com.hospitalapi.model.Recarga;
import com.hospitalapi.model.reports.HistorialMedico;
import com.hospitalapi.model.reports.ReportPacienteConsultas;
import com.hospitalapi.model.reports.ReportPacienteExamenes;
import com.hospitalapi.objects.LectorJson;
import com.hospitalapi.service.reports.ServiceDownloadReportsPaciente;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author luis
 */
@WebServlet(name = "ServletControllerDownloadPaciente", urlPatterns = {"/ServletControllerDownloadPaciente"})
public class ServletControllerDownloadPaciente extends HttpServlet {

    private LectorJson lector;
    private ServiceDownloadReportsPaciente serviceReport;
    private RecargaDB recargaDB;

    public ServletControllerDownloadPaciente() {
        this.serviceReport = new ServiceDownloadReportsPaciente();
        this.lector = new LectorJson();
        this.recargaDB = new RecargaDB();
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
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "attachment; filename=reporte.pdf");

            String accion = request.getParameter("accion");
            switch (accion) {
                case "1":
                    downloadHistorialMedico(request, response);
                    break;
                case "2":
                    downloadRecargas(request, response);
                    break;
                case "3":
                    downloadConsultas(request, response);
                    break;
                case "4":
                    downloadExamenes(request, response);
                    break;
                default:
            }
        } catch (JRException ex) {
            System.out.println("fallo la descarga");
            Logger.getLogger(ServletControllerDownloadPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void downloadHistorialMedico(HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {
        String json = this.lector.read(request.getReader());

        Type listType = new TypeToken<ArrayList<HistorialMedico>>() {
        }.getType();
        List<HistorialMedico> list = new Gson().fromJson(json, listType);

        System.out.println(Arrays.toString(list.toArray()));

        this.serviceReport.reportHistorialMedico(response.getOutputStream(), list);
    }

    private void downloadRecargas(HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {
        int paciente = Integer.parseInt(request.getParameter("id"));

        this.serviceReport.reportHistorialRecargas(response.getOutputStream(), this.recargaDB.getRecargas(paciente));
    }

    private void downloadConsultas(HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {
        String json = this.lector.read(request.getReader());

        Type listType = new TypeToken<ArrayList<ReportPacienteConsultas>>() {
        }.getType();
        List<ReportPacienteConsultas> list = new Gson().fromJson(json, listType);

        System.out.println(Arrays.toString(list.toArray()));

        this.serviceReport.reportConsultas(response.getOutputStream(), list);
    }

    private void downloadExamenes(HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {
        String json = this.lector.read(request.getReader());

        Type listType = new TypeToken<ArrayList<ReportPacienteExamenes>>() {
        }.getType();
        List<ReportPacienteExamenes> list = new Gson().fromJson(json, listType);

        System.out.println(Arrays.toString(list.toArray()));

        this.serviceReport.reportExamenes(response.getOutputStream(), list);
    }

}
