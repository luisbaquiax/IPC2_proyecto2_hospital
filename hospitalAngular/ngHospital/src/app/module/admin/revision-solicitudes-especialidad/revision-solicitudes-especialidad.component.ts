import { Component, OnInit } from '@angular/core';
import { SolicitudService } from 'src/app/service/admin/solicitud.service';
import { SolicitudEspecialidad } from '../../../../entidad/SolicitudEspecialidad';
import { Especialidad } from '../../../../entidad/Especialidad';

@Component({
  selector: 'app-revision-solicitudes-especialidad',
  templateUrl: './revision-solicitudes-especialidad.component.html',
  styleUrls: ['./revision-solicitudes-especialidad.component.css']
})
export class RevisionSolicitudesEspecialidadComponent implements OnInit {

  solicitudes: SolicitudEspecialidad[] = [];
  nuevo: Especialidad;

  constructor(private serviceSolicitud: SolicitudService) {
    this.nuevo = new Especialidad();
    this.listarSolicitudes();
  }

  ngOnInit(): void {
  }

  listarSolicitudes() {
    this.serviceSolicitud.getSolicitudesEspecialidad().subscribe(
      (list: SolicitudEspecialidad[]) => {
        this.solicitudes = list;
      }
    );
  }

  aceptar(solicitud: SolicitudEspecialidad, status: number) {
    if (status === 1) {
      solicitud.estado = SolicitudEspecialidad.ACEPTADO;
      this.nuevo.description = solicitud.descripcion;
      this.nuevo.name = solicitud.nombre;
      this.nuevo.id = 0;
      this.serviceSolicitud.updateSolicitudEspecialidad(solicitud).subscribe(
        resonse => {
          this.serviceSolicitud.insertNewEspecialidad(this.nuevo).subscribe(
            reponse => {
              alert('Se ha aceptado la solicitud');
            }, error => {
              console.log('falló: ' + error)
            }
          );
        }, error => {
          console.log('falló: ' + error)
        }
      );
    } else {
      solicitud.estado = SolicitudEspecialidad.RECHAZADO;
      this.serviceSolicitud.updateSolicitudEspecialidad(solicitud).subscribe(
        response => {
          alert('Se ha rechazado la solicitud')
        }, error => {
          console.log('falló: ' + error)
        }
      );
    }
    this.listarSolicitudes();
  }

}
