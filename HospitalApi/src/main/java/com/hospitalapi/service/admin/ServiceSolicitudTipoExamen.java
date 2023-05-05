/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.service.admin;

import com.hospitalapi.data.modelDB.SolicitudTipoExamenDB;
import com.hospitalapi.model.SolicitudTipoExamen;
import java.util.List;

/**
 *
 * @author luis
 */
public class ServiceSolicitudTipoExamen {

    private SolicitudTipoExamenDB solicitudTipoExamenDB;

    public ServiceSolicitudTipoExamen() {
        this.solicitudTipoExamenDB = new SolicitudTipoExamenDB();
    }

    public boolean insert(SolicitudTipoExamen solicitud) {
        return this.solicitudTipoExamenDB.insert(solicitud);
    }

    public boolean update(SolicitudTipoExamen solicitud) {
        return this.solicitudTipoExamenDB.update(solicitud);
    }

    public List<SolicitudTipoExamen> getList() {
        return this.solicitudTipoExamenDB.getListSolicitudes();
    }

}
