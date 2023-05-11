import { Component, OnInit } from '@angular/core';
import { SolicitudExamen } from '../../../../entidad/SolicitudExamen';
import { SolicitudService } from '../../../service/admin/solicitud.service';
import { SolicitudTipoExamen } from '../../../../entidad/SolicitudTipoExamen';
import { TipoExamen } from '../../../../entidad/TipoExamen';

@Component({
  selector: 'app-revision-solicitudes-examen',
  templateUrl: './revision-solicitudes-examen.component.html',
  styleUrls: ['./revision-solicitudes-examen.component.css']
})
export class RevisionSolicitudesExamenComponent implements OnInit {

  solicitudes: SolicitudTipoExamen[] = [];
  nuevo: TipoExamen;

  constructor(private serviceSolicitud: SolicitudService) {
    this.nuevo = new TipoExamen();
    this.setList();
  }

  ngOnInit(): void {
  }

  setList() {
    this.serviceSolicitud.getSolicitudesExamen().subscribe(
      (list: SolicitudTipoExamen[]) => {
        this.solicitudes = list;
      }
    );
  }

  aceptar(solicitud: SolicitudTipoExamen, status: number) {
    if (status == 1) {
      solicitud.estado = SolicitudTipoExamen.ACEPTADO;
      this.nuevo.description = solicitud.descripcion;
      this.nuevo.name = solicitud.nombre;
      console.log(solicitud);
      this.serviceSolicitud.updateSolicitudExamen(solicitud).subscribe(
        response => {
          this.serviceSolicitud.insertNewTipoExamen(this.nuevo).subscribe(
            response => {
              alert('Se ha aceptado la solicitud.');
            }, error => {
              console.log('falló ' + error)
            }
          );
        }, error => {
          console.log('falló ' + error)
        }
      );
    } else {
      solicitud.estado = SolicitudTipoExamen.RECHAZADO;
      this.serviceSolicitud.updateSolicitudEspecialidad(solicitud).subscribe(
        response => {
          alert('Se ha rechazado la solictud.')
        }, error => {
          console.log(error);
        }
      );
    }

    this.setList();
  }
}
