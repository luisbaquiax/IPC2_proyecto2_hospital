import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ExamenesLaboratorio } from '../../../../entidad/ExamenesLaboratorio';
import { ExamenesLaboratorioService } from '../../../service/examenesLaboratorio/examenes-laboratorio.service';
import { TipoExamen } from '../../../../entidad/TipoExamen';
import { PrecioExamen } from '../../../../entidad/PrecioExamen';
import { Usuario } from '../../../../entidad/Usuario';

@Component({
  selector: 'app-lab-edit-examen',
  templateUrl: './lab-edit-examen.component.html',
  styleUrls: ['./lab-edit-examen.component.css']
})
export class LabEditExamenComponent implements OnInit {

  form = new FormGroup({
    precio: new FormControl()
  });

  nuevo!: ExamenesLaboratorio;
  modificando!: PrecioExamen;
  user: Usuario;

  constructor(private serviceLab: ExamenesLaboratorioService) {
    let userJson = localStorage.getItem('userLogin');
    this.user = userJson ? JSON.parse(userJson) : null;
    this.nuevo = new ExamenesLaboratorio();
    this.modificando = new PrecioExamen();
  }

  ngOnInit(): void {
    this.serviceLab.emiter.subscribe(
      (data: ExamenesLaboratorio) => {
        this.nuevo = data;
      }
    );
  }

  guardarCambios() {
    this.modificando.idExamen = 0;
    this.modificando.idLaboratorio = this.user.id;
    this.modificando.precio = this.form.value.precio;
    if (this.form.value.precio) {
      if (this.modificando.precio > 0) {
        this.serviceLab.updateExamenLaboratory(this.modificando, this.nuevo.nombre).subscribe(
          response => {
            alert('Se guardado los cambios con éxito.');
            location.reload();
          }, error => {
          }
        );
      } else {
        alert('El precio debe ser mayor a cero.')
      }

    }
  }
}
