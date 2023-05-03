/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.data.modelDB.reportsDB;

import com.hospitalapi.data.coneccionDB.ConeccionDB;
import com.hospitalapi.model.reports.MedicoIngresos;
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
public class MedicoIngresosDB {

    private static final String TOP_MEDICOS
            = "SELECT SUM(ganancia_medico) AS total_medico, SUM(ganancia_admin) AS total_app, COUNT(medico) AS cantidad, u.nombre, u.id\n"
            + "FROM consulta c\n"
            + "INNER JOIN medico m\n"
            + "ON m.id = c.medico\n"
            + "INNER JOIN usuario u\n"
            + "ON u.id = m.id\n"
            + "GROUP BY medico\n"
            + "ORDER BY SUM(ganancia_medico) DESC\n"
            + "LIMIT 5";

    private ResultSet resultSet;

    public MedicoIngresosDB() {
    }

    /**
     * List of top medico
     *
     * @return
     */
    public List<MedicoIngresos> getListTopMedicos() {
        List<MedicoIngresos> list = new ArrayList<>();
        try (PreparedStatement statemenet = ConeccionDB.getConnection().prepareStatement(TOP_MEDICOS)) {
            resultSet = statemenet.executeQuery();
            while (resultSet.next()) {
                list.add(MedicoIngresos.builder().
                        cantidadConsultas(resultSet.getInt("cantidad")).
                        gananciaApp(resultSet.getDouble("total_app")).
                        nombre("nombre").
                        totalPorConsultas(resultSet.getDouble("total_medico")).
                        id(resultSet.getInt("id")).
                        build());
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicoIngresosDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
