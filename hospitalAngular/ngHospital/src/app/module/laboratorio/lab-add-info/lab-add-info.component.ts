import { Component, OnInit } from '@angular/core';
import { ExamenesLaboratorio } from '../../../../entidad/ExamenesLaboratorio';
import { TipoExamen } from '../../../../entidad/TipoExamen';
import { ExamenesLaboratorioService } from '../../../service/examenesLaboratorio/examenes-laboratorio.service';
import { Usuario } from '../../../../entidad/Usuario';
import { Router } from '@angular/router';

@Component({
  selector: 'app-lab-add-info',
  templateUrl: './lab-add-info.component.html',
  styleUrls: ['./lab-add-info.component.css']
})
export class LabAddInfoComponent implements OnInit {

  misExamens: ExamenesLaboratorio[] = [];
  examenesDisponibles: TipoExamen[] = [];
  examenNuevo: ExamenesLaboratorio;
  user: Usuario;

  examenes: ExamenesLaboratorio[] = [];

  constructor(private serviceExamenes: ExamenesLaboratorioService, private router: Router) {
    this.examenNuevo = new ExamenesLaboratorio();
    this.user = new Usuario();
    let userLogin = localStorage.getItem('userLogin');
    this.user = userLogin ? JSON.parse(userLogin) : null;
    this.refresh();

  }

  ngOnInit(): void {
    this.serviceExamenes.getExamenesDisponibles().subscribe((lista) => { this.examenesDisponibles = lista });
  }
  agregar() {
    let contenido: Boolean = false;
    for (let i = 0; i < this.examenes.length; i++) {
      if (this.examenes[i].nombre == this.examenNuevo.nombre) {
        contenido = true;
        break;
      }
    }
    if (contenido == false) {
      let existe: Boolean = false;
      for (let i = 0; i < this.misExamens.length; i++) {
        if (this.misExamens[i].nombre == this.examenNuevo.nombre) {
          existe = true;
          break;
        }
      }
      if (existe == false) {
        if (this.examenNuevo.nombre.length > 0 && this.examenNuevo.precio > 0) {
          this.misExamens.push(this.examenNuevo)
          this.examenNuevo = new ExamenesLaboratorio();
        }
      }
    } else {
      alert('Ya exite el examen.')
    }

  }
  quitar() {

  }

  guardarCambios() {
    let stringUser = localStorage.getItem('userLogin');
    if (stringUser != null) {
      this.user = JSON.parse(stringUser);
      if (this.misExamens.length > 0) {
        this.serviceExamenes.insertExamenesLaboratory(this.misExamens, this.user.id).subscribe(
          (data) => {
            alert('Se ha guardado la información.');
          }, (error) => {
            console.log('No se pudo guardar la información');
            this.misExamens = new Array();
            location.reload();
          }
        );
      } else {
        alert('Debes agregar al menos un examen.');
      }
    }
  }

  refresh() {
    this.serviceExamenes.getExamenesDisponibles().subscribe((lista) => { this.examenesDisponibles = lista });
    this.serviceExamenes.getExamenesByLaboratory(this.user).subscribe(
      (list: ExamenesLaboratorio[]) => {
        this.examenes = list;
      }
    );
  }

  editar(editando: ExamenesLaboratorio) {
    this.serviceExamenes.emiter.emit(Object.assign({}, editando));
  }

}
