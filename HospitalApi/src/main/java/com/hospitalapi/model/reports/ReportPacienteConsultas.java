/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.model.reports;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author luis
 */
@Getter
@Setter
@ToString
public class ReportPacienteConsultas {

    private String fechaAgenda;
    private String fechaCreacion;
    private String especialidad;
    private String estado;
    private String informe;
    private Double precio;
    private String fecha;
    private String hora;

    /**
     *
     * @param fechaAgenda
     * @param fechaCreacion
     * @param especialidad
     * @param estado
     * @param informe
     * @param precio
     */
    public ReportPacienteConsultas(String fechaAgenda, String fechaCreacion, String especialidad, String estado, String informe, Double precio) {
        this.fechaAgenda = fechaAgenda;
        this.fechaCreacion = fechaCreacion;
        this.especialidad = especialidad;
        this.estado = estado;
        this.informe = informe;
        this.precio = precio;
        String[] list = this.fechaAgenda.split(" ");
        this.fecha = list[0];
        this.hora = list[1];
    }
}
