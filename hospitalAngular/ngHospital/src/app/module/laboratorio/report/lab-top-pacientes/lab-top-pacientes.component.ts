import { Component, OnInit } from '@angular/core';
import { LabTopPaciente } from '../../../../../entidad/model/reports/LabTopPacientes';
import { SesionService } from '../../../../service/sesion.service';
import { Usuario } from '../../../../../entidad/Usuario';
import { ReportLaboratorioService } from '../../../../service/reports/report-laboratorio/report-laboratorio.service';

@Component({
  selector: 'app-lab-top-pacientes',
  templateUrl: './lab-top-pacientes.component.html',
  styleUrls: ['./lab-top-pacientes.component.css']
})
export class LabTopPacientesComponent implements OnInit {

  user: Usuario;
  pacientes: LabTopPaciente[] = [];
  constructor(private sesion: SesionService, private reportService: ReportLaboratorioService) {
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

  descargar(){
    
  }

}
