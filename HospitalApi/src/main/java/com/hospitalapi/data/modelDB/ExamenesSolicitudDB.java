/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.data.modelDB;

import com.hospitalapi.data.coneccionDB.ConeccionDB;
import com.hospitalapi.model.ExamenSolicitado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class ExamenesSolicitudDB {

    private static final String INSERT = "INSERT INTO examenes_solicitud(tipo_examen,solicitud,precio,estado) VALUES(?,?,?,?)";
    
    private static final String UPDATE = "UPDATE examenes_solicitud SET estado =? WHERE tipo_examen = ? AND solicitud = ?";

    private ResultSet resultSet;

    public ExamenesSolicitudDB() {
    }

    /**
     *
     * @param exaSolicitado
     * @return
     */
    public boolean insert(ExamenSolicitado exaSolicitado) {
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(INSERT)) {
            statement.setInt(1, exaSolicitado.getIdExamen());
            statement.setInt(2, exaSolicitado.getIdSolicitud());
            statement.setDouble(3, exaSolicitado.getPrecio());
            statement.setBoolean(4, exaSolicitado.isEstado());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ExamenesSolicitudDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }
    }
    public boolean update(ExamenSolicitado exaSolicitado) {
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(UPDATE)) {
            statement.setBoolean(1, exaSolicitado.isEstado());
            statement.setInt(2, exaSolicitado.getIdExamen());
            statement.setInt(3, exaSolicitado.getIdSolicitud());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ExamenesSolicitudDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }
    }
}
