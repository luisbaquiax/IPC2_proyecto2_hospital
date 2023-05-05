/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.service.admin;

import com.hospitalapi.data.modelDB.HistorialPorcentajDB;
import com.hospitalapi.model.HistorialPorcentaje;
import com.hospitalapi.model.reports.HistorialInfo;
import com.hospitalapi.objects.Entidad;
import java.util.List;

/**
 *
 * @author luis
 */
public class HistorialPorcentajeService {

    private HistorialPorcentajDB historialPorcentajDB;

    public HistorialPorcentajeService() {
        this.historialPorcentajDB = new HistorialPorcentajDB();
    }

    public boolean insert(Object object) {
        actualizarTodos();
        return this.historialPorcentajDB.insert((HistorialPorcentaje) object);
    }

    public boolean update(Object object, String fechaFinal) {
        return this.historialPorcentajDB.update((HistorialPorcentaje) object, fechaFinal);
    }

    public List<HistorialPorcentaje> getAll() {
        return this.historialPorcentajDB.getListaHistorialPorcentaje();
    }

    public List<HistorialInfo> getInfo() {
        return this.historialPorcentajDB.getListInfo();
    }

    private void actualizarTodos() {
        for (HistorialPorcentaje historialPorcentaje : this.historialPorcentajDB.getListaHistorialPorcentaje()) {
            historialPorcentaje.setEstado(HistorialPorcentaje.ANTERIOR);
            this.historialPorcentajDB.update(historialPorcentaje, historialPorcentaje.getFechaFinal());
        }
    }

}
