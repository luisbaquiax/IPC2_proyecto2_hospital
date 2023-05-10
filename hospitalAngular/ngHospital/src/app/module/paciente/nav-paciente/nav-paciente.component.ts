import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from '../../../../entidad/Usuario';
import { SesionService } from '../../../service/sesion.service';
import { AddUserService } from '../../../service/user/add-user.service';

@Component({
  selector: 'app-nav-paciente',
  templateUrl: './nav-paciente.component.html',
  styleUrls: ['./nav-paciente.component.css']
})
export class NavPacienteComponent implements OnInit {

  user: Usuario;

  constructor(private router: Router, private sesion: SesionService, private userSevice: AddUserService) {
    this.user = new Usuario();
    let userJson = localStorage.getItem('userLogin');
    this.user = userJson ? JSON.parse(userJson) : null;

  }

  ngOnInit(): void {
    this.sesion.validarSesion();
    this.userSevice.getUserNamePassword(this.user).subscribe(
      (data: Usuario) => {
        this.user = data;
      }, error => {
        this.exit();
      }
    );
  }
  exit() {
    this.sesion.salir();
  }

}
