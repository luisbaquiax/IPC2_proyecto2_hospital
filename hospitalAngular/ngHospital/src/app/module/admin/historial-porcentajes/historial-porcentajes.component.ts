import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ReportAdminService } from '../../../service/report-admin.service';
import { HistorialPorcentaje } from '../../../../entidad/HistorialPorcentaje';
import { Usuario } from '../../../../entidad/Usuario';
import { SesionService } from '../../../service/sesion.service';

@Component({
  selector: 'app-historial-porcentajes',
  templateUrl: './historial-porcentajes.component.html',
  styleUrls: ['./historial-porcentajes.component.css']
})
export class HistorialPorcentajesComponent implements OnInit {

  historial: HistorialPorcentaje[] = [];
  user: Usuario;
  constructor(private router: Router, private serviceReport: ReportAdminService, private serviceSesion: SesionService) {
    this.user = new Usuario();
    this.obtenerLista();
  }

  ngOnInit(): void {
    this.serviceSesion.validarSesion();
  }

  private obtenerLista() {
    this.serviceReport.getAllHistorialPorcentajes().subscribe(
      (list: HistorialPorcentaje[]) => {
        this.historial = list;
      }
    );
  }
  descargar(){
    console.log('descargando');
  }
}
