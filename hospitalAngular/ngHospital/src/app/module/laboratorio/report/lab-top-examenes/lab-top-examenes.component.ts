import { Component, OnInit } from '@angular/core';
import { SesionService } from '../../../../service/sesion.service';
import { Usuario } from '../../../../../entidad/Usuario';
import { ReportLaboratorioService } from '../../../../service/reports/report-laboratorio/report-laboratorio.service';
import { LabTopExaemns } from '../../../../../entidad/model/reports/LabTopExamens';

@Component({
  selector: 'app-lab-top-examenes',
  templateUrl: './lab-top-examenes.component.html',
  styleUrls: ['./lab-top-examenes.component.css']
})
export class LabTopExamenesComponent implements OnInit {

  user: Usuario;
  examens: LabTopExaemns[] = [];
  constructor(private sesion: SesionService, private serviceReport: ReportLaboratorioService) {
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

  ngOnInit(): void {
    this.sesion.validarSesion();
  }

  descargar() { }

}
