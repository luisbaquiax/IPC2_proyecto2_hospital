/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.service.paciente;

import com.hospitalapi.data.modelDB.reportsDB.HistorialMedicoDB;
import com.hospitalapi.model.reports.HistorialMedico;
import java.util.List;

/**
 *
 * @author luis
 */
public class ServiceHistorialMedico {

    private HistorialMedicoDB historialMedicoDB;

    public ServiceHistorialMedico() {
        this.historialMedicoDB = new HistorialMedicoDB();
    }

    public List<HistorialMedico> getList(int paciente) {
        return this.historialMedicoDB.getByPaciente(paciente);
    }

    public List<HistorialMedico> getList(int paciente, String fecha1, String fecha2) {
        return this.historialMedicoDB.getByPaciente(paciente, fecha1, fecha2);
    }

    public List<HistorialMedico> getList(int paciente, int medico) {
        return this.historialMedicoDB.getByPaciente(paciente, medico);
    }
    
     public List<HistorialMedico> getListByMedico(int medico) {
        return this.historialMedicoDB.getByMedico( medico);
    }

}
