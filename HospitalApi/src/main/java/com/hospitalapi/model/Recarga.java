/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.model;

import java.io.Serializable;
import lombok.*;
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
public class Recarga implements Serializable {

    private int id;
    private double monto;
    private String fechaHora;
    private int idPaciente;
    private String fecha;
    private String hora;
    
    public void establecerFechaHora(){
        String[] list = this.getFechaHora().split(" ");
        this.fecha = list[0];
        this.hora = list[1];
    }
}
