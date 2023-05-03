/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hospitalapi.servletController.paciente;

import com.hospitalapi.model.Usuario;
import com.hospitalapi.objects.JsonConverter;
import com.hospitalapi.objects.LectorJson;
import com.hospitalapi.service.paciente.ServiceReporPacienteConsultas;
import com.hospitalapi.service.paciente.ServiceReportPacienteExamenes;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luis
 */
@WebServlet(name = "ServletControllerReportPaciente", urlPatterns = {"/ServletControllerReportPaciente"})
public class ServletControllerReportPaciente extends HttpServlet {

    private JsonConverter converter;
    private LectorJson lector;
    private ServiceReporPacienteConsultas serviceReporPacienteConsultas;
    private ServiceReportPacienteExamenes serviceReportPacienteExamenes;

    public ServletControllerReportPaciente() {
        this.converter = new JsonConverter();
        this.lector = new LectorJson();
        this.serviceReporPacienteConsultas = new ServiceReporPacienteConsultas();
        this.serviceReportPacienteExamenes = new ServiceReportPacienteExamenes();
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
        String tarea = request.getParameter("tarea");
        switch (tarea) {
            case "consultas":
                sendListConsultas(request, response);
                break;
            case "consultasFechas":
                sendListConsultasFechas(request, response);
                break;
            case "consultasEspecialidad":
                sendListConsultasByEspecialidad(request, response);
                break;
            case "examenes":
                sendListExamenes(request, response);
                break;
            case "examenesFechas":
                sendListExamenesBetweenDate(request, response);
                break;
            case "examenesTipoExamen":
                sendListExamenesByTipoExamen(request, response);
                break;
            default:
        }
    }

    private void sendListConsultas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario user = (Usuario) this.converter.fromJson(this.lector.read(request.getReader()), Usuario.class);
        response.getWriter().write(this.converter.toJson(this.serviceReporPacienteConsultas.getList(user)));
    }

    private void sendListConsultasFechas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario user = (Usuario) this.converter.fromJson(this.lector.read(request.getReader()), Usuario.class);
        String fecha1 = request.getParameter("fecha1");
        String fecha2 = request.getParameter("fecha2");
        response.getWriter().write(this.converter.toJson(this.serviceReporPacienteConsultas.getListBetweenFecha(user, fecha1, fecha2)));
    }

    private void sendListConsultasByEspecialidad(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario user = (Usuario) this.converter.fromJson(this.lector.read(request.getReader()), Usuario.class);
        String especialidad = request.getParameter("especialidad");
        response.getWriter().write(this.converter.toJson(this.serviceReporPacienteConsultas.getListByEspecialidad(user, especialidad)));
    }

    private void sendListExamenes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario user = (Usuario) this.converter.fromJson(this.lector.read(request.getReader()), Usuario.class);
        response.getWriter().write(this.converter.toJson(this.serviceReportPacienteExamenes.getList(user)));
    }

    private void sendListExamenesBetweenDate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario user = (Usuario) this.converter.fromJson(this.lector.read(request.getReader()), Usuario.class);
        String fecha1 = request.getParameter("fecha1");
        String fecha2 = request.getParameter("fecha2");
        response.getWriter().write(this.converter.toJson(this.serviceReportPacienteExamenes.getListBetweenFecha(user, fecha1, fecha2)));
    }

    private void sendListExamenesByTipoExamen(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario user = (Usuario) this.converter.fromJson(this.lector.read(request.getReader()), Usuario.class);
        String examen = request.getParameter("examen");
        response.getWriter().write(this.converter.toJson(this.serviceReportPacienteExamenes.getListByExamen(user, examen)));
    }

}
