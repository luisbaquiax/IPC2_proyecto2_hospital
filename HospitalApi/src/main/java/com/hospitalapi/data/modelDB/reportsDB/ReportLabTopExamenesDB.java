/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.data.modelDB.reportsDB;

import com.hospitalapi.data.coneccionDB.ConeccionDB;
import com.hospitalapi.model.reports.ReportLabTopExamenes;
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
public class ReportLabTopExamenesDB {

    private static final String SELECT
            = "SELECT SUM(precio) AS ganancia, COUNT(tipo_examen) AS cantidad, t.nombre as examen, t.id\n"
            + "FROM examenes_solicitud e\n"
            + "INNER JOIN solicitud_examen s\n"
            + "ON e.solicitud = s.id\n"
            + "INNER JOIN tipo_examen t\n"
            + "ON e.tipo_examen = t.id\n"
            + "WHERE s.laboratorio = ?\n"
            + "GROUP BY tipo_examen\n"
            + "ORDER BY SUM(precio) DESC\n"
            + "LIMIT 5;";

    private static final String SELECT_BETWEEN_DATE
            = "SELECT SUM(precio) AS ganancia, COUNT(tipo_examen) AS cantidad, t.nombre as examen, t.id\n"
            + "FROM examenes_solicitud e\n"
            + "INNER JOIN solicitud_examen s\n"
            + "ON e.solicitud = s.id\n"
            + "INNER JOIN tipo_examen t\n"
            + "ON e.tipo_examen = t.id\n"
            + "WHERE s.laboratorio = ? AND s.fecha_solicitado BETWEEN ? AND ?\n"
            + "GROUP BY tipo_examen\n"
            + "ORDER BY SUM(precio) DESC\n"
            + "LIMIT 5;";

    private ResultSet resultSet;

    public ReportLabTopExamenesDB() {
    }

    public List<ReportLabTopExamenes> getList(int laboratorio, String fecha1, String fecha2) {
        List<ReportLabTopExamenes> list = new ArrayList<>();
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(SELECT_BETWEEN_DATE)) {
            statement.setInt(1, laboratorio);
            statement.setString(2, fecha1);
            statement.setString(3, fecha2);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(get(resultSet));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReportLabTopExamenesDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<ReportLabTopExamenes> getList(int laboratorio) {
        List<ReportLabTopExamenes> list = new ArrayList<>();
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(SELECT)) {
            statement.setInt(1, laboratorio);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(get(resultSet));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReportLabTopExamenesDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    private ReportLabTopExamenes get(ResultSet resultSet) throws SQLException {
        return ReportLabTopExamenes.builder().
                idExamen(resultSet.getInt("id")).
                examen(resultSet.getString("examen")).
                cantidad(resultSet.getInt("cantidad")).
                ganancia(resultSet.getDouble("ganancia")).build();
    }
}
