/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.service.paciente;

import com.hospitalapi.data.modelDB.reportsDB.ReportePacienteConsultasDB;
import com.hospitalapi.model.Usuario;
import com.hospitalapi.model.reports.ReportPacienteConsultas;
import java.util.List;

/**
 *
 * @author luis
 */
public class ServiceReporPacienteConsultas {

    private ReportePacienteConsultasDB reportePacienteConsultasDB;

    public ServiceReporPacienteConsultas() {
        this.reportePacienteConsultasDB = new ReportePacienteConsultasDB();
    }

    public List<ReportPacienteConsultas> getList(Usuario user) {
        return this.reportePacienteConsultasDB.getList(user.getId());
    }

    public List<ReportPacienteConsultas> getListBetweenFecha(Usuario user, String fecha1, String fecha2) {
        return this.reportePacienteConsultasDB.getList(user.getId(), fecha1, fecha2);
    }

    public List<ReportPacienteConsultas> getListByEspecialidad(Usuario user, String especialidad) {
        return this.reportePacienteConsultasDB.getList(user.getId(), especialidad);
    }
}
