/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.apiresthospital.servletController.user;

import com.apiresthospital.model.Usuario;
import com.apiresthospital.objects.Encriptador;
import com.apiresthospital.objects.JsonConverter;
import com.apiresthospital.objects.LectorJson;
import com.apiresthospital.service.users.UsuarioService;
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
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    private LectorJson lectorJson;
    private JsonConverter jsonConverter;
    private Encriptador encriptador;

    private UsuarioService usuarioService;

    public UserController() {
        this.lectorJson = new LectorJson();
        this.jsonConverter = new JsonConverter();
        this.usuarioService = new UsuarioService();
        this.encriptador = new Encriptador();
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
            case "ingresarNuevo":
                String json = this.lectorJson.read(request.getReader());
                System.out.println(json);
                Usuario usuario = (Usuario) this.jsonConverter.fromJson(json, Usuario.class);
                usuario.setPassword(encriptador.encriptar(usuario.getPassword()));
                if (usuarioService.insert(usuario)) {
                    System.out.println("bien");
                    response.getWriter().print(json);
                } else {
                    System.out.println("falló");
                }
                break;
            default:
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tarea = request.getParameter("tarea");
        switch (tarea) {
            case "editarUsuario":
                String json = this.lectorJson.read(request.getReader());
                System.out.println(json);
                Usuario usuario = (Usuario) this.jsonConverter.fromJson(json, Usuario.class);
                usuario.setPassword(encriptador.encriptar(usuario.getPassword()));
                if (usuarioService.update(usuario)) {
                    System.out.println("bien");
                } else {
                    System.out.println("falló");
                }
                response.getWriter().print(json);
                break;
            default:
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

}
