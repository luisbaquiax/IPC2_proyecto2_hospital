/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hospitalapi.servletController.admin;

import com.hospitalapi.model.Especialidad;
import com.hospitalapi.model.SolicitudEspecialidad;
import com.hospitalapi.model.SolicitudTipoExamen;
import com.hospitalapi.model.TipoExamen;
import com.hospitalapi.objects.JsonConverter;
import com.hospitalapi.objects.LectorJson;
import com.hospitalapi.service.admin.ServiceSolicitudTipoExamen;
import com.hospitalapi.service.admin.ServiceSolicitudesEspecialidad;
import com.hospitalapi.service.laboratorio.ServiceExamenes;
import com.hospitalapi.service.medico.ServiceEspecialidad;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luis
 */
@WebServlet(name = "ServletControllerSolicitudes", urlPatterns = {"/ServletControllerSolicitudes"})
public class ServletControllerSolicitudes extends HttpServlet {

    private ServiceSolicitudesEspecialidad servceSolicitudesEspecialidad;
    private ServiceSolicitudTipoExamen serviceSolicitudTipoExamen;
    private ServiceEspecialidad serviceEspecialidad;
    private ServiceExamenes serviceExamenes;

    private LectorJson lector;
    private JsonConverter converter;

    public ServletControllerSolicitudes() {
        this.servceSolicitudesEspecialidad = new ServiceSolicitudesEspecialidad();
        this.serviceSolicitudTipoExamen = new ServiceSolicitudTipoExamen();
        this.serviceEspecialidad = new ServiceEspecialidad();
        this.serviceExamenes = new ServiceExamenes();

        this.lector = new LectorJson();
        this.converter = new JsonConverter<>();
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
        String tarea = request.getParameter("tarea");
        switch (tarea) {
            case "listEspecialidad":
                sendListSolicitudesEspecialidad(request, response);
                break;
            case "listExamen":
                sendListSolicitudesExamen(request, response);
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
            case "insertSolicitudEspecialidad":
                inserSolicitudEspecialidad(request, response);
                break;
            case "insertSolicitudExamen":
                insertSolicitudExamen(request, response);
                break;
            case "insertNuevaEspecialidad":
                insertNewEspecialidad(request, response);
                break;
            case "insertNuevoExamen":
                insertNewExamen(request, response);
                break;
            default:
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tarea = request.getParameter("tarea");
        switch (tarea) {
            case "updateSolicitudEspecialidad":
                updateSolicitudEspecialidad(request, response);
                break;
            case "updateSolicitudExamen":
                updateSolicitudExamen(request, response);
                break;
            default:
        }

    }

    private void sendListSolicitudesEspecialidad(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write(this.converter.toJson(this.servceSolicitudesEspecialidad.getList()));
    }

    private void inserSolicitudEspecialidad(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SolicitudEspecialidad solicitud = (SolicitudEspecialidad) this.converter.fromJson(this.lector.read(request.getReader()), SolicitudEspecialidad.class);
        if (servceSolicitudesEspecialidad.insert(solicitud)) {
            System.out.println("Todo bien.");
        } else {
            System.out.println("falló");
        }
    }

    private void updateSolicitudEspecialidad(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SolicitudEspecialidad solicitud = (SolicitudEspecialidad) this.converter.fromJson(this.lector.read(request.getReader()), SolicitudEspecialidad.class);
        if (servceSolicitudesEspecialidad.update(solicitud)) {
            System.out.println("Todo bien. update ");
        } else {
            System.out.println("falló update");
        }
    }

    private void sendListSolicitudesExamen(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println(this.serviceSolicitudTipoExamen.getList());
            response.getWriter().write(this.converter.toJson(this.serviceSolicitudTipoExamen.getList()));
        } catch (IOException ex) {
            Logger.getLogger(ServletControllerSolicitudes.class.getName()).log(Level.SEVERE, null, ex);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void insertSolicitudExamen(HttpServletRequest request, HttpServletResponse response) {
        try {
            SolicitudTipoExamen solicitud = (SolicitudTipoExamen) this.converter.fromJson(this.lector.read(request.getReader()), SolicitudTipoExamen.class);
            if (serviceSolicitudTipoExamen.insert(solicitud)) {
                System.out.println("bien: ");
            } else {
                System.out.println("falló: ");
            }
        } catch (IOException ex) {
            Logger.getLogger(ServletControllerSolicitudes.class.getName()).log(Level.SEVERE, null, ex);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void updateSolicitudExamen(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SolicitudTipoExamen solicitud = (SolicitudTipoExamen) this.converter.fromJson(this.lector.read(request.getReader()), SolicitudTipoExamen.class);
        if (serviceSolicitudTipoExamen.update(solicitud)) {
            System.out.println("bien: ");
        } else {
            System.out.println("falló: ");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void insertNewEspecialidad(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Especialidad especialidad = (Especialidad) this.converter.fromJson(this.lector.read(request.getReader()), Especialidad.class);
        if (serviceEspecialidad.insert(especialidad)) {
            System.out.println("bien");
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            System.out.println("falló");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void insertNewExamen(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TipoExamen tipoExamen = (TipoExamen) this.converter.fromJson(this.lector.read(request.getReader()), TipoExamen.class);
        if (serviceExamenes.insert(tipoExamen)) {
            System.out.println("bien");
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            System.out.println("falló");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}
