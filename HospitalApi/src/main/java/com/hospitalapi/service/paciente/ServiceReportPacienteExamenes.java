/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.service.paciente;

import com.hospitalapi.data.modelDB.reportsDB.ReportPacienteExamenesDB;
import com.hospitalapi.model.Usuario;
import com.hospitalapi.model.reports.ReportPacienteExamenes;
import java.util.List;

/**
 *
 * @author luis
 */
public class ServiceReportPacienteExamenes {

    private ReportPacienteExamenesDB reportPacienteExamenesDB;

    public ServiceReportPacienteExamenes() {
        this.reportPacienteExamenesDB = new ReportPacienteExamenesDB();
    }

    public List<ReportPacienteExamenes> getList(Usuario user) {
        return this.reportPacienteExamenesDB.getList(user.getId());
    }

    public List<ReportPacienteExamenes> getListBetweenFecha(Usuario user, String fecha1, String fecha2) {
        return this.reportPacienteExamenesDB.getList(user.getId(), fecha1, fecha2);
    }

    public List<ReportPacienteExamenes> getListByExamen(Usuario user, String tipoExamen) {
        return this.reportPacienteExamenesDB.getList(user.getId(), tipoExamen);
    }
}
