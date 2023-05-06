import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { TipoExamen } from '../../../../../entidad/TipoExamen';
import { PacienteReportExamenes } from '../../../../../entidad/model/reports/PacienteReportExamenes';
import { Usuario } from '../../../../../entidad/Usuario';
import { SesionService } from '../../../../service/sesion.service';
import { ReportPacienteService } from '../../../../service/reports/report-paciente/report-paciente.service';
import { EspecialidadService } from '../../../../service/especialidad/especialidad.service';
import { ServiceEspecialidadService } from '../../../../service/especialidad/service-especialidad.service';
import { ExamenesLaboratorioService } from '../../../../service/examenesLaboratorio/examenes-laboratorio.service';
import { DownloadPacienteService } from '../../../../service/download/download-paciente/download-paciente.service';

@Component({
  selector: 'app-paciente-report-examenes',
  templateUrl: './paciente-report-examenes.component.html',
  styleUrls: ['./paciente-report-examenes.component.css']
})
export class PacienteReportExamenesComponent implements OnInit {

  tipoExamenes: TipoExamen[] = [];
  examenes: PacienteReportExamenes[] = [];
  user: Usuario;

  form = new FormGroup({
    fecha1: new FormControl(),
    fecha2: new FormControl()
  });
  form2 = new FormGroup({
    nombreExamen: new FormControl()
  });

  form3 = new FormGroup({
    fecha3: new FormControl(),
    fecha4: new FormControl(),
    tipoExamen: new FormControl()
  });

  constructor(private sesion: SesionService, private serviceRerport: ReportPacienteService, 
    private serviceLab: ExamenesLaboratorioService, private download: DownloadPacienteService) {
    this.user = new Usuario();
    let userJson = localStorage.getItem('userLogin');
    this.user = userJson ? JSON.parse(userJson) : null;
    this.mostrarTodos();
    this.serviceLab.getExamenesDisponibles().subscribe(
      (list: TipoExamen[]) => {
        this.tipoExamenes = list;
      }
    );
  }

  ngOnInit(): void {
    this.sesion.validarSesion();
  }
  mostrarTodos() {
    this.serviceRerport.getExamens(this.user).subscribe(
      (list: PacienteReportExamenes[]) => {
        this.examenes = list;
      }
    );
  }
  realizarConsulta() {
    this.serviceRerport.getExamensBetweenFecha(this.user, this.form.value.fecha1, this.form.value.fecha2).subscribe(
      (list: PacienteReportExamenes[]) => {
        this.examenes = list;
      }
    );
  }
  realizarConsultaByExamen() {
    console.log('examen seleccionado: ' + this.form2.value.nombreExamen)
    this.serviceRerport.getExamensByExamen(this.user, this.form2.value.nombreExamen).subscribe(
      (list: PacienteReportExamenes[]) => {
        this.examenes = list;
        console.log('estos ' + this.examenes)
      }
    );
  }
  consultaDateExamen() {
    console.log(this.form3.value.tipoExamen);
    this.serviceRerport.getExamensByDateAndExamen(this.user, this.form3.value.fecha3, this.form3.value.fecha4, this.form3.value.tipoExamen).subscribe(
      (list: PacienteReportExamenes[])=>{
        this.examenes = list;
      }
    );
  }
  descargar() { 
    this.download.dowlaodExamenes(this.examenes).subscribe(
      blob=>{
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'Reporte de exámenes.pdf';
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
