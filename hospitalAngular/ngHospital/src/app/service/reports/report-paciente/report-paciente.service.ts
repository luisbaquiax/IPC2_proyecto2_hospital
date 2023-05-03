import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../../../../entidad/Usuario';
import { Observable } from 'rxjs';
import { PacienteReportConsultas } from 'src/entidad/model/reports/PacienteReportConsultas';
import { PacienteReportExamenes } from '../../../../entidad/model/reports/PacienteReportExamenes';

@Injectable({
  providedIn: 'root'
})
export class ReportPacienteService {

  url: string = "http://localhost:8080/HospitalApi/ServletControllerReportPaciente?tarea="

  constructor(private httpClient: HttpClient) { }

  public getHistorialMedico(user: Usuario) {
  }

  public getConsultas(user: Usuario): Observable<PacienteReportConsultas[]> {
    return this.httpClient.post<PacienteReportConsultas[]>(this.url + "consultas", user);
  }
  public getConsultasBetweenFecha(user: Usuario, fecha1: string, fecha2: string): Observable<PacienteReportConsultas[]> {
    return this.httpClient.post<PacienteReportConsultas[]>(
      this.url + "consultasFechas&fecha1=" + fecha1 + "&fecha2=" + fecha2, user);
  }
  public getConsultasByEspecialidad(user: Usuario, especialidad: String): Observable<PacienteReportConsultas[]> {
    return this.httpClient.post<PacienteReportConsultas[]>(this.url + "consultasEspecialidad&especialidad=" + especialidad, user);
  }
  public getExamens(user: Usuario): Observable<PacienteReportExamenes[]> {
    return this.httpClient.post<PacienteReportExamenes[]>(this.url + "examenes", user);
  }
  public getExamensBetweenFecha(user: Usuario, fecha1: string, fecha2: string): Observable<PacienteReportExamenes[]> {
    return this.httpClient.post<PacienteReportExamenes[]>(
      this.url + "examenesFechas&fecha1=" + fecha1 + "&fecha2=" + fecha2, user);
  }
  public getExamensByExamen(user: Usuario, tipoExamen: string): Observable<PacienteReportExamenes[]> {
    return this.httpClient.post<PacienteReportExamenes[]>(this.url + "examenesTipoExamen&=" + tipoExamen, user);
  }
}
