import { Injectable } from '@angular/core';
import { LabTopPaciente } from '../../../../entidad/model/reports/LabTopPacientes';
import { HttpClient } from '@angular/common/http';
import { LabTopExaemns } from '../../../../entidad/model/reports/LabTopExamens';

@Injectable({
  providedIn: 'root'
})
export class DownloadLabService {

  private URL: string = "http://localhost:8080/HospitalApi/ServletControllerDownloadLab?accion=";

  constructor(private HttpClient: HttpClient) { }

  public dwonloadTopPaciente(pacientes: LabTopPaciente[]) {
    return this.HttpClient.post(this.URL + "1", pacientes, { responseType: 'blob' });
  }

  public dwonloadTopExamen(examens: LabTopExaemns[]) {
    return this.HttpClient.post(this.URL + "2", examens, { responseType: 'blob' });
  }
}
