/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.service.laboratorio;

import com.hospitalapi.data.modelDB.PrecioExamenDB;
import com.hospitalapi.data.modelDB.TipoExamenDB;
import com.hospitalapi.data.modelDB.UserDB;
import com.hospitalapi.model.PrecioExamen;
import com.hospitalapi.model.TipoExamen;
import com.hospitalapi.model.reports.ExamenesLaboratorio;
import java.util.List;

/**
 *
 * @author luis
 */
public class ServiceExamenes {

    private TipoExamenDB tipoExamenDB;
    private PrecioExamenDB precioExamenDB;

    public ServiceExamenes() {
        this.tipoExamenDB = new TipoExamenDB();
        this.precioExamenDB = new PrecioExamenDB();
    }

    public boolean insert(TipoExamen tipoExamen) {
        return this.tipoExamenDB.insertTipoExamen(tipoExamen);
    }

    private boolean insert(PrecioExamen precioExamen) {
        return this.precioExamenDB.insert(precioExamen);
    }

    /**
     *
     * @param precioExamen
     * @return
     */
    public boolean update(PrecioExamen precioExamen) {
        return this.precioExamenDB.update(precioExamen);
    }

    public void ingresarExamenesLaboratorio(List<ExamenesLaboratorio> lista, int idLaboratorio) {
        for (ExamenesLaboratorio examenesLaboratorio : lista) {
            for (TipoExamen tipos : this.tipoExamenDB.getTipoExamenes()) {
                if (examenesLaboratorio.getNombre().equals(tipos.getName())) {
                    insert(new PrecioExamen(
                            tipos.getId(),
                            idLaboratorio,
                            examenesLaboratorio.getPrecio()));
                    break;
                }
            }
        }
    }

    public List<TipoExamen> getDisponibles() {
        return this.tipoExamenDB.getTipoExamenes();
    }
}
