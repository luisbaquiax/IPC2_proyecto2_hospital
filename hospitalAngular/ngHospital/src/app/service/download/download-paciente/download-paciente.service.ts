import { Injectable } from '@angular/core';
import { HistorialMecioReport } from '../../../../entidad/model/reports/HistorialMedicoReport';
import { HttpClient } from '@angular/common/http';
import { Recarga } from '../../../../entidad/Recarga';
import { Observable } from 'rxjs';
import { PacienteReportConsultas } from '../../../../entidad/model/reports/PacienteReportConsultas';
import { PacienteReportExamenes } from '../../../../entidad/model/reports/PacienteReportExamenes';
import { Usuario } from '../../../../entidad/Usuario';

@Injectable({
  providedIn: 'root'
})
export class DownloadPacienteService {

  private URL: string = "http://localhost:8080/HospitalApi/ServletControllerDownloadPaciente?accion=";

  constructor(private HttpClient: HttpClient) { }

  public dowloadHistorialMedico(historial: HistorialMecioReport[]): Observable<Blob> {
    return this.HttpClient.post(this.URL + "1", historial, { responseType: 'blob' });
  }

  public dowloadRecargas(recargas: Recarga[], user: Usuario): Observable<Blob> {
    return this.HttpClient.post(this.URL + "2&id="+user.id, recargas, { responseType: 'blob' })
  }

  public dowloadConsultas(consultas: PacienteReportConsultas[]): Observable<Blob> {
    return this.HttpClient.post(this.URL + "3", consultas, { responseType: 'blob' });
  }

  public dowlaodExamenes(examenes: PacienteReportExamenes[]): Observable<Blob> {
    return this.HttpClient.post(this.URL + "4", examenes, { responseType: 'blob' });
  }
}
