import { Component, OnInit } from '@angular/core';
import { ReportPacienteService } from '../../../../service/reports/report-paciente/report-paciente.service';
import { PacienteReportConsultas } from '../../../../../entidad/model/reports/PacienteReportConsultas';
import { Usuario } from '../../../../../entidad/Usuario';
import { SesionService } from '../../../../service/sesion.service';
import { ExamenesLaboratorioService } from '../../../../service/examenesLaboratorio/examenes-laboratorio.service';
import { TipoExamen } from '../../../../../entidad/TipoExamen';
import { FormGroup, FormControl } from '@angular/forms';
import { ServiceEspecialidadService } from '../../../../service/especialidad/service-especialidad.service';
import { Especialidad } from '../../../../../entidad/Especialidad';

@Component({
  selector: 'app-paciente-reporte-consultas',
  templateUrl: './paciente-reporte-consultas.component.html',
  styleUrls: ['./paciente-reporte-consultas.component.css']
})
export class PacienteReporteConsultasComponent implements OnInit {

  consultas: PacienteReportConsultas[] = [];
  user: Usuario;
  especialidades: Especialidad[] = [];

  form = new FormGroup({
    fecha1: new FormControl(),
    fecha2: new FormControl()
  });
  form2 = new FormGroup({
    especialidad: new FormControl()
  });

  form3 = new FormGroup({
    fechaA: new FormControl(),
    fechaB: new FormControl(),
    especialidadA: new FormControl()
  });

  constructor(private reportService: ReportPacienteService, private sesion: SesionService, private serviceLab: ExamenesLaboratorioService,
    private serviceEspecialida: ServiceEspecialidadService) {
    let stringUser = localStorage.getItem('userLogin')
    this.user = stringUser ? JSON.parse(stringUser) : null;
    this.mostrarTodos();
    this.serviceEspecialida.getEspecialidadesDisponibles().subscribe(
      (list: Especialidad[]) => {
        this.especialidades = list;
      }
    );
  }

  ngOnInit(): void {
    this.sesion.validarSesion();
  }

  mostrarTodos() {
    this.reportService.getConsultas(this.user).subscribe(
      (list: PacienteReportConsultas[]) => {
        this.consultas = list;
      }
    );
  }

  realizarConsulta() {
    this.reportService.getConsultasBetweenFecha(this.user, this.form.value.fecha1, this.form.value.fecha2).subscribe(
      (list: PacienteReportConsultas[]) => {
        this.consultas = list;
      }
    );
  }

  realizarConsultaByEspecialidad() {
    this.reportService.getConsultasByEspecialidad(this.user, this.form2.value.especialidad).subscribe(
      (list: PacienteReportConsultas[]) => {
        this.consultas = list;
      }
    );
  }
  consultarConsultasByDateAndEspecialidad() {
    this.reportService.getConsultasByFechaAndEspecialidad(
      this.user, this.form3.value.fechaA, this.form3.value.fechaB, this.form3.value.especialidadA).
      subscribe(
        (list: PacienteReportConsultas[]) => {
          this.consultas = list;
        }
      );
  }
  descargar() { }

}
