/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hospitalapi.servletController.paciente;

import com.hospitalapi.model.Recarga;
import com.hospitalapi.model.Usuario;
import com.hospitalapi.objects.JsonConverter;
import com.hospitalapi.objects.LectorJson;
import com.hospitalapi.service.paciente.ServiceHistorialRecarga;
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
@WebServlet(name = "ServletControllerRecarga", urlPatterns = {"/ServletControllerRecarga"})
public class ServletControllerRecarga extends HttpServlet {

    private JsonConverter converter;
    private LectorJson lector;
    private ServiceHistorialRecarga serviceHistorialRecarga;

    public ServletControllerRecarga() {
        this.converter = new JsonConverter();
        this.lector = new LectorJson();
        this.serviceHistorialRecarga = new ServiceHistorialRecarga();

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
            case "recargas":
                listarRecargas(request, response);
                break;
            case "insertar":
                ingesarRecarga(request, response);
                break;
            default:
        }
    }

    private void listarRecargas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario user = (Usuario) this.converter.fromJson(this.lector.read(request.getReader()), Usuario.class);
        response.getWriter().write(this.converter.toJson(this.serviceHistorialRecarga.getList(user)));
    }

    private void ingesarRecarga(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Recarga recarga = (Recarga) this.converter.fromJson(this.lector.read(request.getReader()), Recarga.class);
        recarga.setFechaHora(recarga.getFecha() + " " + recarga.getHora());
        if (this.serviceHistorialRecarga.insert(recarga)) {
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}
