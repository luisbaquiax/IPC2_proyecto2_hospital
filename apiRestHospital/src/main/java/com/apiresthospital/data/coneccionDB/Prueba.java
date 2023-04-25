/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apiresthospital.data.coneccionDB;

import com.apiresthospital.objects.Encriptador;
import java.sql.SQLException;

/**
 *
 * @author luis
 */
public class Prueba {
    public static void main(String[] args) throws SQLException {
        ConeccionDB.getConnection();
        Encriptador encriptador = new Encriptador();
        String[] pass = new String[]{"contra1","contra2","contra3","contra4","contra5","contra6","contra7"};
        System.out.println(encriptador.encriptar(pass[0]));
    }
}
