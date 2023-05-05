/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hospitalapi.service.laboratorio;

import com.hospitalapi.data.modelDB.reportsDB.ReportLabTopExamenesDB;
import com.hospitalapi.data.modelDB.reportsDB.ReportLabTopPacientesDB;
import com.hospitalapi.model.reports.ReportLabTopExamenes;
import com.hospitalapi.model.reports.ReportLabTopPacientes;
import java.util.List;

/**
 *
 * @author luis
 */
public class ServiceReportLaboratorio {

    private ReportLabTopExamenesDB reportLabTopExamenesDB;
    private ReportLabTopPacientesDB reportLabTopPacientesDB;

    public ServiceReportLaboratorio() {
        this.reportLabTopExamenesDB = new ReportLabTopExamenesDB();
        this.reportLabTopPacientesDB = new ReportLabTopPacientesDB();
    }

    public List<ReportLabTopExamenes> getListTopExamenes(int idLab) {
        return this.reportLabTopExamenesDB.getList(idLab);
    }

    public List<ReportLabTopPacientes> getListTopPaciente(int idLab) {
        return this.reportLabTopPacientesDB.getList(idLab);
    }

}
