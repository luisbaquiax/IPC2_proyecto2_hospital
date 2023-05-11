/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.data.modelDB;

import com.hospitalapi.data.coneccionDB.ConeccionDB;
import com.hospitalapi.model.ResultadoConsulta;
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
public class ResultadoConsultaDB {

    private static final String INSERT = "INSERT INTO resultados_consulta(id_consulta,id_examen,nombre_archivo) VALUES(?,?,?)";

    private static final String SELECT = "SELECT r.id, id_consulta, id_examen, nombre_archivo, t.nombre\n"
            + "FROM resultados_consulta r\n"
            + "INNER JOIN tipo_examen t \n"
            + "ON r.id_examen = t.id\n"
            + "WHERE id_consulta = ?";

    private static final String SELECT_BY = "SELECT * FROM resultados_consulta WHERE id_consulta = ? AND id_examen = ?";

    private static final String UPDATE = "UPDATE resultados_consulta SET nombre_archivo = ? WHERE id = ?";

    private ResultSet resultSet;

    public ResultadoConsultaDB() {
    }

    public boolean insert(ResultadoConsulta resultado) {
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(INSERT)) {
            statement.setInt(1, resultado.getIdConsulta());
            statement.setInt(2, resultado.getIdExamen());
            statement.setString(3, resultado.getNombreArchivo());
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ResultadoConsultaDB.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ResultadoConsultaDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ResultadoConsulta get(int consulta, int examen) {
        ResultadoConsulta result = null;
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(SELECT_BY)) {
            statement.setInt(1, consulta);
            statement.setInt(2, examen);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = ResultadoConsulta.builder().
                        id(resultSet.getInt("id")).
                        idConsulta(resultSet.getInt("id_consulta")).
                        idExamen(resultSet.getInt("id_examen")).
                        nombreArchivo(resultSet.getString("nombre_archivo")).
                        build();
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(ResultadoConsultaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * List resultados de laboratorio
     *
     * @param solicitud
     * @return
     */
    public List<ResultadoConsulta> getList(int solicitud) {
        List<ResultadoConsulta> list = new ArrayList<>();
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(SELECT)) {
            statement.setInt(1, solicitud);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(ResultadoConsulta.builder().
                        id(resultSet.getInt("id")).
                        idConsulta(resultSet.getInt("id_consulta")).
                        idExamen(resultSet.getInt("id_examen")).
                        nombreArchivo(resultSet.getString("nombre_archivo")).
                        examen(resultSet.getString("nombre")).
                        build());
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(ResultadoConsultaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
