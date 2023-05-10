import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SolicitudExamen } from '../../../entidad/SolicitudExamen';
import { ExamenSolicitado } from '../../../entidad/ExamenSolicitado';
import { Observable } from 'rxjs';
import { ExamenesLaboratorio } from '../../../entidad/ExamenesLaboratorio';

@Injectable({
  providedIn: 'root'
})
export class SolicitudExamenService {

  private URL: string = "http://localhost:8080/HospitalApi/ServletControllerSolicitudExamen?tarea=";

  constructor(private HttpClient: HttpClient) { }

  public inserSolicitud(solicitud: SolicitudExamen): Observable<SolicitudExamen> {
    return this.HttpClient.post<SolicitudExamen>(this.URL + "insert", solicitud);
  }

  public insertExamenesSolicitud(examens: ExamenesLaboratorio[]): Observable<ExamenesLaboratorio[]> {
    return this.HttpClient.post<ExamenesLaboratorio[]>(this.URL + "insertExamenes", examens)
  }
}
