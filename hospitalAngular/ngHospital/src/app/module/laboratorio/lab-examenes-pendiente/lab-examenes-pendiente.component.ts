import { Component, OnInit } from '@angular/core';
import { SolicitudExamen } from '../../../../entidad/SolicitudExamen';
import { ExamenesLaboratorioService } from '../../../service/examenesLaboratorio/examenes-laboratorio.service';
import { SolicitudExamenService } from '../../../service/solicitude-examen/solicitud-examen.service';
import { Usuario } from '../../../../entidad/Usuario';
import { Router } from '@angular/router';

@Component({
  selector: 'app-lab-examenes-pendiente',
  templateUrl: './lab-examenes-pendiente.component.html',
  styleUrls: ['./lab-examenes-pendiente.component.css']
})
export class LabExamenesPendienteComponent implements OnInit {

  solicitudes: SolicitudExamen[] = [];
  user: Usuario;
  constructor(private serviceLab: ExamenesLaboratorioService, private serviceSolicitud: SolicitudExamenService,
    private router: Router) {
    let userJSON = localStorage.getItem('userLogin');
    this.user = userJSON ? JSON.parse(userJSON) : null;
  }

  ngOnInit(): void {
    this.serviceSolicitud.getSolicitudBy(this.user.id, 0, SolicitudExamen.PENDIENTE).subscribe(
      (list: SolicitudExamen[]) => {
        this.solicitudes = list;
      }
    );
  }

  revisarExamens(solicitud: SolicitudExamen) {
    localStorage.setItem('solicitud',JSON.stringify(solicitud));
    this.router.navigate(['/labExamenesSolicitud']);
   }

}
