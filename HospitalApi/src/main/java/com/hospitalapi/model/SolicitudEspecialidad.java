/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.model;

import java.io.Serializable;
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
public class SolicitudEspecialidad implements Serializable {

    public static final String ACEPTADO = "ACEPTADO";
    public static final String RECHAZADO = "RECHAZADO";
    public static final String SOLICITADO = "SOLICITADO";
    private String nombre;
    /**
     * Estado de la solicitud = aceptado / rechazado / solicitado
     */
    private String estado;
    private String descripcion;
}
