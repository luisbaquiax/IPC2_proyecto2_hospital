import { Component, OnInit } from '@angular/core';
import { SesionService } from '../../../../service/sesion.service';
import { Usuario } from '../../../../../entidad/Usuario';
import { ReportLaboratorioService } from '../../../../service/reports/report-laboratorio/report-laboratorio.service';
import { LabTopExaemns } from '../../../../../entidad/model/reports/LabTopExamens';
import { DownloadLabService } from '../../../../service/download/download-laboratory/download-lab.service';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-lab-top-examenes',
  templateUrl: './lab-top-examenes.component.html',
  styleUrls: ['./lab-top-examenes.component.css']
})
export class LabTopExamenesComponent implements OnInit {

  user: Usuario;
  examens: LabTopExaemns[] = [];

  form = new FormGroup(
    {
      fecha1: new FormControl(),
      fecha2: new FormControl()
    }
  );

  constructor(private sesion: SesionService, private serviceReport: ReportLaboratorioService, private download: DownloadLabService) {
    let userJson = localStorage.getItem('userLogin');
    this.user = userJson ? JSON.parse(userJson) : null;
    if (this.user) {
      this.serviceReport.getTopExamenes(this.user).subscribe(
        (list: LabTopExaemns[]) => {
          this.examens = list;
        }
      );
    }
  }

  consultar() {
    this.serviceReport.getTopExamenesFechas(this.user, this.form.value.fecha1, this.form.value.fecha2).subscribe(
      (list: LabTopExaemns[]) => {
        this.examens = list;
      }
    );
    console.log(this.examens);
  }

  ngOnInit(): void {
    this.sesion.validarSesion();
  }

  descargar() {
    console.log('Descargando');
    this.download.dwonloadTopExamen(this.examens).subscribe(
      blob => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'reporteTopExamenes.pdf';
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
