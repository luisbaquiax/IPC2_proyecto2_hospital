/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.data.modelDB;

import com.hospitalapi.data.coneccionDB.ConeccionDB;
import com.hospitalapi.model.ResultadoLaboratorio;
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
public class ResultadoLaboratorioDB {

    private static final String INSERT = "INSERT INTO resultados_laboratorio(id_solicitud,id_examen,nombre_archivo) VALUES(?,?,?)";

    private static final String SELECT = "SELECT r.id, id_solicitud, id_examen, nombre_archivo, e.nombre\n"
            + "FROM resultados_laboratorio r\n"
            + "INNER JOIN tipo_examen e\n"
            + "ON e.id = r.id_examen\n"
            + "WHERE id_solicitud = ?";

    private static final String SELECT_BY = "SELECT * FROM resultados_laboratorio WHERE id_solicitud = ? AND id_examen = ?";

    private static final String UPDATE = "UPDATE resultados_laboratorio SET nombre_archivo = ? WHERE id = ?";

    private ResultSet resultSet;

    public ResultadoLaboratorioDB() {
    }

    public boolean insert(ResultadoLaboratorio resultado) {
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(INSERT)) {
            statement.setInt(1, resultado.getIdSolicitud());
            statement.setInt(2, resultado.getIdExamen());
            statement.setString(3, resultado.getNombreArchivo());
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ResultadoLaboratorioDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean update(ResultadoLaboratorio resultado) {
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(UPDATE)) {
            statement.setString(1, resultado.getNombreArchivo());
            statement.setInt(2, resultado.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ResultadoLaboratorioDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ResultadoLaboratorio get(int solicitud, int examen) {
        ResultadoLaboratorio result = null;
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(SELECT_BY)) {
            statement.setInt(1, solicitud);
            statement.setInt(2, examen);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = ResultadoLaboratorio.builder().
                        id(resultSet.getInt("id")).
                        idSolicitud(resultSet.getInt("id_solicitud")).
                        idExamen(resultSet.getInt("id_examen")).
                        nombreArchivo(resultSet.getString("nombre_archivo")).
                        build();
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(ResultadoLaboratorioDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * List resultados de laboratorio
     *
     * @param solicitud
     * @return
     */
    public List<ResultadoLaboratorio> getList(int solicitud) {
        List<ResultadoLaboratorio> list = new ArrayList<>();
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(SELECT)) {
            statement.setInt(1, solicitud);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(ResultadoLaboratorio.builder().
                        id(resultSet.getInt("id")).
                        idSolicitud(resultSet.getInt("id_solicitud")).
                        idExamen(resultSet.getInt("id_examen")).
                        nombreArchivo(resultSet.getString("nombre_archivo")).
                        examen(resultSet.getString("nombre")).
                        build());
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(ResultadoLaboratorioDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
