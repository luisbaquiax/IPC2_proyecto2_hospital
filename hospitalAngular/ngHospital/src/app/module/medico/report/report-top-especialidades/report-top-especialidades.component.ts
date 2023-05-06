import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MedicoReportEspecialidadesIngresos } from '../../../../../entidad/model/reports/MedicoReportEspecialidadesIngresos';
import { SesionService } from '../../../../service/sesion.service';
import { MedicoReportService } from '../../../../service/reports/reportMedico/medico-report.service';
import { Usuario } from '../../../../../entidad/Usuario';
import { DownloadMedicoService } from '../../../../service/download/download-medico/download-medico.service';

@Component({
  selector: 'app-report-top-especialidades',
  templateUrl: './report-top-especialidades.component.html',
  styleUrls: ['./report-top-especialidades.component.css']
})
export class ReportTopEspecialidadesComponent implements OnInit {

  form = new FormGroup({
    fecha1: new FormControl(),
    fecha2: new FormControl()
  });
  user: Usuario;
  total: number;
  especialidades: MedicoReportEspecialidadesIngresos[] = [];


  constructor(private sesion: SesionService, private serviceReport: MedicoReportService, private dowload: DownloadMedicoService) {
    this.total = 0;
    this.user = new Usuario();
    let userString = localStorage.getItem('userLogin');
    if (userString != null) {
      this.user = JSON.parse(userString);
      this.serviceReport.getTopEspecialidades(this.user).subscribe(
        (list: MedicoReportEspecialidadesIngresos[]) => {
          this.especialidades = list;
          this.getTotal(this.especialidades);
        }
      );;
    }
  }

  ngOnInit(): void {
    this.sesion.validarSesion();
  }

  realizarConsulta() {
    this.serviceReport.getTopEspecialidadesFecha(this.user, this.form.value.fecha1, this.form.value.fecha2)
      .subscribe(
        (list: MedicoReportEspecialidadesIngresos[]) => {
          this.especialidades = list;
          this.getTotal(this.especialidades);
        }
      );

  }
  mostrarTodos() {
    this.serviceReport.getTopEspecialidades(this.user).subscribe(
      (list: MedicoReportEspecialidadesIngresos[]) => {
        this.especialidades = list;
        this.getTotal(this.especialidades);
      }
    );
  }
  descargar() {
    console.log('Descargando...');
    this.dowload.downloadTopEspecialidades(this.especialidades).subscribe(
      blob => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'reporteTopEspecialidades.pdf';
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

  getTotal(especialidades: MedicoReportEspecialidadesIngresos[]) {
    this.total = 0;
    for (let i = 0; i < especialidades.length; i++) {
      this.total += especialidades[i].gananciaMedico;
    }
  }
}
