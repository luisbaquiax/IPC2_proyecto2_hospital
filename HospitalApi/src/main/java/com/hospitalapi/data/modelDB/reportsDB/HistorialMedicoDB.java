/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.data.modelDB.reportsDB;

import com.hospitalapi.data.coneccionDB.ConeccionDB;
import com.hospitalapi.model.reports.HistorialMedico;
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
public class HistorialMedicoDB {

    public static final String SELECT
            = "SELECT fecha_creacion, fecha_agendada, informe, estado, t.nombre AS examen, u.nombre as medico, precio, c.id as consulta\n"
            + "FROM consulta c\n"
            + "INNER JOIN examenes_consulta e\n"
            + "ON c.id = e.consulta\n"
            + "INNER JOIN tipo_examen t\n"
            + "ON t.id = e.examen\n"
            + "INNER JOIN medico m\n"
            + "ON c.medico = m.id\n"
            + "INNER JOIN usuario u\n"
            + "ON m.id = u.id\n"
            + "WHERE paciente = ?";

    public static final String SELECT_BETWEEN_DATE
            = "SELECT fecha_creacion, fecha_agendada, informe, estado, t.nombre AS examen, u.nombre as medico, precio, c.id as consulta\n"
            + "FROM consulta c\n"
            + "INNER JOIN examenes_consulta e\n"
            + "ON c.id = e.consulta\n"
            + "INNER JOIN tipo_examen t\n"
            + "ON t.id = e.examen\n"
            + "INNER JOIN medico m\n"
            + "ON c.medico = m.id\n"
            + "INNER JOIN usuario u\n"
            + "ON m.id = u.id\n"
            + "WHERE paciente = ?\n"
            + "AND fecha_creacion BETWEEN ? AND ?";

    private static final String SELECT_BY_PACIENTE_MEDICO
            = "SELECT fecha_creacion, fecha_agendada, informe, estado, t.nombre AS examen, u.nombre as medico, precio, c.id as consulta\n"
            + "FROM consulta c\n"
            + "INNER JOIN examenes_consulta e\n"
            + "ON c.id = e.consulta\n"
            + "INNER JOIN tipo_examen t\n"
            + "ON t.id = e.examen\n"
            + "INNER JOIN medico m\n"
            + "ON c.medico = m.id\n"
            + "INNER JOIN usuario u\n"
            + "ON m.id = u.id\n"
            + "WHERE paciente = ?\n"
            + "AND medico = ? ORDER BY fecha_creacion DESC";
    
    private static final String SELECT_BY_MEDICO
            = "SELECT fecha_creacion, fecha_agendada, informe, estado, t.nombre AS examen, u.nombre as medico, precio, c.id as consulta\n"
            + "FROM consulta c\n"
            + "INNER JOIN examenes_consulta e\n"
            + "ON c.id = e.consulta\n"
            + "INNER JOIN tipo_examen t\n"
            + "ON t.id = e.examen\n"
            + "INNER JOIN medico m\n"
            + "ON c.medico = m.id\n"
            + "INNER JOIN usuario u\n"
            + "ON m.id = u.id\n"
            + "WHERE medico = ?\n"
            + "ORDER BY fecha_creacion DESC";

    private ResultSet resultSet;
    private HistorialMedico historial;

    public HistorialMedicoDB() {
    }

    /**
     * Historial medico por paciente
     *
     * @param idPaciente
     * @return
     */
    public List<HistorialMedico> getByPaciente(int idPaciente) {
        List<HistorialMedico> list = new ArrayList<>();
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(SELECT)) {
            statement.setInt(1, idPaciente);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                historial = get(resultSet);
                historial.setFechaAndHour();
                list.add(historial);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(HistorialMedicoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Historial medico por paciente
     *
     * @param idPaciente
     * @param fecha1
     * @param fecha2
     * @return
     */
    public List<HistorialMedico> getByPaciente(int idPaciente, String fecha1, String fecha2) {
        List<HistorialMedico> list = new ArrayList<>();
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(SELECT_BETWEEN_DATE)) {
            statement.setInt(1, idPaciente);
            statement.setString(2, fecha1);
            statement.setString(3, fecha2);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                historial = get(resultSet);
                historial.setFechaAndHour();
                list.add(historial);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(HistorialMedicoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<HistorialMedico> getByPaciente(int idPaciente, int idMedico) {
        List<HistorialMedico> list = new ArrayList<>();
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(SELECT_BY_PACIENTE_MEDICO)) {
            statement.setInt(1, idPaciente);
            statement.setInt(2, idMedico);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                historial = get(resultSet);
                historial.setFechaAndHour();
                list.add(historial);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(HistorialMedicoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<HistorialMedico> getByMedico(int idMedico) {
        List<HistorialMedico> list = new ArrayList<>();
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(SELECT_BY_MEDICO)) {
            statement.setInt(1, idMedico);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                historial = get(resultSet);
                historial.setFechaAndHour();
                list.add(historial);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(HistorialMedicoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public HistorialMedico get(ResultSet resultSet) throws SQLException {
        return HistorialMedico.builder().
                consulta(resultSet.getInt("consulta")).
                precio(resultSet.getDouble("precio")).
                examen(resultSet.getString("examen")).
                estado(resultSet.getString("estado")).
                informe(resultSet.getString("informe")).
                fechaAgenda(resultSet.getString("fecha_agendada")).
                fechaCreacion(resultSet.getString("fecha_creacion")).
                medico(resultSet.getString("medico")).build();
    }
}
