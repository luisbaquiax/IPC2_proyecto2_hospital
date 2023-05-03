/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hospitalapi.servletController.medico;

import com.hospitalapi.model.Usuario;
import com.hospitalapi.objects.JsonConverter;
import com.hospitalapi.objects.LectorJson;
import com.hospitalapi.service.medico.ServiceReportMedico;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luis
 */
@WebServlet(name = "ServletControllerReportMedico", urlPatterns = {"/ServletControllerReportMedico"})
public class ServletControllerReportMedico extends HttpServlet {

    private ServiceReportMedico serviceReportMedico;
    private JsonConverter converter;
    private LectorJson lector;

    public ServletControllerReportMedico() {
        this.serviceReportMedico = new ServiceReportMedico();
        this.converter = new JsonConverter();
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
        String tarea = request.getParameter("tarea");
        switch (tarea) {
            case "":

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
        String tarea = request.getParameter("tarea");
        switch (tarea) {
            case "topPaciente":
                listarTopPaciente(request, response);
                break;
            case "topPacienteFechas":
                listarTopPacienteFechas(request, response);
                break;
            case "topEspecialidades":
                listarTopEspecialidades(request, response);
                break;
            case "topEspecialidadesFechas":
                listarTopEspecialidadesFechas(request, response);
                break;
            default:
        }
    }

    private void listarTopPaciente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario user = (Usuario) this.converter.fromJson(this.lector.read(request.getReader()), Usuario.class);
        String json = this.converter.toJson(this.serviceReportMedico.getTopPacientes(user));
        response.getWriter().write(json);
    }

    private void listarTopPacienteFechas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fecha1 = request.getParameter("fecha1");
        String fecha2 = request.getParameter("fecha2");
        Usuario user = (Usuario) this.converter.fromJson(this.lector.read(request.getReader()), Usuario.class);
        String json = this.converter.toJson(this.serviceReportMedico.getTopPacientes(user, fecha1, fecha2));
        response.getWriter().write(json);
    }

    private void listarTopEspecialidades(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario user = (Usuario) this.converter.fromJson(this.lector.read(request.getReader()), Usuario.class);
        String json = this.converter.toJson(this.serviceReportMedico.getTopEscpecialidades(user));
        response.getWriter().write(json);
    }

    private void listarTopEspecialidadesFechas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fecha1 = request.getParameter("fecha1");
        String fecha2 = request.getParameter("fecha2");
        Usuario user = (Usuario) this.converter.fromJson(this.lector.read(request.getReader()), Usuario.class);
        String json = this.converter.toJson(this.serviceReportMedico.getTopEscpecialidades(user, fecha1, fecha2));
        response.getWriter().write(json);
    }

}
