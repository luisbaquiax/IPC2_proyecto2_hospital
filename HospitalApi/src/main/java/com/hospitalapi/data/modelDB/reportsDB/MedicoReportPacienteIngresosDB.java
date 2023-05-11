/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.data.modelDB.reportsDB;

import com.hospitalapi.data.coneccionDB.ConeccionDB;
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
public class MedicoReportPacienteIngresosDB {

    private static final String SELECT = "SELECT SUM(ganancia_medico) AS ganancia_medico, COUNT(paciente) AS cantidad_consultas, u.nombre, u.id\n"
            + "FROM consulta c\n"
            + "INNER JOIN paciente p\n"
            + "ON c.paciente = p.id\n"
            + "INNER JOIN usuario u\n"
            + "ON p.id = u.id\n"
            + "WHERE c.medico = ?\n"
            + "GROUP BY paciente\n"
            + "ORDER BY SUM(ganancia_medico) DESC\n"
            + "LIMIT 5";

    private static final String SELECT_BETWEEN_FECHA = "SELECT SUM(ganancia_medico) AS ganancia_medico, COUNT(paciente) AS cantidad_consultas, u.nombre, u.id\n"
            + "FROM consulta c\n"
            + "INNER JOIN paciente p\n"
            + "ON c.paciente = p.id\n"
            + "INNER JOIN usuario u\n"
            + "ON p.id = u.id\n"
            + "WHERE c.medico = ? AND fecha_creacion BETWEEN ? AND ?\n"
            + "GROUP BY paciente\n"
            + "ORDER BY SUM(ganancia_medico) DESC\n"
            + "LIMIT 5";

    private ResultSet resultSet;

    public MedicoReportPacienteIngresosDB() {
    }

    /**
     * Top pacientes que ha generado mas ingresos
     *
     * @param idMedico
     * @return
     */
    public List<MedicoReportPacientesIngresos> getTopPacientesByMedico(int idMedico) {
        List<MedicoReportPacientesIngresos> list = new ArrayList<>();
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(SELECT)) {
            statement.setInt(1, idMedico);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(MedicoReportPacientesIngresos.builder().
                        gananciaMedico(resultSet.getDouble("ganancia_medico")).
                        cantidadConsulta(resultSet.getInt("cantidad_consultas")).
                        paciente("nombre").
                        idPaciente(resultSet.getInt("id")).build());
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicoReportPacienteIngresosDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Top pacientes que ha generado mas ingresos
     *
     * @param idMedico
     * @param fecha1
     * @param fecha2
     * @return
     */

    public List<MedicoReportPacientesIngresos> getTopPacientesByMedicoFechas(int idMedico, String fecha1, String fecha2) {
        List<MedicoReportPacientesIngresos> list = new ArrayList<>();
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(SELECT_BETWEEN_FECHA)) {
            statement.setInt(1, idMedico);
            statement.setString(2, fecha1);
            statement.setString(3, fecha2);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(MedicoReportPacientesIngresos.builder().
                        gananciaMedico(resultSet.getDouble("ganancia_medico")).
                        cantidadConsulta(resultSet.getInt("cantidad_consultas")).
                        paciente("nombre").
                        idPaciente(resultSet.getInt("id")).build());
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicoReportPacienteIngresosDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
