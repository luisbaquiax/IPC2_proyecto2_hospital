/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hospitalapi.servletController.download;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hospitalapi.model.Consulta;
import com.hospitalapi.model.SolicitudExamen;
import com.hospitalapi.model.reports.ReportLabTopExamenes;
import com.hospitalapi.objects.LectorJson;
import com.hospitalapi.service.reports.ServiceDownloadReportsAdmin;
import com.hospitalapi.service.reports.ServiceDownloadReportsMedico;
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
@WebServlet(name = "ControlladorDowload", urlPatterns = {"/ControlladorDowload"})
public class ControlladorDowloadAdmin extends HttpServlet {

    private ServiceDownloadReportsAdmin serviceReportsAdminDownload;
    private LectorJson lector;

    public ControlladorDowloadAdmin() {
        this.serviceReportsAdminDownload = new ServiceDownloadReportsAdmin();
        this.lector = new LectorJson();
    }

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
        System.out.println("hola");
        try {

            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "attachment; filename=reporte.pdf");

            String accion = request.getParameter("accion");
            switch (accion) {
                case "1":
                    this.serviceReportsAdminDownload.reportTopMedicos(response.getOutputStream());
                    break;
                case "2":
                    this.serviceReportsAdminDownload.reportTopLaboratorio(response.getOutputStream());
                    break;
                case "3":
                    this.serviceReportsAdminDownload.reportHistorialPorcentaje(response.getOutputStream());
                    break;
                default:
            }
        } catch (JRException ex) {
            System.out.println("valio");
            Logger.getLogger(ControlladorDowloadAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "attachment; filename=reporte.pdf");

            String accion = request.getParameter("accion");
            switch (accion) {
                case "1":
                    downReportConsultas(request, response);
                    break;
                case "2":
                    dowReportExamenes(request, response);
                    break;
                default:
            }
        } catch (JRException ex) {
            Logger.getLogger(ControlladorDowloadAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void downReportConsultas(HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {
        String json = this.lector.read(request.getReader());

        Type listType = new TypeToken<ArrayList<Consulta>>() {
        }.getType();
        List<Consulta> list = new Gson().fromJson(json, listType);

        System.out.println(Arrays.toString(list.toArray()));

        this.serviceReportsAdminDownload.reportConsultas(response.getOutputStream(), list);
    }

    private void dowReportExamenes(HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {

        String json = this.lector.read(request.getReader());

        Type listType = new TypeToken<ArrayList<SolicitudExamen>>() {
        }.getType();
        List<SolicitudExamen> list = new Gson().fromJson(json, listType);

        System.out.println(Arrays.toString(list.toArray()));

        this.serviceReportsAdminDownload.reportExamenes(response.getOutputStream(), list);
    }

}
