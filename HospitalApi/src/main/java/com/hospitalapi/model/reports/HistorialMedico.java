/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.model.reports;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author luis
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistorialMedico {

    private int consulta;
    private String fechaCreacion;
    private String fechaAgenda;
    private String informe;
    private String estado;
    private String examen;
    private String medico;
    private double precio;
    private String fecha;
    private String hora;

    public void setFechaAndHour() {
        String[] list = this.fechaAgenda.split(" ");
        this.fecha = list[0];
        this.hora = list[1];
    }
}
