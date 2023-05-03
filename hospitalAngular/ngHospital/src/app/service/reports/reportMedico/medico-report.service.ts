import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../../../../entidad/Usuario';
import { MedicoReportEspecialidadesIngresos } from '../../../../entidad/model/reports/MedicoReportEspecialidadesIngresos';
import { MedicoReportPacientesIngresos } from '../../../../entidad/model/reports/MedicoReportPacientesIngresos';

@Injectable({
  providedIn: 'root'
})
export class MedicoReportService {

  url: string = "http://localhost:8080/HospitalApi/ServletControllerReportMedico?tarea=";

  constructor(private httpClient: HttpClient) { }

  public getTopEspecialidades(user: Usuario) {
    return this.httpClient.post<MedicoReportEspecialidadesIngresos[]>(this.url + "topEspecialidades", user);
  }
  public getTopEspecialidadesFecha(user: Usuario, fecha1: string, fecha2: string) {
    return this.httpClient.post<MedicoReportEspecialidadesIngresos[]>(this.url + "topEspecialidadesFechas&fecha1=" + fecha1 + "&fecha2=" + fecha2, user);
  }
  public getTopPaciente(user: Usuario) {
    return this.httpClient.post<MedicoReportPacientesIngresos[]>(this.url + "topPaciente", user);
  }
  public getTopPacienteFechas(user: Usuario, fecha1: string, fecha2: string) {
    return this.httpClient.post<MedicoReportPacientesIngresos[]>(this.url + "topPacienteFechas&fecha1=" + fecha1 + "&fecha2=" + fecha2, user);
  }
}
