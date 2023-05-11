/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hospitalapi.servletController.admin;

import com.hospitalapi.model.HistorialPorcentaje;
import com.hospitalapi.objects.JsonConverter;
import com.hospitalapi.objects.LectorJson;
import com.hospitalapi.service.admin.HistorialPorcentajeService;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luis
 */
@WebServlet(name = "AdminController", urlPatterns = {"/AdminController"})
public class AdminController extends HttpServlet {

    private HistorialPorcentajeService historialPorcentajeService;

    private JsonConverter converter;
    private LectorJson lector;

    public AdminController() {
        this.historialPorcentajeService = new HistorialPorcentajeService();
        this.converter = new JsonConverter();
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String tarea = request.getParameter("tarea");
        switch (tarea) {
            case "getAll":
                sendListHistoriorial(request, response);
                break;
            case "getInfo":
                sendListHistoriorialInfo(request, response);
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
                insertHistoriorial(request, response);
                break;
            default:
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tarea = request.getParameter("tarea");
        response.setContentType("application/json");
        switch (tarea) {
            case "update":
                updateHistoriorial(request, response);
                break;
            default:
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    private void sendListHistoriorial(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write(this.converter.toJson(this.historialPorcentajeService.getAll()));
    }

    private void updateHistoriorial(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HistorialPorcentaje historial = (HistorialPorcentaje) this.converter.fromJson(this.lector.read(request.getReader()), HistorialPorcentaje.class);
        if (historialPorcentajeService.update(historial, LocalDate.now().toString())) {
            System.out.println("Todo bien.");
        } else {
            System.out.println("NO se pudo actualizar");
        }
    }

    private void insertHistoriorial(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HistorialPorcentaje historial = (HistorialPorcentaje) this.converter.fromJson(this.lector.read(request.getReader()), HistorialPorcentaje.class);
        this.historialPorcentajeService.actualizarTodos();

        HistorialPorcentaje ultimo = this.historialPorcentajeService.getAll().get(0);

        historial.setFechaInicial(LocalDate.now().toString());
        historial.setFechaFinal(LocalDate.now().toString());
        historial.setEstado(HistorialPorcentaje.ACTUAL);

        System.out.println("ingresado " + historial.toString());

        if (historialPorcentajeService.insert(historial)) {
            historialPorcentajeService.update(ultimo, LocalDate.now().toString());
            System.out.println("todo bien");
        } else {
            System.out.println("fallo");
        }
    }

    private void sendListHistoriorialInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write(this.converter.toJson(this.historialPorcentajeService.getInfo()));
    }
}
