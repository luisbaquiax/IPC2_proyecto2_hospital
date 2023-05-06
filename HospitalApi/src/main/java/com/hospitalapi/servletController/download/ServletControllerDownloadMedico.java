/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hospitalapi.servletController.download;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hospitalapi.model.reports.MedicoReportEspecialidadesIngresos;
import com.hospitalapi.model.reports.MedicoReportPacientesIngresos;
import com.hospitalapi.objects.LectorJson;
import com.hospitalapi.service.reports.ServiceDownloadReportsMedico;
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
@WebServlet(name = "ServletControllerDownloadMedico", urlPatterns = {"/ServletControllerDownloadMedico"})
public class ServletControllerDownloadMedico extends HttpServlet {

    private ServiceDownloadReportsMedico service;
    private LectorJson lector;

    public ServletControllerDownloadMedico() {
        this.service = new ServiceDownloadReportsMedico();
        this.lector = new LectorJson();
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "attachment; filename=reporte.pdf");
            
            String accion = request.getParameter("accion");
            switch (accion) {
                case "1":
                    downloadTopPacientes(request, response);
                    break;
                case "2":
                    downloadTopEspecialidades(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
        } catch (JRException ex) {
            System.out.println("fallos uno de las descargas");
            Logger.getLogger(ServletControllerDownloadMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void downloadTopPacientes(HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {
        String json = this.lector.read(request.getReader());

        Type listType = new TypeToken<ArrayList<MedicoReportPacientesIngresos>>() {
        }.getType();
        List<MedicoReportPacientesIngresos> list = new Gson().fromJson(json, listType);

        System.out.println(Arrays.toString(list.toArray()));

        this.service.reportTopPacientes(response.getOutputStream(), list);

    }

    private void downloadTopEspecialidades(HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {
        String json = this.lector.read(request.getReader());

        Type listType = new TypeToken<ArrayList<MedicoReportEspecialidadesIngresos>>() {
        }.getType();
        List<MedicoReportEspecialidadesIngresos> list = new Gson().fromJson(json, listType);

        System.out.println(Arrays.toString(list.toArray()));

        this.service.reportTopEspecialidades(response.getOutputStream(), list);
    }

}
