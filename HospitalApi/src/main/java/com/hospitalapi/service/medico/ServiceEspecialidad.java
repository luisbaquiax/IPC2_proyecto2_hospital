/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.service.medico;

import com.hospitalapi.data.modelDB.EspecialidadDB;
import com.hospitalapi.model.Especialidad;
import java.util.List;

/**
 *
 * @author luis
 */
public class ServiceEspecialidad {
    private EspecialidadDB especialidadDB;

    public ServiceEspecialidad() {
        this.especialidadDB = new EspecialidadDB();
    }
    
    public List<Especialidad> getEspecialidades(){
        return this.especialidadDB.getEspecialidades();
    }
}
