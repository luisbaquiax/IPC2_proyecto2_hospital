/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.service.reports;

import com.hospitalapi.model.reports.ReportLabTopExamenes;
import com.hospitalapi.model.reports.ReportLabTopPacientes;
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
public class ServiceDownloadReportsLaboratory {

    public ServiceDownloadReportsLaboratory() {
    }

    public void reportTopPacientes(OutputStream stream, List<ReportLabTopPacientes> list) throws JRException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("reportes/laboratory/topPacientes.jasper");
        JRDataSource source = new JRBeanCollectionDataSource(list);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, stream);
    }

    public void reportTopExamenes(OutputStream stream, List<ReportLabTopExamenes> list) throws JRException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("reportes/laboratory/topExamenes.jasper");
        JRDataSource source = new JRBeanCollectionDataSource(list);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, stream);
    }
}
