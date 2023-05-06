/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospitalapi.service.reports;

import com.hospitalapi.data.modelDB.HistorialPorcentajDB;
import com.hospitalapi.data.modelDB.reportsDB.LaboratorioIngresosDB;
import com.hospitalapi.data.modelDB.reportsDB.MedicoIngresosDB;
import com.hospitalapi.model.Consulta;
import com.hospitalapi.model.SolicitudExamen;
import com.hospitalapi.model.reports.MedicoIngresos;
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
public class ServiceDownloadReportsAdmin {

    private MedicoIngresosDB medicoIngresosDB;
    private LaboratorioIngresosDB laboratorioIngresosDB;
    private HistorialPorcentajDB historialPorcentajDB;

    public ServiceDownloadReportsAdmin() {
        this.medicoIngresosDB = new MedicoIngresosDB();
        this.laboratorioIngresosDB = new LaboratorioIngresosDB();
        this.historialPorcentajDB = new HistorialPorcentajDB();

    }

    public void reportTopMedicos(OutputStream stream) throws JRException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("reportes/topMedicos.jasper");
        List<MedicoIngresos> list = this.medicoIngresosDB.getListTopMedicos();
        JRDataSource source = new JRBeanCollectionDataSource(list);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, stream);

    }

    public void reportTopLaboratorio(OutputStream stream) throws JRException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("reportes/topLabs.jasper");

        JRDataSource source = new JRBeanCollectionDataSource(this.laboratorioIngresosDB.getListTopLaboratories());

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, stream);

    }

    public void reportHistorialPorcentaje(OutputStream stream) throws JRException {

        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("reportes/porcentajes.jasper");

        JRDataSource source = new JRBeanCollectionDataSource(this.historialPorcentajDB.getListaHistorialPorcentaje());

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, stream);

    }
    
    public void reportConsultas(OutputStream stream, List<Consulta> list) throws JRException {

        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("reportes/consultas.jasper");

        JRDataSource source = new JRBeanCollectionDataSource(list);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, stream);

    }
    
    public void reportExamenes(OutputStream stream, List<SolicitudExamen> list) throws JRException {

        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("reportes/examenes.jasper");

        JRDataSource source = new JRBeanCollectionDataSource(list);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, stream);

    }
    
    

    public double getTotal(List<MedicoIngresos> list) {
        double total = 0;
        for (MedicoIngresos medicoIngresos : list) {
            total += medicoIngresos.getGananciaApp();
        }
        return total;
    }

}
