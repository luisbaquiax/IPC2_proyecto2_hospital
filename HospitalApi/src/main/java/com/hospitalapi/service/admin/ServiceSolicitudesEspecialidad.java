/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.service.admin;

import com.hospitalapi.data.modelDB.SolicitudEspecialidadDB;
import com.hospitalapi.model.SolicitudEspecialidad;
import java.util.List;

/**
 *
 * @author luis
 */
public class ServiceSolicitudesEspecialidad {

    private SolicitudEspecialidadDB solicitudEspecialidadDB;

    public ServiceSolicitudesEspecialidad() {
        this.solicitudEspecialidadDB = new SolicitudEspecialidadDB();
    }

    public boolean insert(SolicitudEspecialidad solicitud) {
        return this.solicitudEspecialidadDB.insert(solicitud);
    }

    public boolean update(SolicitudEspecialidad solicitud) {
        return this.solicitudEspecialidadDB.update(solicitud);
    }

    public List<SolicitudEspecialidad> getList() {
        return this.solicitudEspecialidadDB.getListSolicitudes();
    }
}
