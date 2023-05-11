import { EventEmitter, Injectable, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../../../entidad/Usuario';
import { Consulta } from '../../../entidad/Consulta';

@Injectable({
  providedIn: 'root'
})
export class MedicoService {

  private URL: string = "http://localhost:8080/HospitalApi/ServletControllerConsultasMedico?tarea=";

  @Output()
  public emmiter: EventEmitter<Consulta> = new EventEmitter();

  constructor(private HttpClient: HttpClient) { }

  public getConsultasAgendadas(user: Usuario, fecha: string) {
    return this.HttpClient.get<Consulta[]>(this.URL + "agendadas&id=" + user.id + "&fecha=" + fecha);
  }
  public getConsultasBy(medicoID: number, pacienteID: number, estado: string) {
    return this.HttpClient.get<Consulta[]>(this.URL + "agendadaBy&medico=" + medicoID + "&paciente=" + pacienteID + "&estado=" + estado);
  }

  public updateConsulta(consulta: Consulta) {
    return this.HttpClient.put<Consulta>(this.URL+"update",consulta);
  }
}
