/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.data.modelDB.reportsDB;

import com.hospitalapi.data.coneccionDB.ConeccionDB;
import com.hospitalapi.model.reports.ReportPacienteExamenes;
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
public class ReportPacienteExamenesDB {

    private static final String SELECT
            = "SELECT s.id as solicitud, fecha_solicitado, fecha_finalizada, t.nombre as examen, estado, u.nombre, e.precio \n"
            + "FROM solicitud_examen s\n"
            + "INNER JOIN examenes_solicitud e\n"
            + "ON s.id = e.solicitud\n"
            + "INNER JOIN tipo_examen t\n"
            + "ON t.id = e.tipo_examen\n"
            + "INNER JOIN laboratorio l\n"
            + "ON l.id = s.laboratorio\n"
            + "INNER JOIN usuario u\n"
            + "ON l.id = u.id\n"
            + "WHERE paciente = ?";

    private static final String SELECT_BETWEEN_FECHA
            = "SELECT s.id as solicitud, fecha_solicitado, fecha_finalizada, t.nombre as examen, estado, u.nombre, e.precio\n"
            + "FROM solicitud_examen s\n"
            + "INNER JOIN examenes_solicitud e\n"
            + "ON s.id = e.solicitud\n"
            + "INNER JOIN tipo_examen t\n"
            + "ON t.id = e.tipo_examen\n"
            + "INNER JOIN laboratorio l\n"
            + "ON l.id = s.laboratorio\n"
            + "INNER JOIN usuario u\n"
            + "ON l.id = u.id\n"
            + "WHERE paciente = ?\n"
            + "AND fecha_solicitado BETWEEN ? AND ?";

    private static final String SELECT_BY_TIPO_EXAMEN
            = "SELECT s.id as solicitud, fecha_solicitado, fecha_finalizada, t.nombre as examen, estado, u.nombre, e.precio\n"
            + "FROM solicitud_examen s\n"
            + "INNER JOIN examenes_solicitud e\n"
            + "ON s.id = e.solicitud\n"
            + "INNER JOIN tipo_examen t\n"
            + "ON t.id = e.tipo_examen\n"
            + "INNER JOIN laboratorio l\n"
            + "ON l.id = s.laboratorio\n"
            + "INNER JOIN usuario u\n"
            + "ON l.id = u.id\n"
            + "WHERE paciente = ? \n"
            + "AND t.nombre = ?";

    private static final String SELECT_FECHA_EXAMEN
            = "SELECT s.id as solicitud, fecha_solicitado, fecha_finalizada, t.nombre as examen, estado, u.nombre, e.precio\n"
            + "FROM solicitud_examen s\n"
            + "INNER JOIN examenes_solicitud e\n"
            + "ON s.id = e.solicitud\n"
            + "INNER JOIN tipo_examen t\n"
            + "ON t.id = e.tipo_examen\n"
            + "INNER JOIN laboratorio l\n"
            + "ON l.id = s.laboratorio\n"
            + "INNER JOIN usuario u\n"
            + "ON l.id = u.id \n"
            + "WHERE paciente = ? \n"
            + "AND fecha_solicitado BETWEEN ? AND ?\n"
            + "AND t.nombre = ?";

    private ResultSet resultSet;

    public ReportPacienteExamenesDB() {
    }

    public List<ReportPacienteExamenes> getList(int idPaciente) {
        List<ReportPacienteExamenes> list = new ArrayList<>();
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(SELECT)) {
            statement.setInt(1, idPaciente);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(get(resultSet));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReportPacienteExamenesDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<ReportPacienteExamenes> getList(int idPaciente, String fecha1, String fecha2) {
        List<ReportPacienteExamenes> list = new ArrayList<>();
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(SELECT_BETWEEN_FECHA)) {
            statement.setInt(1, idPaciente);
            statement.setString(2, fecha1);
            statement.setString(3, fecha2);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(get(resultSet));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReportPacienteExamenesDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<ReportPacienteExamenes> getList(int idPaciente, String examen) {
        List<ReportPacienteExamenes> list = new ArrayList<>();
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(SELECT_BY_TIPO_EXAMEN)) {
            statement.setInt(1, idPaciente);
            statement.setString(2, examen);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(get(resultSet));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReportPacienteExamenesDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<ReportPacienteExamenes> getList(int idPaciente, String fecha1, String fecha2, String examen) {
        List<ReportPacienteExamenes> list = new ArrayList<>();
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(SELECT_FECHA_EXAMEN)) {
            statement.setInt(1, idPaciente);
            statement.setString(2, fecha1);
            statement.setString(3, fecha2);
            statement.setString(4, examen);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(get(resultSet));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReportPacienteExamenesDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    private ReportPacienteExamenes get(ResultSet resultSet) throws SQLException {
        return ReportPacienteExamenes.builder().
                solicitud(resultSet.getInt("solicitud")).
                fechaSolicitado(resultSet.getString("fecha_solicitado")).
                fechaFinalizada(resultSet.getString("fecha_finalizada")).
                examen(resultSet.getString("examen")).
                estado(resultSet.getString("estado")).
                laboratorio(resultSet.getString("nombre")).
                precio(resultSet.getDouble("precio")).build();
    }
}
