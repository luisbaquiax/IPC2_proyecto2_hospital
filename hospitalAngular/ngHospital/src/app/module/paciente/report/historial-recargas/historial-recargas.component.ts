import { Component, OnInit } from '@angular/core';
import { SesionService } from '../../../../service/sesion.service';
import { HistorialRecargaService } from '../../../../service/reports/report-paciente/historial-recarga.service';
import { Recarga } from '../../../../../entidad/Recarga';
import { Usuario } from '../../../../../entidad/Usuario';
import { DownloadPacienteService } from '../../../../service/download/download-paciente/download-paciente.service';

@Component({
  selector: 'app-historial-recargas',
  templateUrl: './historial-recargas.component.html',
  styleUrls: ['./historial-recargas.component.css']
})
export class PacienteHistorialRecargasComponent implements OnInit {

  historial: Recarga[] = [];
  user: Usuario;

  constructor(private sesion: SesionService, private serviceReport: HistorialRecargaService, private dowload: DownloadPacienteService) {
    let userJson = localStorage.getItem('userLogin');
    this.user = userJson ? JSON.parse(userJson) : null;
    this.serviceReport.getRecargas(this.user).subscribe(
      (list: Recarga[]) => {
        this.historial = list;
      }
    );
  }

  ngOnInit(): void {
    this.sesion.validarSesion();
  }
  descargar() {
    this.dowload.dowloadRecargas(this.historial, this.user).subscribe(
      blob => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'Historial de recaragas.pdf';
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
        window.URL.revokeObjectURL(url);
        console.log('Todo bien')
      }, error => {
        console.log('falló')
        console.log(error)
      }
    );
  }
}
