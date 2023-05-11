import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../../../entidad/Usuario';
import { Observable } from 'rxjs';
import { HistorialMecioReport } from 'src/entidad/model/reports/HistorialMedicoReport';

@Injectable({
  providedIn: 'root'
})
export class HistorialMedicoService {

  url: string = "http://localhost:8080/HospitalApi/ServletControllerReportPaciente?tarea=";

  constructor(private httpClient: HttpClient) { }
  /**
   * Historial por paciente
   * @param user 
   * @returns 
   */
  public getHistorial(user: Usuario) {
    return this.httpClient.get<HistorialMecioReport[]>(this.url + "historialMedico&id=" + user.id);
  }
  /**
   * Historial por paciente en intervalo de tiempo
   * @param user 
   * @param fecha1 
   * @param fecha2 
   * @returns 
   */
  public getHistorialBetweenDate(user: Usuario, fecha1: string, fecha2: string): Observable<HistorialMecioReport[]> {
    return this.httpClient.get<HistorialMecioReport[]>(this.url + "historialMedicoDate&id=" + user.id + "&fecha1=" + fecha1 + "&fecha2=" + fecha2);
  }
  /**
   * Historial medico (que corresponde a un médico en particular) por paciente
   * @param user 
   * @param paciente 
   * @returns 
   */
  public getHistorialByMedico(user: Usuario, paciente: number): Observable<HistorialMecioReport[]> {
    return this.httpClient.get<HistorialMecioReport[]>(this.url + "historialMedicoByMedico&id=" + paciente + "&medico=" + user.id);
  }
  /**
   * Muestra el historial en el que el médico está implicado
   * @param user 
   * @returns 
   */
  public getHistorialMedico(user: Usuario): Observable<HistorialMecioReport[]> {
    return this.httpClient.get<HistorialMecioReport[]>(this.url + "historialByMedico&medico=" + user.id);
  }

}
