/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.service.admin;

import com.hospitalapi.data.modelDB.reportsDB.LaboratorioIngresosDB;
import com.hospitalapi.model.reports.LaboratorioIngresos;
import java.util.List;

/**
 *
 * @author luis
 */
public class TopLaboratorioService {

    private LaboratorioIngresosDB laboratorioIngresosDB;

    public TopLaboratorioService() {
        this.laboratorioIngresosDB = new LaboratorioIngresosDB();
    }

    public List<LaboratorioIngresos> getList() {
        return this.laboratorioIngresosDB.getListTopLaboratories();
    }
}
