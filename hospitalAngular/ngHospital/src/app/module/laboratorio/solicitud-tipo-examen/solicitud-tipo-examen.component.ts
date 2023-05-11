import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ExamenesLaboratorioService } from '../../../service/examenesLaboratorio/examenes-laboratorio.service';
import { TipoExamen } from '../../../../entidad/TipoExamen';
import { SolicitudService } from '../../../service/admin/solicitud.service';
import { SolicitudTipoExamen } from '../../../../entidad/SolicitudTipoExamen';

@Component({
  selector: 'app-solicitud-tipo-examen',
  templateUrl: './solicitud-tipo-examen.component.html',
  styleUrls: ['./solicitud-tipo-examen.component.css']
})
export class SolicitudTipoExamenComponent implements OnInit {

  form = new FormGroup({
    nombre: new FormControl(),
    descripcion: new FormControl()
  });
  nuevo: SolicitudTipoExamen
  exmenes: TipoExamen[] = [];
  constructor(private serviceExamen: ExamenesLaboratorioService, private serviceSolicitud: SolicitudService) {
    this.nuevo = new SolicitudTipoExamen();
    this.refreshlist();
  }

  ngOnInit(): void {
  }

  enviarSolicitud() {
    this.nuevo.nombre = this.form.value.nombre;
    this.nuevo.descripcion = this.form.value.descripcion;
    this.nuevo.estado = SolicitudTipoExamen.SOLICITADO;
    let existe: Boolean = false;
    for (let i = 0; i < this.exmenes.length; i++) {
      if (this.exmenes[i].name == this.nuevo.nombre) {
        existe = true;
        break;
      }
    }
    if (existe == false) {
      this.serviceSolicitud.inserSolicitudTipoExamen(this.nuevo).subscribe(
        reponse => {
          alert('Se ha enviado la solicitud.')
        }, error => {
          alert('No se pudo enviar la solictud, los sentimos.')
        }
      );
    }else{
      alert('El tipo de examen ya existe.')
    }

  }

  refreshlist() {
    this.serviceExamen.getExamenesDisponibles().subscribe(
      (list: TipoExamen[]) => {
        this.exmenes = list;
      }
    );
  }
}
