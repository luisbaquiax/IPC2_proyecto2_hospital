/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hospitalapi.servletController.medico;

import com.hospitalapi.model.Medico;
import com.hospitalapi.model.Usuario;
import com.hospitalapi.objects.JsonConverter;
import com.hospitalapi.objects.LectorJson;
import com.hospitalapi.service.medico.ServiceEspecialidad;
import com.hospitalapi.service.medico.ServiceMedicoEspecialidad;
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
@WebServlet(name = "servletControllerMedicoEspecialidad", urlPatterns = {"/servletControllerMedicoEspecialidad"})
public class servletControllerMedicoEspecialidad extends HttpServlet {

    private JsonConverter converter;
    private LectorJson lectorJson;
    private ServiceMedicoEspecialidad serviceEspecialidadesMedico;
    private ServiceEspecialidad serviceEspecialidad;

    public servletControllerMedicoEspecialidad() {
        this.converter = new JsonConverter();
        this.lectorJson = new LectorJson();
        this.serviceEspecialidadesMedico = new ServiceMedicoEspecialidad();
        this.serviceEspecialidad = new ServiceEspecialidad();
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
            case "especialidadesDisponibles":
                listarEspecialidadesDisponbles(request, response);
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
            case "especialidades":
                listarEspecialidades(request, response);
                break;
            default:
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private void listarEspecialidades(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario medico = (Usuario) this.converter.fromJson(lectorJson.read(request.getReader()), Usuario.class);
        String json = this.converter.toJson(this.serviceEspecialidadesMedico.getEspecialidadsByMedico(medico));

        response.getWriter().print(json);
    }

    private void listarEspecialidadesDisponbles(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write(this.converter.toJson(this.serviceEspecialidad.getEspecialidades()));
    }

}
