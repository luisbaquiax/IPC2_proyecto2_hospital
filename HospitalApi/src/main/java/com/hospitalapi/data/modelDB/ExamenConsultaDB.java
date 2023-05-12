/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.data.modelDB;

import com.hospitalapi.data.coneccionDB.ConeccionDB;
import com.hospitalapi.model.ExamenConsulta;
import com.hospitalapi.model.SolicitudEspecialidad;
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
public class ExamenConsultaDB {

    private static final String INSERT = "INSERT INTO examenes_consulta(examen,consulta,estado) VALUES(?,?,?)";
    private static final String UPDATE = "UPDATE examenes_consulta SET estado = ? WHERE examen = ? AND consulta = ?";
    private static final String SELECT
            = "SELECT examen, consulta, estado, t.nombre\n"
            + "FROM examenes_consulta e\n"
            + "INNER JOIN tipo_examen t \n"
            + "ON t.id = e.examen WHERE consulta = ?";

    private ResultSet resultSet;

    public ExamenConsultaDB() {
    }

    /**
     *
     * @param examenConsulta
     * @return
     */
    public boolean insert(ExamenConsulta examenConsulta) {
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(INSERT)) {
            statement.setInt(1, examenConsulta.getIdExamen());
            statement.setInt(2, examenConsulta.getIndConsulta());
            statement.setBoolean(3, examenConsulta.isEstado());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ExamenConsultaDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     *
     * @param examenConsulta
     * @return
     */
    public boolean update(ExamenConsulta examenConsulta) {
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(UPDATE)) {
            statement.setBoolean(1, examenConsulta.isEstado());
            statement.setInt(2, examenConsulta.getIdExamen());
            statement.setInt(3, examenConsulta.getIndConsulta());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ExamenConsultaDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * query: <br>
     * SELECT examen, consulta, estado, t.nombre<br>
     * FROM examenes_consulta e<br>
     * INNER JOIN tipo_examen t <br>
     * ON t.id = e.examen WHERE consulta = ?<br>
     *
     * @param consulta
     * @return
     */
    public List<ExamenConsulta> getListExamenConsult(int consulta) {
        List<ExamenConsulta> list = new ArrayList<>();
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(SELECT)) {
            statement.setInt(1, consulta);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(ExamenConsulta.builder().
                        idExamen(resultSet.getInt("examen")).
                        indConsulta(resultSet.getInt("consulta")).
                        estado(resultSet.getBoolean("estado")).
                        nombre(resultSet.getString("nombre")).build());
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(ExamenConsultaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
