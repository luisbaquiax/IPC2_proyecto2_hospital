/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.service.users;

import com.hospitalapi.objects.datos.CargaDatos;
import com.hospitalapi.objects.datos.ProcesadorJSON;
import org.json.simple.parser.ParseException;

/**
 *
 * @author luis
 */
public class DataService {

    private ProcesadorJSON procesadorJSON;
    private CargaDatos cargaDatos;

    public DataService() {
        this.procesadorJSON = new ProcesadorJSON();
        this.cargaDatos = new CargaDatos(procesadorJSON);
    }

    public void procesarDatos(String content) throws ParseException {
        this.procesadorJSON.procesarContendioJSON(content);
    }

    public void subirDatos() {
        this.cargaDatos.subirDatos(procesadorJSON);
    }
}
