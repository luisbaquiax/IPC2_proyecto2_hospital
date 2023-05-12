/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.service.medico;

import com.hospitalapi.data.modelDB.ResultadoConsultaDB;
import com.hospitalapi.model.ResultadoConsulta;
import java.util.List;

/**
 *
 * @author luis
 */
public class ServiceResultadosConsulta {

    private ResultadoConsultaDB resultadoConsultaDB;

    public ServiceResultadosConsulta() {
        this.resultadoConsultaDB = new ResultadoConsultaDB();
    }

    public boolean insert(ResultadoConsulta resultado) {
        return this.resultadoConsultaDB.insert(resultado);
    }

    public boolean update(ResultadoConsulta resultado) {
        return this.resultadoConsultaDB.update(resultado);
    }
    
    public ResultadoConsulta get(int examen, int consulta){
        return this.resultadoConsultaDB.get(consulta, examen);
    }

    public List<ResultadoConsulta> getList(int consulta) {
        return this.resultadoConsultaDB.getList(consulta);
    }

}
