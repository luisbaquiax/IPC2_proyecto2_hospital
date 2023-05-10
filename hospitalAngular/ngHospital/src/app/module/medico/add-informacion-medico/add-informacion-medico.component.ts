import { Component, Input,Output, OnInit, EventEmitter } from '@angular/core';
import { Especialidad } from '../../../../entidad/Especialidad';
import { EspecialidadesMedico } from '../../../../entidad/EspecialidadesMedico';
import { ServiceEspecialidadService } from '../../../service/especialidad/service-especialidad.service';
import { Usuario } from '../../../../entidad/Usuario';
import { Router } from '@angular/router';
import { SesionService } from '../../../service/sesion.service';
import { EspecialidadService } from '../../../service/especialidad/especialidad.service';

@Component({
  selector: 'app-add-informacion-medico',
  templateUrl: './add-informacion-medico.component.html',
  styleUrls: ['./add-informacion-medico.component.css']
})
export class AddInformacionMedicoComponent implements OnInit {

  user!: Usuario;
  stringUser!: String;
  diponibles: Especialidad[] = [];
  misEspecialidades: EspecialidadesMedico[] = [];
  nuevaEspecialidad: EspecialidadesMedico;

  especialidades: EspecialidadesMedico[] = [];
  modificando: EspecialidadesMedico;

  constructor(private serviceEspecialidadService: ServiceEspecialidadService, private router: Router, private sesion: SesionService,
    private serviceEspecialidad: EspecialidadService) {
    this.nuevaEspecialidad = new EspecialidadesMedico();
    this.modificando = new EspecialidadesMedico();

    let stringUser = localStorage.getItem('userLogin');
    this.user = stringUser ? JSON.parse(stringUser) : null;

    this.serviceEspecialidadService.getEspecialidadesDisponibles().subscribe(
      (lista: Especialidad[]) => {
        this.diponibles = lista;
      }
    );
  }

  ngOnInit(): void {
    this.sesion.validarSesion();
    this.refresarEspecialidades();
  }

  agregar() {
    this.refresarEspecialidades();
    let contenido: Boolean = false;
    for (let i = 0; i < this.especialidades.length; i++) {
      if (this.especialidades[i].especialidad == this.nuevaEspecialidad.especialidad) {
        contenido = true;
        break;
      }
    }
    if (contenido == false) {
      let existe: Boolean = false;
      for (let i = 0; i < this.misEspecialidades.length; i++) {
        if (this.misEspecialidades[i].especialidad == this.nuevaEspecialidad.especialidad) {
          existe = true;
          break;
        }
      }
      if (existe == false) {
        if (this.nuevaEspecialidad.especialidad.length > 0 && this.nuevaEspecialidad.precio > 0) {
          this.misEspecialidades.push(this.nuevaEspecialidad);
          this.nuevaEspecialidad = new EspecialidadesMedico();
        }
      }
    } else {
      alert('La especialidad ya existe en tu lista de especialidades.')
    }

  }

  quitar() {
    location.reload();
  }

  guardarCambios() {
    console.log('Guardando cambios en la base de datos.');
    console.log(this.misEspecialidades)
    if (this.user) {
      if (this.misEspecialidades.length > 0) {
        this.serviceEspecialidadService.insertEspecialidad(this.misEspecialidades, this.user.id).subscribe(
          (data: EspecialidadesMedico[]) => {
            alert('Se guardado los cambios');
            location.reload();
          }, error => {
            console.log('No se pudo guardar la informacón');
            this.misEspecialidades = new Array();
          }
        );
      } else {
        alert('Debes agregar especialidades.')
      }
    }
    this.refresarEspecialidades();
  }

  refresarEspecialidades() {
    this.serviceEspecialidadService.getEspecialidades(this.user).subscribe(
      (list: EspecialidadesMedico[]) => {
        this.especialidades = list;
      }
    );
  }

  editar(espe: EspecialidadesMedico) {
    this.modificando = espe;
    this.serviceEspecialidad.emiter.emit(Object.assign({}, this.modificando));
  }

}
