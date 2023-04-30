/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.service.medico;

import com.hospitalapi.data.modelDB.EspecialidadesMedicoDB;
import com.hospitalapi.model.Usuario;
import com.hospitalapi.model.reports.EspecialidadesMedico;
import java.util.List;
import lombok.ToString;

/**
 *
 * @author luis
 */
@ToString
public class ServiceMedicoEspecialidad {

    private EspecialidadesMedicoDB especialidadesMedicoDB;

    public ServiceMedicoEspecialidad() {
        this.especialidadesMedicoDB = new EspecialidadesMedicoDB();
    }

    public List<EspecialidadesMedico> getEspecialidadsByMedico(Usuario medico) {
        return this.especialidadesMedicoDB.getEspecialidadsByMedico(medico);
    }

}
