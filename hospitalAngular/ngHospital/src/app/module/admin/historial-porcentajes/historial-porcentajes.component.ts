import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ReportAdminService } from '../../../service/report-admin.service';
import { HistorialPorcentaje } from '../../../../entidad/HistorialPorcentaje';
import { Usuario } from '../../../../entidad/Usuario';
import { SesionService } from '../../../service/sesion.service';
import { DownloadService } from '../../../service/download/download.service';

@Component({
  selector: 'app-historial-porcentajes',
  templateUrl: './historial-porcentajes.component.html',
  styleUrls: ['./historial-porcentajes.component.css']
})
export class HistorialPorcentajesComponent implements OnInit {

  historial: HistorialPorcentaje[] = [];
  user: Usuario;
  constructor(private router: Router, private serviceReport: ReportAdminService, private serviceSesion: SesionService, private down: DownloadService) {
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
    this.down.downHistorialPorcentajes().subscribe(
      blob=>{
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'informe.pdf';
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
        window.URL.revokeObjectURL(url);
        console.log('Todo bien')
      },error=>{  
        console.log('falló')
        console.log(error)
      }
    );
  }
}
