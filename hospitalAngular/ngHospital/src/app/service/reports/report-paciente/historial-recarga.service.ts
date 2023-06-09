import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../../../../entidad/Usuario';
import { Recarga } from '../../../../entidad/Recarga';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HistorialRecargaService {
  urlRecarga: string = "http://localhost:8080/HospitalApi/ServletControllerRecarga?tarea=";

  constructor(private httpClient: HttpClient) { }

  public getRecargas(user: Usuario): Observable<Recarga[]> {
    return this.httpClient.post<Recarga[]>(this.urlRecarga + "recargas",user);
  }
  public insert(recarga: Recarga): Observable<Recarga> {
    return this.httpClient.post<Recarga>(this.urlRecarga + "insertar", recarga);
  }
}
