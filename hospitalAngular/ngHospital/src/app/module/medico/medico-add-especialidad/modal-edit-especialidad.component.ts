import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { EspecialidadesMedico } from '../../../../entidad/EspecialidadesMedico';
import { EspecialidadService } from '../../../service/especialidad/especialidad.service';
import { FormGroup, FormControl } from '@angular/forms';
import { ServiceEspecialidadService } from '../../../service/especialidad/service-especialidad.service';
import { Usuario } from '../../../../entidad/Usuario';
import { MedicoEspecialidad } from '../../../../entidad/MedicoEspecialidad';
import { Router } from '@angular/router';

@Component({
  selector: 'app-medico-add-especialidad',
  templateUrl: './medico-add-especialidad.component.html',
  styleUrls: ['./medico-add-especialidad.component.css']
})
export class ModalEditEspecialidadComponent implements OnInit {

  form = new FormGroup({
    precio: new FormControl()
  });

  nuevo: EspecialidadesMedico;
  user: Usuario;
  modificado!: MedicoEspecialidad;

  constructor(private serviceEspecialidad: ServiceEspecialidadService, private serviceEmit: EspecialidadService, private router: Router, 
    private ServiceEspecialidadService: ServiceEspecialidadService) {
    this.nuevo = new EspecialidadesMedico();
    this.modificado = new MedicoEspecialidad();

    let usrJson = localStorage.getItem('userLogin');
    this.user = usrJson ? JSON.parse(usrJson) : null;

  }

  ngOnInit(): void {
    this.serviceEmit.emiter.subscribe(
      (data: EspecialidadesMedico)=>{
        this.nuevo = data;
        console.log(this.nuevo)
      }
    );
  }

  guardarCambios() {
    console.log(this.nuevo);
    this.modificado.idEspecialidad = 0;
    this.modificado.idMedico = this.user.id;
    this.modificado.precio = this.form.value.precio;
    console.log(this.modificado);
    if(this.form.value.precio){
      if(this.modificado.precio>0){
        this.serviceEspecialidad.updateEspecialidaMedico(this.modificado, this.nuevo.especialidad).subscribe(
          response => {
            alert('Se ha guardado los cambios correctamente.')
            location.reload();
          }, error => {
            alert('No se pudo guardar los cambios.')
          }
        );
      }else{
        alert('La cantidad debe ser mayor a cero.')
      }
    }
  }

}
