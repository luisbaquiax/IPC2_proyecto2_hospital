import { Component, OnInit } from '@angular/core';
import { Consulta } from '../../../../entidad/Consulta';
import { MedicoService } from '../../../service/medicoService/medico.service';
import { AddUserService } from '../../../service/user/add-user.service';
import { Usuario } from '../../../../entidad/Usuario';
import { Router } from '@angular/router';

@Component({
  selector: 'app-paciente-consultas-pendiente-resultados',
  templateUrl: './paciente-consultas-pendiente-resultados.component.html',
  styleUrls: ['./paciente-consultas-pendiente-resultados.component.css']
})
export class PacienteConsultasPendienteResultadosComponent implements OnInit {

  consultas: Consulta[] =[];
  user: Usuario;
  constructor(private serviceConsulta: MedicoService, private serviceUser: AddUserService, private router: Router) {
    let userJson = localStorage.getItem('userLogin');
    this.user = userJson ? JSON.parse(userJson): null;

   }

  ngOnInit(): void {
    this.serviceConsulta.getConsultasBy(0, this.user.id, Consulta.EXAMEN_PENDIENTE).subscribe(
      (list: Consulta[])=>{
        this.consultas = list;
      }
    );
  }

  subirResultados(consulta: Consulta){
    localStorage.setItem('consultaPendienteExamen', JSON.stringify(consulta));
    this.router.navigate(['/pacienteUploadResultadoConsulta']);
  }

}
