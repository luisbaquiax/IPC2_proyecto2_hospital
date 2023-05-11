/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.service.medico;

import com.hospitalapi.data.modelDB.reportsDB.MedicoReportEspecialidadesIngresosDB;
import com.hospitalapi.data.modelDB.reportsDB.MedicoReportPacienteIngresosDB;
import com.hospitalapi.model.Usuario;
import com.hospitalapi.model.reports.MedicoReportEspecialidadesIngresos;
import com.hospitalapi.model.reports.MedicoReportPacientesIngresos;
import java.util.List;

/**
 *
 * @author luis
 */
public class ServiceReportMedico {

    private MedicoReportPacienteIngresosDB medicoReportPacienteIngresosDB;
    private MedicoReportEspecialidadesIngresosDB medicoReportEspecialidadesIngresosDB;

    public ServiceReportMedico() {
        this.medicoReportPacienteIngresosDB = new MedicoReportPacienteIngresosDB();
        this.medicoReportEspecialidadesIngresosDB = new MedicoReportEspecialidadesIngresosDB();
    }

    public List<MedicoReportPacientesIngresos> getTopPacientes(Usuario usuario) {
        return this.medicoReportPacienteIngresosDB.getTopPacientesByMedico(usuario.getId());
    }

    public List<MedicoReportPacientesIngresos> getTopPacientes(Usuario usuario, String fecha1, String fecha2) {
        return this.medicoReportPacienteIngresosDB.getTopPacientesByMedicoFechas(usuario.getId(), fecha1, fecha2);
    }

    public List<MedicoReportEspecialidadesIngresos> getTopEscpecialidades(Usuario user) {
        return this.medicoReportEspecialidadesIngresosDB.getTopEspecialidadesByMedico(user.getId());
    }

    public List<MedicoReportEspecialidadesIngresos> getTopEscpecialidades(Usuario user, String fecha1, String fecha2) {
        return this.medicoReportEspecialidadesIngresosDB.getTopEspecialidadesByMedicoFechas(user.getId(), fecha1, fecha2);
    }
}
