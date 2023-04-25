/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apiresthospital.data.modelDB;

import com.apiresthospital.data.coneccionDB.ConeccionDB;
import java.sql.PreparedStatement;

/**
 *
 * @author luis
 */
public class LaboratorioDB {

    private static final String INSERT = "INSERT INTO laboratorio(id) VALUES(?)";

    public LaboratorioDB() {
    }

    public boolean insert(int id) {
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(INSERT)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
