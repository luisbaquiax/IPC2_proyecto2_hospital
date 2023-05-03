import { Component, OnInit } from '@angular/core';
import { SolicitudExamen } from '../../../../entidad/SolicitudExamen';
import { SesionService } from '../../../service/sesion.service';
import { ReportAdminService } from '../../../service/report-admin.service';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-report-examenes',
  templateUrl: './report-examenes.component.html',
  styleUrls: ['./report-examenes.component.css']
})
export class ReportExamenesComponent implements OnInit {

  examenes: SolicitudExamen[] = [];
  form = new FormGroup({
    fecha1: new FormControl(),
    fecha2: new FormControl()
  });

  cantidad: number;
  total: number;

  constructor(private sesion: SesionService, private report: ReportAdminService) {
    this.cantidad = 0;
    this.total = 0;
    this.report.getExamenes().subscribe(
      (list: SolicitudExamen[]) => {
        this.examenes = list;
        this.resetearValores(this.examenes);
      }
    );
  }

  ngOnInit(): void {
    this.sesion.validarSesion();
  }

  realizarConsulta() {
    this.report.getExamenesFecha(this.form.value.fecha1, this.form.value.fecha2).subscribe(
      (list: SolicitudExamen[]) => {
        this.examenes = list;
        this.resetearValores(this.examenes);
      }
    );
  }
  mostrarTodos() {
    this.report.getExamenes().subscribe(
      (list: SolicitudExamen[]) => {
        this.examenes = list;
        this.resetearValores(this.examenes);
      }
    );
  }

  descargar() {
    console.log('examanes ' + this.examenes);
  }

  resetearValores(lista: SolicitudExamen[]) {
    this.cantidad = lista.length;
    this.total = 0
    for (let i = 0; i < lista.length; i++) {
      this.total += lista[i].gananciaAdmin;
    }
  }
}
