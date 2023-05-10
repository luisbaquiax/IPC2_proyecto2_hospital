import { Component, OnInit } from '@angular/core';
import { Usuario } from '../../../../entidad/Usuario';
import { AddUserService } from '../../../service/user/add-user.service';
import { FormGroup, FormControl } from '@angular/forms';
import { Especialidad } from '../../../../entidad/Especialidad';
import { EspecialidadService } from '../../../service/especialidad/especialidad.service';
import { ServiceEspecialidadService } from '../../../service/especialidad/service-especialidad.service';

@Component({
  selector: 'app-consultas',
  templateUrl: './consultas.component.html',
  styleUrls: ['./consultas.component.css']
})
export class ConsultasComponent implements OnInit {

  users: Usuario[] = [];
  form = new FormGroup({
    name: new FormControl()
  });
  form2 = new FormGroup({
    especialidad: new FormControl()
  });
  especialidades: Especialidad[] = [];
  constructor(private serviceUser: AddUserService, private serviceEspecialida: ServiceEspecialidadService) {
    this.procesarUsuarios();
    this.serviceEspecialida.getEspecialidadesDisponibles().subscribe(
      (list: Especialidad[]) => {
        this.especialidades = list;
      }
    );
  }

  ngOnInit(): void {
  }

  private procesarUsuarios() {
    this.serviceUser.getAllUser().subscribe(
      (list: Usuario[]) => {
        for (let i = 0; i < list.length; i++) {
          if (list[i].tipo == Usuario.MEDICO) {
            this.users.push(list[i]);
          }
        }
      }
    );
  }
  filtarPorNombre() {
    console.log(this.form.value.name);
    this.serviceUser.getUserMedicoFilterName(this.form.value.name).subscribe(
      (list: Usuario[])=>{
        this.users = list;
      }
    );
  }
  filtarEspecialidad() {
    this.serviceUser.getUserMedicoByEspecialida(this.form2.value.especialidad).subscribe(
      (list: Usuario[])=>{
        this.users = list;
      }
    );
  }
  verHorarios(user: Usuario){
    console.log(user);
  }
}
