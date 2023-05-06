/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.service.reports;

import com.hospitalapi.model.reports.MedicoReportEspecialidadesIngresos;
import com.hospitalapi.model.reports.MedicoReportPacientesIngresos;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author luis
 */
public class ServiceDownloadReportsMedico {

    public ServiceDownloadReportsMedico() {
    }

    public void reportTopPacientes(OutputStream stream, List<MedicoReportPacientesIngresos> list) throws JRException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("reportes/medico/topPacientes.jasper");
        JRDataSource source = new JRBeanCollectionDataSource(list);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, stream);
    }

    public void reportTopEspecialidades(OutputStream stream, List<MedicoReportEspecialidadesIngresos> list) throws JRException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("reportes/medico/topEspecialidades.jasper");
        JRDataSource source = new JRBeanCollectionDataSource(list);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, stream);
    }

}
