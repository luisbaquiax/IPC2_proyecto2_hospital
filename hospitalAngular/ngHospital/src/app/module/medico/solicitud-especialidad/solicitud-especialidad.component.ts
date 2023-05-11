import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { SolicitudService } from 'src/app/service/admin/solicitud.service';
import { SolicitudEspecialidad } from '../../../../entidad/SolicitudEspecialidad';

@Component({
  selector: 'app-solicitud-especialidad',
  templateUrl: './solicitud-especialidad.component.html',
  styleUrls: ['./solicitud-especialidad.component.css']
})
export class SolicitudEspecialidadComponent implements OnInit {

  form = new FormGroup({
    nombre: new FormControl(),
    descripcion: new FormControl()
  });
  solicitud: SolicitudEspecialidad;
  constructor(private service: SolicitudService) {
    this.solicitud = new SolicitudEspecialidad();
  }

  ngOnInit(): void {
  }

  enviarSolicitud() {
    this.solicitud.nombre = this.form.value.nombre;
    this.solicitud.descripcion = this.form.value.descripcion;
    this.solicitud.estado = SolicitudEspecialidad.SOLICITADO;
    console.log(this.solicitud)
    this.service.inserSolicitudEspecialidad(this.solicitud).subscribe(
      response => {
        alert('Se ha enviado la solicitud con éxito.')
      }, error => {
        alert('No se pudo enviar la solictud, lo sentimos.')
      }
    );
  }
}
