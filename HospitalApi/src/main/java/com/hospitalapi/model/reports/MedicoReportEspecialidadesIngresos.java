/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.model.reports;

import lombok.*;

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
public class MedicoReportEspecialidadesIngresos {

    private double gananciaMedico;
    private int cantidadConsulta;
    private String especialidad;
    private int idEspecialidad;
}
