import { Component, OnInit } from '@angular/core';
import { Consulta } from '../../../../entidad/Consulta';
import { MedicoService } from '../../../service/medicoService/medico.service';
import { Usuario } from '../../../../entidad/Usuario';
import { SesionService } from '../../../service/sesion.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-medico-consulta-pendiente-revision',
  templateUrl: './medico-consulta-pendiente-revision.component.html',
  styleUrls: ['./medico-consulta-pendiente-revision.component.css']
})
export class MedicoConsultaPendienteRevisionComponent implements OnInit {

  consultas: Consulta[] = [];
  user: Usuario;

  constructor(private serviceMedico: MedicoService, private sesion: SesionService, private router: Router) {
    let userJson = localStorage.getItem('userLogin');
    this.user = userJson ? JSON.parse(userJson) : null;
    this.serviceMedico.getConsultasBy(this.user.id, 0, Consulta.PENDIENTE_REVISION).subscribe(
      (list: Consulta[]) => {
        this.consultas = list;
      }
    );
  }

  ngOnInit(): void {
    this.sesion.validarSesion();
  }

  revisarExamens(consulta: Consulta) {
    let consultaJson = JSON.stringify(consulta);
    localStorage.setItem('consultaRevision', consultaJson)
    this.router.navigate(['/medExamenesRevision']);
  }

}
