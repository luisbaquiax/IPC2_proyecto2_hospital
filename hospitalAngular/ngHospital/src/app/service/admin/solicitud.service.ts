import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SolicitudEspecialidad } from '../../../entidad/SolicitudEspecialidad';
import { SolicitudTipoExamen } from '../../../entidad/SolicitudTipoExamen';
import { TipoExamen } from '../../../entidad/TipoExamen';
import { Especialidad } from '../../../entidad/Especialidad';

@Injectable({
  providedIn: 'root'
})
export class SolicitudService {

  private URL: string = "http://localhost:8080/HospitalApi/ServletControllerSolicitudes?tarea=";

  constructor(private HttpClient: HttpClient) { }

  public getSolicitudesEspecialidad(): Observable<SolicitudEspecialidad[]> {
    return this.HttpClient.get<SolicitudEspecialidad[]>(this.URL + "listEspecialidad");
  }

  public getSolicitudesExamen(): Observable<SolicitudTipoExamen[]> {
    return this.HttpClient.get<SolicitudTipoExamen[]>(this.URL + "listExamen");
  }

  public inserSolicitudEspecialidad(solicitd: SolicitudEspecialidad) {
    return this.HttpClient.post<SolicitudEspecialidad>(this.URL + "insertSolicitudEspecialidad", solicitd);
  }

  public inserSolicitudTipoExamen(solicitd: SolicitudTipoExamen) {
    return this.HttpClient.post<SolicitudTipoExamen>(this.URL + "insertSolicitudExamen", solicitd);
  }

  public insertNewEspecialidad(especialidad: Especialidad) {
    return this.HttpClient.post<Especialidad>(this.URL + "insertNuevaEspecialidad", especialidad);
  }

  public insertNewTipoExamen(examen: TipoExamen) {
    return this.HttpClient.post<TipoExamen>(this.URL + "insertNuevoExamen", examen);
  }

  public updateSolicitudEspecialidad(solicitd: SolicitudEspecialidad) {
    return this.HttpClient.put<SolicitudEspecialidad>(this.URL + "updateSolicitudEspecialidad", solicitd);
  }

  public updateSolicitudExamen(solicitd: SolicitudTipoExamen) {
    return this.HttpClient.put<SolicitudTipoExamen>(this.URL + "updateSolicitudExamen", solicitd);
  }

}
