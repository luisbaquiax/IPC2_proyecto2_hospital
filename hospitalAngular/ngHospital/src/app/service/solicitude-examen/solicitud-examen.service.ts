import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SolicitudExamen } from '../../../entidad/SolicitudExamen';
import { ExamenSolicitado } from '../../../entidad/ExamenSolicitado';
import { Observable } from 'rxjs';
import { ExamenesLaboratorio } from '../../../entidad/ExamenesLaboratorio';
import { ExamenTipoSolicitud } from '../../../entidad/ExamenTipoSolicitud';

@Injectable({
  providedIn: 'root'
})
export class SolicitudExamenService {

  private URL: string = "http://localhost:8080/HospitalApi/ServletControllerSolicitudExamen?tarea=";

  private URL2: string = "http://localhost:8080/HospitalApi/SevletControllerSolicitudExamenLab?tarea=";

  constructor(private HttpClient: HttpClient) { }

  public inserSolicitud(solicitud: SolicitudExamen): Observable<SolicitudExamen> {
    return this.HttpClient.post<SolicitudExamen>(this.URL + "insert", solicitud);
  }

  public updateSolicitud(solicitud: SolicitudExamen): Observable<SolicitudExamen> {
    return this.HttpClient.put<SolicitudExamen>(this.URL + "update", solicitud);
  }

  public insertExamenesSolicitud(examens: ExamenesLaboratorio[]): Observable<ExamenesLaboratorio[]> {
    return this.HttpClient.post<ExamenesLaboratorio[]>(this.URL + "insertExamenes", examens)
  }

  public getSolicitudBy(laboratorio: number, paciente: number, estado: string): Observable<SolicitudExamen[]> {
    return this.HttpClient.get<SolicitudExamen[]>(this.URL2 + "solicitudesPendiente&laboratorio="
      + laboratorio + "&paciente=" + paciente + "&estado=" + estado);
  }

  public getListExamensBySolicitud(solictud: number): Observable<ExamenTipoSolicitud[]> {
    return this.HttpClient.get<ExamenTipoSolicitud[]>(this.URL2 + "examenesSolicitud&id=" + solictud);
  }

}
