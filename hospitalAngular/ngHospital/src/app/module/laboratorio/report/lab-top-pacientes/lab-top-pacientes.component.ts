import { Component, OnInit } from '@angular/core';
import { LabTopPaciente } from '../../../../../entidad/model/reports/LabTopPacientes';
import { SesionService } from '../../../../service/sesion.service';
import { Usuario } from '../../../../../entidad/Usuario';
import { ReportLaboratorioService } from '../../../../service/reports/report-laboratorio/report-laboratorio.service';
import { DownloadLabService } from '../../../../service/download/download-laboratory/download-lab.service';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-lab-top-pacientes',
  templateUrl: './lab-top-pacientes.component.html',
  styleUrls: ['./lab-top-pacientes.component.css']
})
export class LabTopPacientesComponent implements OnInit {

  form = new FormGroup({
    fecha1: new FormControl(),
    fecha2: new FormControl()
  });

  user: Usuario;
  pacientes: LabTopPaciente[] = [];
  constructor(private sesion: SesionService, private reportService: ReportLaboratorioService, private donwload: DownloadLabService) {
    let userJson = localStorage.getItem('userLogin');
    this.user = userJson ? JSON.parse(userJson) : null;
    if (this.user) {
      this.reportService.getTopPacientes(this.user).subscribe(
        (list: LabTopPaciente[]) => {
          this.pacientes = list;
        }
      );
    }
  }

  ngOnInit(): void {
    this.sesion.validarSesion();
  }

  consultar() {
    this.reportService.getTopPacientesFechas(this.user, this.form.value.fecha1, this.form.value.fecha2).subscribe(
      (list: LabTopPaciente[]) => {
        this.pacientes = list;
      }
    );
    console.log(this.pacientes)
  }

  descargar() {
    this.donwload.dwonloadTopPaciente(this.pacientes).subscribe(
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

}
