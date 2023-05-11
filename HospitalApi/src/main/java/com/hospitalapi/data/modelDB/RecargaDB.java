/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.data.modelDB;

import com.hospitalapi.data.coneccionDB.ConeccionDB;
import com.hospitalapi.model.Recarga;
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
public class RecargaDB {

    private static final String INSERT = "INSERT INTO recarga(monto,fecha_hora,paciente) VALUES(?,?,?)";
    private static final String SELECT_BY_PACIENTE = "SELECT * FROM recarga WHERE paciente = ?";

    private ResultSet resultSet;

    public RecargaDB() {
    }

    /**
     * Insert a new Recarga
     *
     * @param recarga
     * @return
     */
    public boolean insert(Recarga recarga) {
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(INSERT)) {
            statement.setDouble(1, recarga.getMonto());
            statement.setString(2, recarga.getFechaHora());
            statement.setInt(3, recarga.getIdPaciente());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RecargaDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * List of Recargas of pacient
     *
     * @param idPaciente
     * @return
     */
    public List<Recarga> getRecargas(int idPaciente) {
        List<Recarga> lista = new ArrayList<>();
        Recarga recarga = new Recarga();
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(SELECT_BY_PACIENTE)) {
            statement.setInt(1, idPaciente);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                recarga = get(resultSet);
                recarga.establecerFechaHora();
                lista.add(recarga);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(RecargaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    private Recarga get(ResultSet resultSet) throws SQLException {
        return Recarga.builder().
                id(resultSet.getInt("id")).
                monto(resultSet.getDouble("monto")).
                fechaHora(resultSet.getString("fecha_hora")).
                idPaciente(resultSet.getInt("paciente")).build();
    }
}
