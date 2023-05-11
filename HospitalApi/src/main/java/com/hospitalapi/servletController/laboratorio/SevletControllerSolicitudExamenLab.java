/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hospitalapi.servletController.laboratorio;

import com.hospitalapi.data.modelDB.ExamanesLaboratorioDB;
import com.hospitalapi.model.SolicitudExamen;
import com.hospitalapi.objects.JsonConverter;
import com.hospitalapi.objects.LectorJson;
import com.hospitalapi.service.paciente.ServiceSolicitudExamen;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luis
 */
@WebServlet(name = "SevletControllerSolicitudExamenLab", urlPatterns = {"/SevletControllerSolicitudExamenLab"})
public class SevletControllerSolicitudExamenLab extends HttpServlet {

    private LectorJson lector;
    private JsonConverter converter;
    private ServiceSolicitudExamen serviceSolicitudExamen;

    public SevletControllerSolicitudExamenLab() {
        this.lector = new LectorJson();
        this.converter = new JsonConverter();
        this.serviceSolicitudExamen = new ServiceSolicitudExamen();
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
        response.setContentType("application/json");
        String tarea = request.getParameter("tarea");
        switch (tarea) {
            case "solicitudesPendiente":
                sendListSolicitudes(request, response);
                break;
            case "examenesSolicitud":
                sendListExamensSolicitud(request, response);
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
        String tarea = request.getParameter("tarea");
        switch (tarea) {
            case "":
                break;
            default:
        }
    }

    private void sendListSolicitudes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int lab = Integer.parseInt(request.getParameter("laboratorio"));
        int paciente = Integer.parseInt(request.getParameter("paciente"));
        String estado = request.getParameter("estado");
        List<SolicitudExamen> list = this.serviceSolicitudExamen.getListBy(lab, paciente, estado);

        response.getWriter().write(this.converter.toJson(list));

    }

    private void sendListExamensSolicitud(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int solicitud = Integer.parseInt(request.getParameter("id"));
        System.out.println("examenes solicitud ");
        System.out.println(Arrays.toString(this.serviceSolicitudExamen.getListExmamenes(solicitud).toArray()));
        response.getWriter().write(this.converter.toJson(this.serviceSolicitudExamen.getListExmamenes(solicitud)));
    }

}
