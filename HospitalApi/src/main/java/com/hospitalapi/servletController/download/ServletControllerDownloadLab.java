/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hospitalapi.servletController.download;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hospitalapi.model.reports.HistorialMedico;
import com.hospitalapi.model.reports.ReportLabTopExamenes;
import com.hospitalapi.model.reports.ReportLabTopPacientes;
import com.hospitalapi.objects.LectorJson;
import com.hospitalapi.service.reports.ServiceDownloadReportsLaboratory;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ServletControllerDownloadLab", urlPatterns = {"/ServletControllerDownloadLab"})
public class ServletControllerDownloadLab extends HttpServlet {

    private LectorJson lector;
    private ServiceDownloadReportsLaboratory serviceDownload;

    public ServletControllerDownloadLab() {
        this.lector = new LectorJson();
        this.serviceDownload = new ServiceDownloadReportsLaboratory();
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
                    downloadTopPacientes(request, response);
                    break;
                case "2":
                    downloadTopExamenes(request, response);
                    break;
                default:
            }
        } catch (JRException ex) {
            System.out.println("falló la descarga");
            Logger.getLogger(ServletControllerDownloadLab.class.getName()).log(Level.SEVERE, null, ex);
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

        Type listType = new TypeToken<ArrayList<ReportLabTopPacientes>>() {
        }.getType();
        List<ReportLabTopPacientes> list = new Gson().fromJson(json, listType);

        System.out.println(Arrays.toString(list.toArray()));

        this.serviceDownload.reportTopPacientes(response.getOutputStream(), list);
    }

    private void downloadTopExamenes(HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {

        String json = this.lector.read(request.getReader());

        Type listType = new TypeToken<ArrayList<ReportLabTopExamenes>>() {
        }.getType();
        List<ReportLabTopExamenes> list = new Gson().fromJson(json, listType);

        System.out.println(Arrays.toString(list.toArray()));

        this.serviceDownload.reportTopExamenes(response.getOutputStream(), list);
    }

}
