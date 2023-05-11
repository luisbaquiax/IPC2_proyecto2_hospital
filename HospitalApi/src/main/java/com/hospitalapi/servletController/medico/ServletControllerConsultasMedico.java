/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hospitalapi.servletController.medico;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hospitalapi.model.Consulta;
import com.hospitalapi.model.ExamenConsulta;
import com.hospitalapi.objects.JsonConverter;
import com.hospitalapi.objects.LectorJson;
import com.hospitalapi.objects.ListaEstadosConsulta;
import com.hospitalapi.service.medico.ServiceConsultas;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
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
@WebServlet(name = "ServletControllerConsultasMedico", urlPatterns = {"/ServletControllerConsultasMedico"})
public class ServletControllerConsultasMedico extends HttpServlet {

    private ServiceConsultas serviceConsultas;
    private JsonConverter converter;
    private LectorJson lector;

    public ServletControllerConsultasMedico() {
        this.serviceConsultas = new ServiceConsultas();
        this.converter = new JsonConverter<>();
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
            case "agendadas":
                sendAgendadas(request, response);
                break;
            case "agendadaBy":
                sendAgendadasBy(request, response);
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
            case "insertExamenConsulta":
                inserExamenesConsulta(request, response);
                break;
            default:
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String tarea = request.getParameter("tarea");
        switch (tarea) {
            case "update":
                updateConsulta(request, response);
                break;
            default:
        }
    }

    private void sendAgendadas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fecha = request.getParameter("fecha");
        int medico = Integer.parseInt(request.getParameter("id"));
        System.out.println(fecha);
        if (fecha.equals("hoy")) {
            System.out.println(Arrays.toString(this.serviceConsultas.getConsultasAgendadas(
                    medico,
                    LocalDate.now().toString() + " 00:00:00",
                    LocalDate.now().toString() + " 23:59:00",
                    ListaEstadosConsulta.AGENDADA).toArray()));
            response.getWriter().write(this.converter.toJson(this.serviceConsultas.getConsultasAgendadas(
                    medico,
                    LocalDate.now().toString() + " 00:00:00",
                    LocalDate.now().toString() + " 23:59:00",
                    ListaEstadosConsulta.AGENDADA)));
        } else {
            System.out.println(Arrays.toString(this.serviceConsultas.getConsultasAgendadas(
                    medico,
                    fecha + " 00:00:00",
                    fecha + " 23:59:00",
                    ListaEstadosConsulta.AGENDADA).toArray()));
            response.getWriter().write(this.converter.toJson(this.serviceConsultas.getConsultasAgendadas(
                    medico,
                    fecha + " 00:00:00",
                    fecha + " 23:59:00",
                    ListaEstadosConsulta.AGENDADA)));
        }

    }

    private void inserExamenesConsulta(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String json = this.lector.read(request.getReader());
        Type listType2 = new TypeToken<List<ExamenConsulta>>() {
        }.getType();
        List<ExamenConsulta> list = new Gson().fromJson(json, listType2);
        System.out.println(Arrays.toString(list.toArray()));
        for (ExamenConsulta examenConsulta : list) {
            this.serviceConsultas.insert(examenConsulta);
        }
    }

    private void sendAgendadasBy(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int paciente = Integer.parseInt(request.getParameter("paciente"));
        int medico = Integer.parseInt(request.getParameter("medico"));
        String estado = request.getParameter("estado");
        List<Consulta> list = this.serviceConsultas.getConsultasBy(medico, paciente, estado);
        System.out.println(Arrays.toString(list.toArray()));
        response.getWriter().write(this.converter.toJson(list));

    }

    private void updateConsulta(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Consulta consulta = (Consulta) this.converter.fromJson(this.lector.read(request.getReader()), Consulta.class);
        this.serviceConsultas.update(consulta);
    }

}
