/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.data.coneccionDB;

import com.hospitalapi.data.modelDB.HistorialPorcentajDB;
import com.hospitalapi.data.modelDB.UserDB;
import com.hospitalapi.data.modelDB.reportsDB.ReportLabTopExamenesDB;
import com.hospitalapi.data.modelDB.reportsDB.ReportLabTopPacientesDB;
import com.hospitalapi.objects.Encriptador;
import com.hospitalapi.objects.datos.ContenidoArchivoJSON;
import com.hospitalapi.objects.datos.ProcesadorEnradaJSON;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import javax.ejb.Local;
import org.joda.time.DateTime;

/**
 *
 * @author luis
 */
public class Prueba {

    public static void main(String[] args) throws Exception {
        String[] pass = new String[]{"contra1", "contra2", "contra3", "contra4", "contra5", "contra6", "contra7"};
        Encriptador encriptador = new Encriptador();
        System.out.println(encriptador.desencriptar("1Z1U4g19HcLKeL47jpIN9g=="));
        ProcesadorEnradaJSON p = new ProcesadorEnradaJSON();
        ContenidoArchivoJSON contenidoArchivoJSON = new ContenidoArchivoJSON();
        UserDB userDB = new UserDB();
        System.out.println(userDB.getUserFilterName("m"));
//        String json2 = "[{\"nombre\":\"Nombre tipo examen A\",\"precio\":11}]";d
//        Type listType2 = new TypeToken<ArrayList<ExamenesLaboratorio>>() {
//        }.getType();
//        ArrayList<ExamenesLaboratorio> users2 = new Gson().fromJson(json2, listType2);
//        System.out.println(Arrays.toString(users2.toArray()));
//        File file = new File("/home/luis/Escritorio/entrada_hospital.json");

    }

}
