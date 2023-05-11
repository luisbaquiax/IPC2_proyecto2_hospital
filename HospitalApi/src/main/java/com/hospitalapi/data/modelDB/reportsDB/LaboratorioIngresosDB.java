/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.data.modelDB.reportsDB;

import com.hospitalapi.data.coneccionDB.ConeccionDB;
import com.hospitalapi.model.reports.LaboratorioIngresos;
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
public class LaboratorioIngresosDB {

    private static final String TOP_LABORATORIO
            = "SELECT SUM(ganancia_lab) AS total_lab, SUM(ganancia_admin) AS total_app, COUNT(laboratorio) AS cantidad, u.nombre, u.id\n"
            + "FROM solicitud_examen s\n"
            + "INNER JOIN laboratorio p\n"
            + "ON p.id = s.laboratorio\n"
            + "INNER JOIN usuario u\n"
            + "ON u.id = p.id\n"
            + "GROUP BY laboratorio\n"
            + "ORDER BY SUM(ganancia_lab) DESC\n"
            + "LIMIT 5;";

    private ResultSet resultSet;

    public LaboratorioIngresosDB() {
    }

    /**
     * List of laboratorios
     *
     * @return
     */
    public List<LaboratorioIngresos> getListTopLaboratories() {
        List<LaboratorioIngresos> list = new ArrayList<>();
        try (PreparedStatement statemenet = ConeccionDB.getConnection().prepareStatement(TOP_LABORATORIO)) {
            resultSet = statemenet.executeQuery();
            while (resultSet.next()) {
                list.add(LaboratorioIngresos.builder().
                        cantidad(resultSet.getInt("cantidad")).
                        totalApp(resultSet.getDouble("total_app")).
                        nombre("nombre").
                        totalPorExamenes(resultSet.getDouble("total_lab")).
                        id(resultSet.getInt("id")).
                        build());
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicoIngresosDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
