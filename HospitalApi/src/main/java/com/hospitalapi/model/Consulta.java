/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author luis
 */
@ToString
@Getter
@Setter
public class Consulta implements Serializable {

    private int id;
    private int idPaciente;
    private int idMedico;
    private int idEspecialidad;
    private double porcentaje;
    private String fechaCreacion;
    private String fechaAgenda;
    private double precio;
    private String informe;
    private String estado;
    private double gananciaMedico;
    private double gananciaAdmin;
    private String fecha;
    private String hora;
    
    /**
     *
     * @param id
     * @param idPaciente
     * @param idMedico
     * @param idEspecialidad
     * @param porcentaje
     * @param fechaCreacion
     * @param fechaAgenda
     * @param precio
     * @param informe
     * @param estado
     * @param gananciaMedico
     * @param gananciaAdmin
     */
    public Consulta(int id, int idPaciente, int idMedico, int idEspecialidad, double porcentaje, String fechaCreacion, String fechaAgenda, double precio, String informe, String estado, double gananciaMedico, double gananciaAdmin) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.idEspecialidad = idEspecialidad;
        this.porcentaje = porcentaje;
        this.fechaCreacion = fechaCreacion;
        this.fechaAgenda = fechaAgenda;
        this.precio = precio;
        this.informe = informe;
        this.estado = estado;
        this.gananciaMedico = gananciaMedico;
        this.gananciaAdmin = gananciaAdmin;
        String[] fechaHora = this.fechaAgenda.split(" ");
        this.fecha = fechaHora[0];
        this.hora = fechaHora[1];
    }
}
