import { Injectable } from '@angular/core';
import { ExamenConsulta } from '../../../entidad/ExamenConsulta';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ExamenesConsultaService {

  private URL: string = "http://localhost:8080/HospitalApi/ServletControllerConsultasMedico?tarea=";

  constructor(private HttpClient: HttpClient) { }

  public insertExamenConsulta(examens: ExamenConsulta[]): Observable<ExamenConsulta[]> {
    return this.HttpClient.post<ExamenConsulta[]>(this.URL + "insertExamenConsulta", examens);
  }
}
