import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MedicoIngresos } from 'src/entidad/MedicoIngresos';
import { LaboratorioIngresos } from 'src/entidad/LaboratorioIngresos';
import { HistorialPorcentaje } from '../../../entidad/HistorialPorcentaje';
import { Consulta } from '../../../entidad/Consulta';
import { SolicitudExamen } from '../../../entidad/SolicitudExamen';

@Injectable({
  providedIn: 'root'
})
export class DownloadService {

  private URL: string = "http://localhost:8080/HospitalApi/ControlladorDowload?accion=";

  constructor(private HttpClient: HttpClient) { }

  public downTopMedicos() {
    return this.HttpClient.get(this.URL + "1", { responseType: 'blob' });
  }

  public downTopLabs() {
    return this.HttpClient.get(this.URL + "2", { responseType: 'blob' });
  }

  public downHistorialPorcentajes() {
    return this.HttpClient.get(this.URL + "3", { responseType: 'blob' });
  }

  public downReportConsultas(consultas: Consulta[]) {
    return this.HttpClient.post(this.URL + "1", consultas, { responseType: 'blob' });
  }

  public downReportExamenes(examenes: SolicitudExamen[]) {
    return this.HttpClient.post(this.URL + "2", examenes, { responseType: 'blob' });
  }
}
