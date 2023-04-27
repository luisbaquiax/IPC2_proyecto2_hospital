/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.service.users;

import com.hospitalapi.data.modelDB.UserDB;
import com.hospitalapi.model.Usuario;
import java.util.List;

/**
 *
 * @author luis
 */
public class UsuarioService {

    private UserDB userDB;

    public UsuarioService() {
        this.userDB = new UserDB();
    }

    public boolean insert(Usuario usuario) {
        return userDB.insert(usuario);
    }

    public boolean insertFromFile(Usuario usuario) {
        return this.userDB.insertFromFile(usuario);
    }
    
    public boolean update(Usuario usuario){
        return this.userDB.update(usuario);
    }

    public Usuario getUserByUsernamePassword(String username, String password) {
        return this.userDB.getUserByUsernamePassword(username, password);
    }
}
