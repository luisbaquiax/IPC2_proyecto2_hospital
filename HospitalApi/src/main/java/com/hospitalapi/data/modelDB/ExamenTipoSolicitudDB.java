/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.data.modelDB;

import com.hospitalapi.data.coneccionDB.ConeccionDB;
import com.hospitalapi.model.ExamenSolicitado;
import com.hospitalapi.model.ExamenTipoSolicitud;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class ExamenTipoSolicitudDB {

    private static final String SELECT = "SELECT tipo_examen, solicitud, precio, estado, t.nombre\n"
            + "FROM examenes_solicitud e\n"
            + "INNER JOIN tipo_examen t\n"
            + "ON e.tipo_examen = t.id\n"
            + "WHERE solicitud = ?";

    private ResultSet resultSet;

    public ExamenTipoSolicitudDB() {
    }

    public List<ExamenTipoSolicitud> getListBySolicitud(int solicitud) {
        List<ExamenTipoSolicitud> list = new ArrayList<>();
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(SELECT)) {
            statement.setInt(1, solicitud);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(ExamenTipoSolicitud.builder().
                        nombre(resultSet.getString("nombre")).
                        examen(ExamenSolicitado.builder().
                                estado(resultSet.getBoolean("estado")).
                                idExamen(resultSet.getInt("tipo_examen")).
                                idSolicitud(resultSet.getInt("solicitud")).
                                precio(resultSet.getDouble("precio")).
                                build()).
                        build());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamenTipoSolicitudDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
}
