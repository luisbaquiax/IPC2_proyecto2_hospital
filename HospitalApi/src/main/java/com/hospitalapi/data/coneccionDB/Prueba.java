/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.data.coneccionDB;

import java.sql.SQLException;

/**
 *
 * @author luis
 */
public class Prueba {
    public static void main(String[] args) throws SQLException {
        ConeccionDB.getConnection();
        String[] pass = new String[]{"contra1","contra2","contra3","contra4","contra5","contra6","contra7"};
    }
}
