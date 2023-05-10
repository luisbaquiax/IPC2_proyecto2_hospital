import { Component, OnInit } from '@angular/core';
import { Consulta } from '../../../../entidad/Consulta';
import { Usuario } from '../../../../entidad/Usuario';
import { SesionService } from '../../../service/sesion.service';
import { MedicoService } from '../../../service/medicoService/medico.service';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-medico-consultas-agendas',
  templateUrl: './medico-consultas-agendas.component.html',
  styleUrls: ['./medico-consultas-agendas.component.css']
})
export class MedicoConsultasAgendasComponent implements OnInit {

  consultas: Consulta[] = [];
  user: Usuario;

  form = new FormGroup({
    fecha: new FormControl()
  });

  consultaEnviado: Consulta;

  constructor(private sesion: SesionService, private serviceMedico: MedicoService, private router: Router) {
    this.user = new Usuario;
    this.consultaEnviado = new Consulta();
    let userJson = localStorage.getItem('userLogin');
    this.user = userJson ? JSON.parse(userJson) : null;
    this.getConsultas('hoy');

  }
  ngOnInit(): void {
    this.sesion.validarSesion();
  }

  getConsultas(fecha: string) {
    this.serviceMedico.getConsultasAgendadas(this.user, fecha).subscribe(
      (list: Consulta[]) => {
        this.consultas = list;
      }
    );
  }

  consultar() {
    console.log(this.form.value.fecha);
    this.getConsultas(this.form.value.fecha);
  }
  agregarExamenes(consulta: Consulta) {
    console.log('enviando consulta...')
    console.log(consulta)
    this.consultaEnviado = consulta;
    let json = JSON.stringify(this.consultaEnviado);
    localStorage.setItem('consultaEnviando', json);
    //this.serviceMedico.emmiter.emit(Object.assign({}, this.consultaEnviado));
    this.router.navigate(['medAddExamenesConsulta']);
  }

}
