/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.data.modelDB.reportsDB;

import com.hospitalapi.data.coneccionDB.ConeccionDB;
import com.hospitalapi.model.reports.ReportLabTopExamenes;
import com.hospitalapi.model.reports.ReportLabTopPacientes;
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
public class ReportLabTopPacientesDB {

    private static final String SELECT
            = "SELECT SUM(ganancia_lab) AS ganancia, COUNT(paciente) AS cantidad, u.nombre as paciente, u.id\n"
            + "FROM solicitud_examen s\n"
            + "INNER JOIN paciente p\n"
            + "ON s.paciente = p.id\n"
            + "INNER JOIN usuario u\n"
            + "ON p.id = u.id\n"
            + "WHERE laboratorio = ?\n"
            + "GROUP BY paciente\n"
            + "ORDER BY SUM(ganancia_lab) DESC\n"
            + "LIMIT 5";

    private ResultSet resultSet;

    public ReportLabTopPacientesDB() {
    }

    public List<ReportLabTopPacientes> getList(int laboratorio) {
        List<ReportLabTopPacientes> list = new ArrayList<>();
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(SELECT)) {
            statement.setInt(1, laboratorio);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(ReportLabTopPacientes.builder().
                        idPaciente(resultSet.getInt("id")).
                        paciente(resultSet.getString("paciente")).
                        cantidad(resultSet.getInt("cantidad")).
                        ganancia(resultSet.getDouble("ganancia")).build());
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReportLabTopExamenesDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
