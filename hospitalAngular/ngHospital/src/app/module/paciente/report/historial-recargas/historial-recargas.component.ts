import { Component, OnInit } from '@angular/core';
import { SesionService } from '../../../../service/sesion.service';
import { HistorialRecargaService } from '../../../../service/reports/report-paciente/historial-recarga.service';
import { Recarga } from '../../../../../entidad/Recarga';
import { Usuario } from '../../../../../entidad/Usuario';

@Component({
  selector: 'app-historial-recargas',
  templateUrl: './historial-recargas.component.html',
  styleUrls: ['./historial-recargas.component.css']
})
export class PacienteHistorialRecargasComponent implements OnInit {

  historial: Recarga[] = [];
  user: Usuario;

  constructor(private sesion: SesionService, private serviceReport: HistorialRecargaService) {
    let userJson = localStorage.getItem('userLogin');
    this.user = userJson ? JSON.parse(userJson) : null;
    this.serviceReport.getRecargas(this.user).subscribe(
      (list: Recarga[])=>{
        this.historial = list;
      }
    );
   }

  ngOnInit(): void {
    this.sesion.validarSesion();
  }
}
