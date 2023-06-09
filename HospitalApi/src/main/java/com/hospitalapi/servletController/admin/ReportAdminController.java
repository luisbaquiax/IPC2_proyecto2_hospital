/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hospitalapi.servletController.admin;

import com.hospitalapi.objects.JsonConverter;
import com.hospitalapi.service.admin.AdminReportIngresosConsultaService;
import com.hospitalapi.service.admin.HistorialPorcentajeService;
import com.hospitalapi.service.admin.ReportExamenesIngresosService;
import com.hospitalapi.service.admin.TopLaboratorioService;
import com.hospitalapi.service.admin.TopMedicosService;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luis
 */
@WebServlet(name = "ReportAdminController", urlPatterns = {"/ReportAdminController"})
public class ReportAdminController extends HttpServlet {

    private HistorialPorcentajeService historialPorcentajeService;
    private AdminReportIngresosConsultaService adminReportIngresosConsultaService;
    private ReportExamenesIngresosService reportExamenesIngresosService;
    private TopLaboratorioService laboratorioService;
    private TopMedicosService medicosService;
    private JsonConverter converter;

    public ReportAdminController() {
        this.historialPorcentajeService = new HistorialPorcentajeService();
        this.adminReportIngresosConsultaService = new AdminReportIngresosConsultaService();
        this.reportExamenesIngresosService = new ReportExamenesIngresosService();
        this.converter = new JsonConverter();
        this.laboratorioService = new TopLaboratorioService();
        this.medicosService = new TopMedicosService();
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
        String tarea = request.getParameter("tarea");
        response.setContentType("application/json");
        switch (tarea) {
            case "historialPorcentajes":
                verHistorialPorcentajes(request, response);
                break;
            case "consultas":
                verconsultas(response);
                break;
            case "consultasFecha":
                verconsultasFecha(request, response);
                break;
            case "examenes":
                verExamenes(request, response);
                break;
            case "examanesFecha":
                verExamenesFecha(request, response);
                break;
            case "topMedicos":
                verTopMedicos(request, response);
                break;
            case "topLaboratorios":
                verTopLaboratorios(request, response);
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
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
    }

    private void verHistorialPorcentajes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = this.converter.toJson(this.historialPorcentajeService.getAll());
        System.out.println(json);
        response.getWriter().append(this.converter.toJson(this.historialPorcentajeService.getAll()));
    }

    private void verconsultas(HttpServletResponse response) throws IOException {
        String json = this.converter.toJson(this.adminReportIngresosConsultaService.getAll());
        System.out.println("consultas: " + json);
        response.getWriter().append(json);
    }

    private void verconsultasFecha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fecha1 = request.getParameter("fecha1");
        String fecha2 = request.getParameter("fecha2");
        String json = this.converter.toJson(this.adminReportIngresosConsultaService.getAllIntoTimeInterval(fecha1, fecha2));
        System.out.println("consultas fechas: " + json);
        response.getWriter().append(json);
    }

    private void verExamenes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().append(this.converter.toJson(this.reportExamenesIngresosService.getAll()));
    }

    private void verExamenesFecha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fecha1 = request.getParameter("fecha1");
        String fecha2 = request.getParameter("fecha2");
        response.getWriter().append(this.converter.toJson(this.reportExamenesIngresosService.getAllTimeInterval(fecha1, fecha2)));
    }

    private void verTopMedicos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(Arrays.toString(this.medicosService.getList().toArray()));
        response.getWriter().write(this.converter.toJson(this.medicosService.getList()));
    }

    private void verTopLaboratorios(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(Arrays.toString(this.laboratorioService.getList().toArray()));
        response.getWriter().write(this.converter.toJson(this.laboratorioService.getList()));
    }
}
