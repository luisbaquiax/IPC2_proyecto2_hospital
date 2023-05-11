import { Component, OnInit } from '@angular/core';
import { Usuario } from '../../../../entidad/Usuario';
import { SesionService } from '../../../service/sesion.service';

@Component({
  selector: 'app-nav-laboratorio',
  templateUrl: './nav-laboratorio.component.html',
  styleUrls: ['./nav-laboratorio.component.css']
})
export class NavLaboratorioComponent implements OnInit {
  user: Usuario
  constructor(private sesion: SesionService) {
    this.user = new Usuario();
    let userJson = localStorage.getItem('userLogin');
    this.user = userJson ? JSON.parse(userJson) : null;

  }

  ngOnInit(): void {
    this.sesion.validarSesion();
  }

  exit() {
    this.sesion.salir();
  }

}
