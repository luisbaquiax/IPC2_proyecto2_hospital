import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Usuario } from '../../../../entidad/Usuario';
import { AddUserService } from '../../../service/user/add-user.service';
import { ExamenesLaboratorioService } from '../../../service/examenesLaboratorio/examenes-laboratorio.service';
import { SesionService } from '../../../service/sesion.service';
import { ExamenesLaboratorio } from '../../../../entidad/ExamenesLaboratorio';
import { SolicitudExamen } from '../../../../entidad/SolicitudExamen';
import { SolicitudExamenService } from '../../../service/solicitude-examen/solicitud-examen.service';

@Component({
  selector: 'app-paciente-create-solicitud-examen',
  templateUrl: './paciente-create-solicitud-examen.component.html',
  styleUrls: ['./paciente-create-solicitud-examen.component.css']
})
export class PacienteCreateSolicitudExamenComponent implements OnInit {

  form = new FormGroup({
    lab: new FormControl()
  });

  form2 = new FormGroup({
    examen: new FormControl(),
  });

  laboratorios: Usuario[] = [];
  user: Usuario;
  seleccionado: Boolean;

  labSeleccionado: Usuario;

  examenens: ExamenesLaboratorio[] = [];

  examenesSolicitados: ExamenesLaboratorio[] = [];

  examenSeleccionado: ExamenesLaboratorio;

  solicitudExamen: SolicitudExamen;

  constructor(private serviceUser: AddUserService, private serviceLab: ExamenesLaboratorioService, private sesion: SesionService,
    private serviceSolicitudExamen: SolicitudExamenService) {
    let userJSON = localStorage.getItem('userLogin')
    this.user = userJSON ? JSON.parse(userJSON) : null;

    this.procesarLaboratorios();

    this.seleccionado = false;
    this.labSeleccionado = new Usuario();
    this.examenSeleccionado = new ExamenesLaboratorio();
    this.solicitudExamen = new SolicitudExamen();
  }

  ngOnInit(): void {
    this.sesion.validarSesion();
    this.serviceUser.getUserNamePassword(this.user).subscribe(
      (data: Usuario) => {
        this.user = data;
      }
    );
  }

  private procesarLaboratorios() {
    this.serviceUser.getAllUser().subscribe(
      (list: Usuario[]) => {
        for (let i = 0; i < list.length; i++) {
          if (list[i].tipo == Usuario.LABORATORIO) {
            this.laboratorios.push(list[i]);
          }
        }
      }
    );
  }

  private procesarExamenesLaboratorio(user: Usuario) {
    this.serviceLab.getExamenesByLaboratory(user).subscribe(
      (list: ExamenesLaboratorio[]) => {
        this.examenens = list;
      }
    );
  }

  private getLab() {
    for (let i = 0; i < this.laboratorios.length; i++) {
      if (this.laboratorios[i].nombre == this.form.value.lab) {
        this.labSeleccionado = this.laboratorios[i];
        break;
      }
    }
  }
  seleccionarLaboratorio() {
    this.seleccionado = !this.seleccionado;
    this.getLab();
    this.procesarExamenesLaboratorio(this.labSeleccionado);
  }

  private getExamen() {
    for (let i = 0; i < this.examenens.length; i++) {
      if (this.examenens[i].nombre == this.form2.value.examen) {
        this.examenSeleccionado = this.examenens[i];
        break;
      }
    }
  }

  seleccionarExamen() {
    this.getExamen();
    let existe: Boolean = false;
    for (let i = 0; i < this.examenesSolicitados.length; i++) {
      if (this.examenesSolicitados[i] === this.examenSeleccionado) {
        existe = true;
        break;
      }
    }

    if (existe == false) {
      this.examenesSolicitados.push(this.examenSeleccionado);
      this.examenSeleccionado = new ExamenesLaboratorio();
    } else {
      alert('El examen ya ha sido agregado');
    }

  }

  enviarSolicitud() {
    console.log(this.examenesSolicitados);
    console.log(this.labSeleccionado);
    this.solicitudExamen.estado = SolicitudExamen.PENDIENTE;
    this.solicitudExamen.costoTotal = this.getTotal();
    this.solicitudExamen.idLaboratorio = this.labSeleccionado.id;
    this.solicitudExamen.idPaciente = this.user.id;
    if(this.user.saldo >= this.getTotal()){
      this.serviceSolicitudExamen.inserSolicitud(this.solicitudExamen).subscribe(
        response=>{
          this.serviceSolicitudExamen.insertExamenesSolicitud(this.examenesSolicitados).subscribe(
            response=>{
              alert('Se ha enviado la solicitud con éxito.')
              location.reload();
            }, error=>{
              console.log(error);
            }
          );
        }, error=>{
          alert('No se pudo enviar la solicitud.')
        }
      );
    }else{
      alert('No tienes suficiente saldo para poder realizar el examen.')
    }

  }

  changeStatus() {
    this.seleccionado = !this.seleccionado;
  }

  getTotal(){
    let total = 0;
    for (let i = 0; i < this.examenesSolicitados.length; i++) {
      total += this.examenesSolicitados[i].precio;
    }
    return total;
  }
}
