/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apiresthospital.service.admin;

import com.apiresthospital.data.modelDB.SolicitudExamenDB;
import com.apiresthospital.model.SolicitudExamen;
import java.util.List;

/**
 *
 * @author luis
 */
public class ReportExamenesIngresosService {
    private SolicitudExamenDB solicitudExamenDB;

    public ReportExamenesIngresosService() {
        this.solicitudExamenDB = new SolicitudExamenDB();
    }
    
    public List<SolicitudExamen> getAll(){
        return this.solicitudExamenDB.getListSolicitdExamen();
    }
    
    public List<SolicitudExamen> getAllTimeInterval(String fecha1, String fecha2){
        return this.solicitudExamenDB.getListSolicitdExamen(fecha1, fecha2);
    }
}
