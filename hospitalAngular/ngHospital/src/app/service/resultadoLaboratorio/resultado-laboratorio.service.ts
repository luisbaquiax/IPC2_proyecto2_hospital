import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ResultadosLaboratorio } from '../../../entidad/ResultadoLaboratorio';

@Injectable({
  providedIn: 'root'
})
export class ResultadoLaboratorioService {

  private URL: string = "http://localhost:8080/HospitalApi/ServletControllerResultadosLab?tarea=";
  constructor(private httpClient: HttpClient) { }

  public getResultados(solicitud: number) {
    return this.httpClient.get<ResultadosLaboratorio[]>(this.URL + "resultados&id=" + solicitud)
  }
}
