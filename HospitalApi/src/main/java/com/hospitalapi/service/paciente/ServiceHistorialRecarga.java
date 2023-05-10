/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.service.paciente;

import com.hospitalapi.data.modelDB.RecargaDB;
import com.hospitalapi.data.modelDB.UserDB;
import com.hospitalapi.model.Recarga;
import com.hospitalapi.model.Usuario;
import java.util.List;

/**
 *
 * @author luis
 */
public class ServiceHistorialRecarga {

    private RecargaDB recargaDB;
    private UserDB userDB;

    public ServiceHistorialRecarga() {
        this.recargaDB = new RecargaDB();
        this.userDB = new UserDB();
    }

    public boolean insert(Recarga recarga) {
        if (recargaDB.insert(recarga)) {
            for (Usuario user : this.userDB.getAllUsers()) {
                if(recarga.getIdPaciente()==user.getId()){
                    user.setSaldo(user.getSaldo()+recarga.getMonto());
                    this.userDB.update(user);
                    break;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public List<Recarga> getList(Usuario user) {
        return this.recargaDB.getRecargas(user.getId());
    }
}
