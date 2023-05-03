/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.data.modelDB.reportsDB;

import com.hospitalapi.data.coneccionDB.ConeccionDB;
import com.hospitalapi.model.reports.MedicoReportEspecialidadesIngresos;
import com.hospitalapi.model.reports.MedicoReportPacientesIngresos;
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
public class MedicoReportEspecialidadesIngresosDB {

    private static final String SELECT = "SELECT SUM(ganancia_medico) AS ganancia_medico, COUNT(especialidad) AS consultas, e.nombre, e.id\n"
            + "FROM consulta c\n"
            + "INNER JOIN especialidad e\n"
            + "ON c.especialidad = e.id\n"
            + "WHERE c.medico = ?\n"
            + "GROUP BY especialidad\n"
            + "ORDER BY SUM(ganancia_medico) DESC\n"
            + "LIMIT 5";
    private static final String SELECT_BETWEEN_FECHA = "SELECT SUM(ganancia_medico) AS ganancia_medico, COUNT(especialidad) AS consultas, e.nombre, e.id\n"
            + "FROM consulta c\n"
            + "INNER JOIN especialidad e\n"
            + "ON c.especialidad = e.id\n"
            + "WHERE c.medico = ? AND fecha_creacion BETWEEN ? AND ?\n"
            + "GROUP BY especialidad\n"
            + "ORDER BY SUM(ganancia_medico) DESC\n"
            + "LIMIT 5";

    private ResultSet resultSet;

    public MedicoReportEspecialidadesIngresosDB() {
    }

    public List<MedicoReportEspecialidadesIngresos> getTopEspecialidadesByMedico(int idMedico) {
        List<MedicoReportEspecialidadesIngresos> list = new ArrayList<>();
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(SELECT)) {
            statement.setInt(1, idMedico);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(MedicoReportEspecialidadesIngresos.builder().
                        gananciaMedico(resultSet.getDouble("ganancia_medico")).
                        cantidadConsulta(resultSet.getInt("consultas")).
                        especialidad("nombre").
                        idEspecialidad(resultSet.getInt("id")).build());
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicoReportPacienteIngresosDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<MedicoReportEspecialidadesIngresos> getTopEspecialidadesByMedicoFechas(int idMedico, String fecha1, String fecha2) {
        List<MedicoReportEspecialidadesIngresos> list = new ArrayList<>();
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(SELECT_BETWEEN_FECHA)) {
            statement.setInt(1, idMedico);
            statement.setString(2, fecha1);
            statement.setString(3, fecha2);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(MedicoReportEspecialidadesIngresos.builder().
                        gananciaMedico(resultSet.getDouble("ganancia_medico")).
                        cantidadConsulta(resultSet.getInt("consultas")).
                        especialidad("nombre").
                        idEspecialidad(resultSet.getInt("id")).build());
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicoReportPacienteIngresosDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
