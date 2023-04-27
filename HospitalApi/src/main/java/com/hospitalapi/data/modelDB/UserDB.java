/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.data.modelDB;

import com.hospitalapi.data.coneccionDB.ConeccionDB;
import com.hospitalapi.model.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author luis
 */
public class UserDB {

    private static final String INSERT_FROM_FILE = "INSERT INTO usuario(id,nombre,username,password,email,fecha_nacimiento,saldo,tipo,direccion,cui,telefono) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String INSERT = "INSERT INTO usuario(nombre,username,password,email,fecha_nacimiento,saldo,tipo,direccion,cui,telefono) VALUES(?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE usuario SET nombre = ?, username = ?, password = ?, email = ?, fecha_nacimiento = ?, saldo = ?, direccion = ?, cui = ?, telefono = ? WHERE id = ?";
    private static final String SELECT_BY_USERNAME_PASSWORD = "SELECT * FROM usuario WHERE username = ? AND password = ?";

    private ResultSet resultSet;

    public UserDB() {
    }

    /**
     * Insert a new user in the schema
     *
     * @param usuario
     * @return
     */
    public boolean insert(Usuario usuario) {
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(INSERT)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getUserName());
            statement.setString(3, usuario.getPassword());
            statement.setString(4, usuario.getEmail());
            statement.setString(5, usuario.getFechaNacimiento());
            statement.setDouble(6, usuario.getSaldo());
            statement.setString(7, usuario.getTipo());
            statement.setString(8, usuario.getDireccion());
            statement.setString(9, usuario.getCui());
            statement.setString(10, usuario.getTelefono());

            statement.executeUpdate();
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    /**
     * Insert a new user in the schema from file
     *
     * @param usuario
     * @return
     */
    public boolean insertFromFile(Usuario usuario) {
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(INSERT_FROM_FILE)) {
            statement.setInt(1, usuario.getId());
            statement.setString(2, usuario.getNombre());
            statement.setString(3, usuario.getUserName());
            statement.setString(4, usuario.getPassword());
            statement.setString(5, usuario.getEmail());
            statement.setString(6, usuario.getFechaNacimiento());
            statement.setDouble(7, usuario.getSaldo());
            statement.setString(8, usuario.getTipo());
            statement.setString(9, usuario.getDireccion());
            statement.setString(10, usuario.getCui());
            statement.setString(11, usuario.getTelefono());

            statement.executeUpdate();
            statement.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;

        }
    }

    /**
     * Update a user
     *
     * @param usuario
     * @return
     */
    public boolean update(Usuario usuario) {
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(UPDATE)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getUserName());
            statement.setString(3, usuario.getPassword());
            statement.setString(4, usuario.getEmail());
            statement.setString(5, usuario.getFechaNacimiento());
            statement.setDouble(6, usuario.getSaldo());
            statement.setString(7, usuario.getDireccion());
            statement.setString(8, usuario.getCui());
            statement.setString(9, usuario.getTelefono());
            statement.setInt(10, usuario.getId());

            statement.executeUpdate();
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    /**
     * Validate if exists user in the data base
     *
     * @param username
     * @param password
     * @return
     */
    public Usuario getUserByUsernamePassword(String username, String password) {
        Usuario user = null;
        try (PreparedStatement statement = ConeccionDB.getConnection().prepareStatement(SELECT_BY_USERNAME_PASSWORD)) {
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = getUser(resultSet);
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
        }
        return user;
    }

    private Usuario getUser(ResultSet resultSet) throws SQLException {
        return new Usuario(
                resultSet.getInt("id"),
                resultSet.getString("nombre"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getString("email"),
                resultSet.getString("fecha_nacimiento"),
                resultSet.getDouble("saldo"),
                resultSet.getString("tipo"),
                resultSet.getString("direccion"),
                resultSet.getString("telefono"),
                resultSet.getString("cui"));
    }

}
