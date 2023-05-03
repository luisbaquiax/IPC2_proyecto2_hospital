import { Component, OnInit } from '@angular/core';
import { SesionService } from '../../../service/sesion.service';
import { Usuario } from '../../../../entidad/Usuario';

@Component({
  selector: 'app-menu-medico',
  templateUrl: './menu-medico.component.html',
  styleUrls: ['./menu-medico.component.css']
})
export class MenuMedicoComponent implements OnInit {

  user: Usuario;
  constructor(private sesion: SesionService) {

    let userJson = localStorage.getItem('userLogin');
    this.user = userJson ? JSON.parse(userJson): null;
  }

  ngOnInit(): void {
    this.sesion.validarSesion();
  }
  salir() {
    this.sesion.salir();
  }
}
