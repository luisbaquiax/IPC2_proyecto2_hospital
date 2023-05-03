/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.service.admin;

import com.hospitalapi.data.modelDB.reportsDB.MedicoIngresosDB;
import com.hospitalapi.model.reports.MedicoIngresos;
import java.util.List;

/**
 *
 * @author luis
 */
public class TopMedicosService {

    private MedicoIngresosDB medicoIngresosDB;

    public TopMedicosService() {
        this.medicoIngresosDB = new MedicoIngresosDB();
    }

    public List<MedicoIngresos> getList() {
        return this.medicoIngresosDB.getListTopMedicos();
    }

}
