/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hospitalapi.servletController.paciente;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hospitalapi.model.SolicitudExamen;
import com.hospitalapi.model.reports.ExamenesLaboratorio;
import com.hospitalapi.objects.JsonConverter;
import com.hospitalapi.objects.LectorJson;
import com.hospitalapi.service.paciente.ServiceSolicitudExamen;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
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
@WebServlet(name = "ServletControllerSolicitudExamen", urlPatterns = {"/ServletControllerSolicitudExamen"})
public class ServletControllerSolicitudExamen extends HttpServlet {

    private LectorJson lector;
    private JsonConverter converter;
    private ServiceSolicitudExamen serviceSolicitudExamen;

    public ServletControllerSolicitudExamen() {
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
            case "":
                addSolicitudExamen(request, response);
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
            case "insert":
                addSolicitudExamen(request, response);
                break;
            case "insertExamenes":
                addExamenenes(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    private void addSolicitudExamen(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SolicitudExamen solicitud = (SolicitudExamen) this.converter.fromJson(this.lector.read(request.getReader()), SolicitudExamen.class);
        this.serviceSolicitudExamen.insertSolicitudExamen(solicitud);
    }

    private void addExamenenes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = this.lector.read(request.getReader());

        Type listType2 = new TypeToken<List<ExamenesLaboratorio>>() {
        }.getType();
        ArrayList<ExamenesLaboratorio> list = new Gson().fromJson(json, listType2);
        System.out.println(Arrays.toString(list.toArray()));

        this.serviceSolicitudExamen.ingresarExamenesSolicitud(list);

    }

}
