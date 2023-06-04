/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hospitalapi.servletController.laboratorio;

import com.hospitalapi.objects.JsonConverter;
import com.hospitalapi.service.laboratorio.ServiceResultadosLab;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ServletControllerResultadosLab", urlPatterns = {"/ServletControllerResultadosLab"})
public class ServletControllerResultadosLab extends HttpServlet {
    
    private ServiceResultadosLab serviceResultadosLab;
    private JsonConverter converter;

    public ServletControllerResultadosLab() {
        this.serviceResultadosLab = new ServiceResultadosLab();
        this.converter = new JsonConverter();
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
            case "resultados":
                sendListResultados(request, response);
                break;
            default:
                throw new AssertionError();
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
    }
    
    private void sendListResultados(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int solicitud = Integer.parseInt(request.getParameter("id"));
        System.out.println("resultados");
        System.out.println(Arrays.toString(this.serviceResultadosLab.getResultados(solicitud).toArray()));
        response.getWriter().write(this.converter.toJson(this.serviceResultadosLab.getResultados(solicitud)));
    }
    
}
