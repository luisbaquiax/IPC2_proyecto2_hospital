import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../../../../entidad/Usuario';
import { Observable } from 'rxjs';
import { LabTopPaciente } from '../../../../entidad/model/reports/LabTopPacientes';
import { LabTopExaemns } from '../../../../entidad/model/reports/LabTopExamens';

@Injectable({
  providedIn: 'root'
})
export class ReportLaboratorioService {

  private URL: string = "http://localhost:8080/HospitalApi/ServletControllerReportLab?tarea="

  constructor(private httpClient: HttpClient) { }

  /**
   * Top pacientes que más ingresos generan
   * @param user 
   * @returns Pacientes
   */
  public getTopPacientes(user: Usuario): Observable<LabTopPaciente[]> {
    return this.httpClient.get<LabTopPaciente[]>(this.URL + "topPacientes&id=" + user.id);
  }

  public getTopPacientesFechas(user: Usuario, fecha1: string, fecha2: string): Observable<LabTopPaciente[]> {
    return this.httpClient.get<LabTopPaciente[]>(this.URL + "topPacientesFechas&id=" + user.id + "&fecha1=" + fecha1 + "&fecha2=" + fecha2);
  }

  /**
   * Top exámenes que más ingreso generan
   * @param user 
   * @returns Lista de top exámens
   */
  public getTopExamenes(user: Usuario): Observable<LabTopExaemns[]> {
    return this.httpClient.get<LabTopExaemns[]>(this.URL + "topExamenes&id=" + user.id)
  }

  public getTopExamenesFechas(user: Usuario, fecha1: string, fecha2: string): Observable<LabTopExaemns[]> {
    return this.httpClient.get<LabTopExaemns[]>(this.URL + "topExamenesFechas&id=" + user.id + "&fecha1=" + fecha1 + "&fecha2=" + fecha2);
  }
}

