/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.service.medico;

import com.hospitalapi.data.modelDB.ConsultaDB;
import com.hospitalapi.data.modelDB.ExamenConsultaDB;
import com.hospitalapi.model.Consulta;
import com.hospitalapi.model.ExamenConsulta;
import java.util.List;

/**
 *
 * @author luis
 */
public class ServiceConsultas {

    private ConsultaDB consultaDB;
    private ExamenConsultaDB examenConsultaDB;

    public ServiceConsultas() {
        this.consultaDB = new ConsultaDB();
        this.examenConsultaDB = new ExamenConsultaDB();
    }

    public List<Consulta> getConsultasAgendadas(int medico, String fecha1, String fecha2, String estado) {
        return this.consultaDB.getListConsultaAgendadas(medico, fecha1, fecha2, estado);
    }

    public List<Consulta> getConsultasBy(int medico, int paciente, String estado) {
        return this.consultaDB.getListConsultaAgendadas(medico, paciente, estado);
    }

    public boolean update(Consulta consulta) {
        return this.consultaDB.update(consulta);
    }

    public boolean insert(Consulta consulta) {
        return this.consultaDB.insert(consulta);
    }

    public boolean insert(ExamenConsulta examenConsulta) {
        return this.examenConsultaDB.insert(examenConsulta);
    }

    public List<ExamenConsulta> getList(int consulta) {
        return this.examenConsultaDB.getListExamenConsult(consulta);
    }

    public boolean updateExamen(ExamenConsulta examenConsulta) {
        return this.examenConsultaDB.update(examenConsulta);
    }
}
