import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HistorialPorcentaje } from '../../entidad/HistorialPorcentaje';
import { Consulta } from '../../entidad/Consulta';
import { SolicitudExamen } from '../../entidad/SolicitudExamen';
import { MedicoIngresos } from '../../entidad/MedicoIngresos';
import { LaboratorioIngresos } from '../../entidad/LaboratorioIngresos';

@Injectable({
  providedIn: 'root'
})
export class ReportAdminService {
  url: string = "http://localhost:8080/HospitalApi/ReportAdminController?tarea=";
  constructor(private http: HttpClient) { }

  public getAllHistorialPorcentajes() {
    return this.http.get<HistorialPorcentaje[]>(this.url + "historialPorcentajes");
  }

  public getConsultas() {
    return this.http.get<Consulta[]>(this.url + "consultas");
  }
  public getConsultasFechas(fecha1: string, fecha2: string) {
    return this.http.get<Consulta[]>(this.url + "consultasFecha&fecha1=" + fecha1 + "&fecha2=" + fecha2);
  }

  public getExamenes() {
    return this.http.get<SolicitudExamen[]>(this.url + "examenes");
  }

  public getExamenesFecha(fecha1: string, fecha2: string) {
    return this.http.get<SolicitudExamen[]>(this.url + "examanesFecha&fecha1=" + fecha1 + "&fecha2=" + fecha2);
  }

  public getTopMedicos() {
    return this.http.get<MedicoIngresos[]>(this.url + "topMedicos");
  }
  
  public getTopLaboratorios() {
    return this.http.get<LaboratorioIngresos[]>(this.url + "topLaboratorios");
  }
}
