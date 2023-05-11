import { Component, OnInit } from '@angular/core';
import { SolicitudExamenService } from '../../../service/solicitude-examen/solicitud-examen.service';
import { SolicitudExamen } from '../../../../entidad/SolicitudExamen';
import { Usuario } from '../../../../entidad/Usuario';
import { Router } from '@angular/router';

@Component({
  selector: 'app-paciente-revision-examenes',
  templateUrl: './paciente-revision-examenes.component.html',
  styleUrls: ['./paciente-revision-examenes.component.css']
})
export class PacienteRevisionExamenesComponent implements OnInit {

  solicitudes: SolicitudExamen[]=[];
  user: Usuario;
  constructor(private serviceSolicitud: SolicitudExamenService, private router: Router) {
    let userJSON = localStorage.getItem('userLogin');
    this.user = userJSON ?  JSON.parse(userJSON): null;

   }

  ngOnInit(): void {
    this.serviceSolicitud.getSolicitudBy(0,this.user.id,SolicitudExamen.FINALIZADA).subscribe(
      (list: SolicitudExamen[])=>{
        this.solicitudes = list;
      }
    );
  }

  ver(solicitud: SolicitudExamen){
    localStorage.setItem('solicitudEnviado', JSON.stringify(solicitud));
    this.router.navigate(['/pacienteResultadosLab'])
  }

}
