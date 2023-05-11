/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.service.paciente;

import com.hospitalapi.data.modelDB.ExamenTipoSolicitudDB;
import com.hospitalapi.data.modelDB.ExamenesSolicitudDB;
import com.hospitalapi.data.modelDB.HistorialPorcentajDB;
import com.hospitalapi.data.modelDB.SolicitudExamenDB;
import com.hospitalapi.data.modelDB.TipoExamenDB;
import com.hospitalapi.data.modelDB.UserDB;
import com.hospitalapi.model.ExamenSolicitado;
import com.hospitalapi.model.ExamenTipoSolicitud;
import com.hospitalapi.model.HistorialPorcentaje;
import com.hospitalapi.model.SolicitudExamen;
import com.hospitalapi.model.TipoExamen;
import com.hospitalapi.model.Usuario;
import com.hospitalapi.model.reports.ExamenesLaboratorio;
import com.hospitalapi.objects.ListaTiposUsuario;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author luis
 */
public class ServiceSolicitudExamen {

    private SolicitudExamenDB solicitudExamenDB;
    private ExamenesSolicitudDB examenesSolicitudDB;
    private TipoExamenDB tipoExamenDB;
    private UserDB userDB;
    private HistorialPorcentajDB historialPorcentajDB;
    private ExamenTipoSolicitudDB examenTipoSolicitudDB;

    public ServiceSolicitudExamen() {
        this.solicitudExamenDB = new SolicitudExamenDB();
        this.examenesSolicitudDB = new ExamenesSolicitudDB();
        this.tipoExamenDB = new TipoExamenDB();
        this.userDB = new UserDB();
        this.historialPorcentajDB = new HistorialPorcentajDB();
        this.examenTipoSolicitudDB = new ExamenTipoSolicitudDB();
    }

    public void insertSolicitudExamen(SolicitudExamen solicitud) {
        solicitud.setFechaRealizada(LocalDate.now().toString());
        solicitud.setFechaSolicitado(LocalDate.now().toString());
        for (HistorialPorcentaje historialPorcentaje : this.historialPorcentajDB.getListaHistorialPorcentaje()) {
            if (historialPorcentaje.getEstado().equals(HistorialPorcentaje.ACTUAL)) {
                solicitud.setPorcentaje(historialPorcentaje.getPorcentaje());
                break;
            }
        }
        solicitud.setGananciaAdmin(solicitud.getCostoTotal() * solicitud.getPorcentaje());
        solicitud.setGananciaLab((1 - solicitud.getPorcentaje()) * solicitud.getCostoTotal());

        System.out.println("solictud ingresando: " + solicitud.toString());

        for (Usuario user : this.userDB.getAllUsers()) {
            if (user.getId() == solicitud.getIdPaciente()) {
                user.setSaldo(user.getSaldo() - solicitud.getCostoTotal());
                this.userDB.update(user);
                break;
            }
        }
        for (Usuario user : this.userDB.getAllUsers()) {
            if (user.getTipo().equals(ListaTiposUsuario.ADMIN)) {
                user.setSaldo(solicitud.getGananciaAdmin() + user.getSaldo());
                this.userDB.update(user);
                break;
            }
        }
        for (Usuario user : this.userDB.getAllUsers()) {
            if (user.getId() == solicitud.getIdLaboratorio()) {
                user.setSaldo(user.getSaldo() + solicitud.getGananciaLab());
                this.userDB.update(user);
                break;
            }
        }
        this.solicitudExamenDB.insert(solicitud);
    }

    public void ingresarExamenesSolicitud(List<ExamenesLaboratorio> list) {
        int ultimo = this.userDB.getUltimoId(SolicitudExamenDB.ULTIMO);
        for (ExamenesLaboratorio examenes : list) {
            ExamenSolicitado examen = new ExamenSolicitado(0, ultimo, examenes.getPrecio(), false);
            for (TipoExamen tipoExamene : this.tipoExamenDB.getTipoExamenes()) {
                if (examenes.getNombre().equals(tipoExamene.getName())) {
                    examen.setIdExamen(tipoExamene.getId());
                    break;
                }
            }
            this.examenesSolicitudDB.insert(examen);
        }
    }

    public List<SolicitudExamen> getListBy(int laboratorio, int paciente, String estado) {
        return this.solicitudExamenDB.getListSolicitdExamenBy(laboratorio, paciente, estado);
    }
    
    public List<ExamenTipoSolicitud> getListExmamenes(int solicitud){
        return this.examenTipoSolicitudDB.getListBySolicitud(solicitud);
    }
}
