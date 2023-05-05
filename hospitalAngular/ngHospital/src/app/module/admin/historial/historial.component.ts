import { Component, OnInit } from '@angular/core';
import { PorcentajesService } from '../../../service/admin/porcentajes.service';
import { HistorialInfo } from '../../../../entidad/HistorialInfo';
import { HistorialPorcentaje } from '../../../../entidad/HistorialPorcentaje';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-historial',
  templateUrl: './historial.component.html',
  styleUrls: ['./historial.component.css']
})
export class HistorialComponent implements OnInit {

  historialInfo: HistorialInfo[] = [];

  historialNuevo: HistorialPorcentaje;


  form = new FormGroup({
    porcentaje: new FormControl()
  });

  form2 = new FormGroup({
    nuevo: new FormControl()
  });
  constructor(private serviceHistorial: PorcentajesService) {
    this.historialNuevo = new HistorialPorcentaje();
    this.refrescarList();
  }

  ngOnInit(): void {
  }

  refrescarList() {
    this.serviceHistorial.getInfo().subscribe(
      (list: HistorialInfo[]) => {
        this.historialInfo = list;
      }
    );
  }

  usar() {
    this.refrescarList();
    this.historialNuevo.porcentaje = this.form.value.porcentaje;
    this.historialNuevo.estado = ""
    this.historialNuevo.fechaFinal = "";
    this.historialNuevo.fechaInicial = "";
    this.historialNuevo.id = 0;
    this.serviceHistorial.inser(this.historialNuevo).subscribe(
      response => {
        alert('Se ha hecho los cambios exitosamente.');
      }, error => {
        alert('No se pudo guardar los cambios, los sentimos.')
      }
    );
    this.historialNuevo = new HistorialPorcentaje();
    location.reload();
  }

  agregar() {
    this.refrescarList();
    this.historialNuevo.porcentaje = this.form2.value.nuevo;
    let ingresas: Boolean = true;
    for (let i = 0; i < this.historialInfo.length; i++) {
      if (this.historialNuevo.porcentaje === this.historialInfo[i].porcentaje) {
        alert('Este porcentaje ya existe, por favor selecciona para usarlo.');
        break;
      } else {
        this.historialNuevo.estado = ""
        this.historialNuevo.fechaFinal = "";
        this.historialNuevo.fechaInicial = "";
        this.historialNuevo.id = 0;
        this.serviceHistorial.inser(this.historialNuevo).subscribe(
          response => {
            alert('Se ha hecho los cambios exitosamente.');
          }, error => {
            alert('No se pudo guardar los cambios, los sentimos.')
          }
        );
      }
    }
    location.reload();
  }
}
