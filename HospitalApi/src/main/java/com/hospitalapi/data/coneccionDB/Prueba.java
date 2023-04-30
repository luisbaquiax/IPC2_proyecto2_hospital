/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.data.coneccionDB;

import com.hospitalapi.data.modelDB.MedicoDB;
import com.hospitalapi.objects.datos.CargaDatos;
import com.hospitalapi.objects.datos.ContenidoArchivoJSON;
import com.hospitalapi.objects.datos.ProcesadorJSON;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.SQLException;

/**
 *
 * @author luis
 */
public class Prueba {

    public static void main(String[] args) throws Exception {
        ConeccionDB.getConnection();
        String[] pass = new String[]{"contra1", "contra2", "contra3", "contra4", "contra5", "contra6", "contra7"};
        MedicoDB medicoDB = new MedicoDB();
        medicoDB.insert(1000);
        ProcesadorJSON p = new ProcesadorJSON();
        ContenidoArchivoJSON contenidoArchivoJSON = new ContenidoArchivoJSON();
//        File file = new File("/home/luis/Escritorio/entrada_hospital.json");
//
//        p.procesarContendioJSON(contenidoArchivoJSON.getContenidioArchivoJSON(new BufferedReader(new FileReader(file))));
//        CargaDatos carga = new CargaDatos(p);
//        carga.subirDatos(p);
    }

}
