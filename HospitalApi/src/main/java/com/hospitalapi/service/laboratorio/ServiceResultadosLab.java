/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.service.laboratorio;

import com.hospitalapi.data.modelDB.ExamenesSolicitudDB;
import com.hospitalapi.data.modelDB.ResultadoLaboratorioDB;
import com.hospitalapi.data.modelDB.SolicitudExamenDB;
import com.hospitalapi.model.ExamenSolicitado;
import com.hospitalapi.model.ResultadoLaboratorio;
import java.util.List;
import lombok.Getter;

/**
 *
 * @author luis
 */
@Getter
public class ServiceResultadosLab {

    private ResultadoLaboratorioDB resultadoLaboratorioDB;
    private SolicitudExamenDB solicitudExamenDB;
    private ExamenesSolicitudDB examenesSolicitudDB;

    public ServiceResultadosLab() {
        this.resultadoLaboratorioDB = new ResultadoLaboratorioDB();
        this.solicitudExamenDB = new SolicitudExamenDB();
        this.examenesSolicitudDB = new ExamenesSolicitudDB();
    }

    public boolean insert(ResultadoLaboratorio resultado) {
        return this.resultadoLaboratorioDB.insert(resultado);
    }

    public List<ResultadoLaboratorio> getResultados(int solicitud) {
        return this.resultadoLaboratorioDB.getList(solicitud);
    }

    public boolean updateExamenSolicitado(ExamenSolicitado examenSolicitado) {
        return this.examenesSolicitudDB.update(examenSolicitado);
    }

}
