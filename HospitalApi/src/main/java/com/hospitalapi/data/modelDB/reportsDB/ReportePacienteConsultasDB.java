/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hospitalapi.data.modelDB.reportsDB;

import com.hospitalapi.data.coneccionDB.ConeccionDB;
import com.hospitalapi.model.reports.ReportPacienteConsultas;
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
public class ReportePacienteConsultasDB {

    private static final String SELECT
            = "SELECT precio, informe, fecha_creacion, fecha_agendada, estado, e.nombre\n"
            + "FROM consulta c\n"
            + "INNER JOIN especialidad e\n"
            + "ON c.especialidad = e.id\n"
            + "WHERE paciente = ?";

    private static final String SELECT_BETWEEN_FECHA = "SELECT * FROM consulta WHERE paciente = 111 AND especialidad = 3;\n"
            + "SELECT precio, informe, fecha_creacion, fecha_agendada, estado, e.nombre\n"
            + "FROM consulta c\n"
            + "INNER JOIN especialidad e\n"
            + "ON c.especialidad = e.id\n"
            + "WHERE paciente = ? "
            + "AND fecha_creacion BETWEEN ? AND ?;";

    private static final String SELECT_BY_ESPECIALIDAD = "SELECT precio, informe, fecha_creacion, fecha_agendada, estado, e.nombre\n"
            + "FROM consulta c\n"
            + "INNER JOIN especialidad e\n"
            + "ON c.especialidad = e.id\n"
            + "WHERE paciente = ? \n"
            + "AND especialidad = ?";

    private ResultSet resutSet;

    public ReportePacienteConsultasDB() {
    }

    public List<ReportPacienteConsultas> getList(int idPaciente) {
        List<ReportPacienteConsultas> list = new ArrayList<>();
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(SELECT)) {
            statement.setInt(1, idPaciente);
            resutSet = statement.executeQuery();
            while (resutSet.next()) {
                list.add(get(resutSet));
            }
            resutSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReportePacienteConsultasDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<ReportPacienteConsultas> getList(int idPaciente, String fecha1, String fecha2) {
        List<ReportPacienteConsultas> list = new ArrayList<>();
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(SELECT_BETWEEN_FECHA)) {
            statement.setInt(1, idPaciente);
            statement.setString(2, fecha1);
            statement.setString(3, fecha2);
            resutSet = statement.executeQuery();
            while (resutSet.next()) {
                list.add(get(resutSet));
            }
            resutSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReportePacienteConsultasDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<ReportPacienteConsultas> getList(int idPaciente, String especialidad) {
        List<ReportPacienteConsultas> list = new ArrayList<>();
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(SELECT_BY_ESPECIALIDAD)) {
            statement.setInt(1, idPaciente);
            statement.setString(2, especialidad);
            resutSet = statement.executeQuery();
            while (resutSet.next()) {
                list.add(get(resutSet));
            }
            resutSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReportePacienteConsultasDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    private ReportPacienteConsultas get(ResultSet resultSet) throws SQLException {
        return new ReportPacienteConsultas(
                resultSet.getString("fecha_agendada"),
                resultSet.getString("fecha_creacion"),
                resultSet.getString("nombre"),
                resultSet.getString("estado"),
                resultSet.getString("informe"),
                resultSet.getDouble("precio"));
    }

}
