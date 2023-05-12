import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ResultadoConsulta } from '../../../entidad/ResultadoConsulta';

@Injectable({
  providedIn: 'root'
})
export class ResultadoConsultaService {

  private URL: string = "http://localhost:8080/HospitalApi/ServletControllerResultadoConsulta?tarea=";
  constructor(private httpClient: HttpClient) { }

  public getResultadoConsulta(idConsulta: number): Observable<ResultadoConsulta[]>{
    return this.httpClient.get<ResultadoConsulta[]>(this.URL+"resultadosConsulta&consulta="+idConsulta);
  }
}
