/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.service.reports;

import com.hospitalapi.model.Recarga;
import com.hospitalapi.model.reports.HistorialMedico;
import com.hospitalapi.model.reports.ReportPacienteConsultas;
import com.hospitalapi.model.reports.ReportPacienteExamenes;
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
public class ServiceDownloadReportsPaciente {

    public ServiceDownloadReportsPaciente() {
    }

    public void reportHistorialMedico(OutputStream stream, List<HistorialMedico> list) throws JRException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("reportes/paciente/historialMedico.jasper");
        JRDataSource source = new JRBeanCollectionDataSource(list);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, stream);
    }

    public void reportHistorialRecargas(OutputStream stream, List<Recarga> list) throws JRException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("reportes/paciente/recargas.jasper");
        JRDataSource source = new JRBeanCollectionDataSource(list);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, stream);
    }

    public void reportConsultas(OutputStream stream, List<ReportPacienteConsultas> list) throws JRException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("reportes/paciente/consultas.jasper");
        JRDataSource source = new JRBeanCollectionDataSource(list);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, stream);
    }

    public void reportExamenes(OutputStream stream, List<ReportPacienteExamenes> list) throws JRException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("reportes/paciente/examenes.jasper");
        JRDataSource source = new JRBeanCollectionDataSource(list);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, stream);
    }
}
