import { Component, OnInit } from '@angular/core';
import { MedicoReportPacientesIngresos } from '../../../../../entidad/model/reports/MedicoReportPacientesIngresos';
import { SesionService } from '../../../../service/sesion.service';
import { Usuario } from '../../../../../entidad/Usuario';
import { MedicoReportService } from '../../../../service/reports/reportMedico/medico-report.service';
import { FormGroup, FormControl } from '@angular/forms';
import { DownloadMedicoService } from '../../../../service/download/download-medico/download-medico.service';

@Component({
  selector: 'app-report-top-pacientes',
  templateUrl: './report-top-pacientes.component.html',
  styleUrls: ['./report-top-pacientes.component.css']
})
export class ReportTopPacientesComponent implements OnInit {

  user: Usuario;
  total: number;
  pacientes: MedicoReportPacientesIngresos[] = [];

  form = new FormGroup({
    fecha1: new FormControl(),
    fecha2: new FormControl()
  });

  constructor(private sesion: SesionService, private reportService: MedicoReportService, private download: DownloadMedicoService) {
    this.user = new Usuario();
    this.total = 0;

    let stringUser = localStorage.getItem('userLogin');

    if (stringUser != null) {
      this.user = JSON.parse(stringUser);
      this.mostrarTodos();
    }
  }

  ngOnInit(): void {
    this.sesion.validarSesion();
  }

  mostrarTodos() {
    this.reportService.getTopPaciente(this.user).subscribe(
      (list: MedicoReportPacientesIngresos[]) => {
        this.pacientes = list;
        this.getTotal(this.pacientes);
      }
    );
  }
  realizarConsulta() {
    this.reportService.getTopPacienteFechas(this.user, this.form.value.fecha1, this.form.value.fecha2).subscribe(
      (list: MedicoReportPacientesIngresos[]) => {
        this.pacientes = list;
        this.getTotal(this.pacientes);
      }
    );
  }
  descargar() {
    this.download.downloadTopPacientes(this.pacientes).subscribe(
      blob => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'reporteTopPacientes.pdf';
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

  getTotal(pacientes: MedicoReportPacientesIngresos[]) {
    this.total = 0;
    this.pacientes.forEach((element: MedicoReportPacientesIngresos) => {
      this.total += element.gananciaMedico;
    });
  }

}
