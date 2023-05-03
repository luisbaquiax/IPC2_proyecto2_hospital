/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.service.paciente;

import com.hospitalapi.data.modelDB.RecargaDB;
import com.hospitalapi.model.Recarga;
import com.hospitalapi.model.Usuario;
import java.util.List;

/**
 *
 * @author luis
 */
public class ServiceHistorialRecarga {

    private RecargaDB recargaDB;

    public ServiceHistorialRecarga() {
        this.recargaDB = new RecargaDB();
    }

    
    public boolean insert(Recarga recarga) {
        return this.recargaDB.insert(recarga);
    }

    public List<Recarga> getList(Usuario user) {
        return this.recargaDB.getRecargas(user.getId());
    }
}
