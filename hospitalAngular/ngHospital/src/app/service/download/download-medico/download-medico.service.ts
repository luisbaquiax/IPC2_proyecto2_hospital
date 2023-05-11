import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MedicoReportPacientesIngresos } from '../../../../entidad/model/reports/MedicoReportPacientesIngresos';
import { MedicoReportEspecialidadesIngresos } from '../../../../entidad/model/reports/MedicoReportEspecialidadesIngresos';

@Injectable({
  providedIn: 'root'
})
export class DownloadMedicoService {

  private URL: string = "http://localhost:8080/HospitalApi/ServletControllerDownloadMedico?accion=";

  constructor(private HttpClient: HttpClient) { }

  public downloadTopPacientes(pacientes: MedicoReportPacientesIngresos[]) {
    return this.HttpClient.post(this.URL + "1", pacientes, { responseType: 'blob' });
  }

  public downloadTopEspecialidades(especialidades: MedicoReportEspecialidadesIngresos[]) {
    return this.HttpClient.post(this.URL + "2", especialidades, { responseType: 'blob' });
  }
}
