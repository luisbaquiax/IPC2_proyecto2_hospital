import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HistorialPorcentaje } from '../../../entidad/HistorialPorcentaje';
import { Observable } from 'rxjs';
import { HistorialInfo } from '../../../entidad/HistorialInfo';

@Injectable({
  providedIn: 'root'
})
export class PorcentajesService {

  private URL: string = "http://localhost:8080/HospitalApi/AdminController?tarea=";

  constructor(private httpClient: HttpClient) { }

  public getAll(): Observable<HistorialPorcentaje[]> {
    return this.httpClient.get<HistorialPorcentaje[]>(this.URL + "getAll");
  }

  public getInfo(): Observable<HistorialInfo[]> {
    return this.httpClient.get<HistorialInfo[]>(this.URL + "getInfo");
  }

  public inser(historial: HistorialPorcentaje): Observable<HistorialPorcentaje> {
    return this.httpClient.post<HistorialPorcentaje>(this.URL + "insert", historial);
  }

  public update(historial: HistorialPorcentaje): Observable<HistorialPorcentaje> {
    return this.httpClient.put<HistorialPorcentaje>(this.URL + "update", historial);
  }
}
