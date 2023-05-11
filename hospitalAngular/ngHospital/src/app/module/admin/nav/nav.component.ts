import { Component, OnInit } from '@angular/core';
import { SesionService } from '../../../service/sesion.service';
import { Usuario } from '../../../../entidad/Usuario';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav-admin',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavAdmin implements OnInit {

  user: Usuario;
  constructor(private router: Router, private sesion: SesionService) {
    this.user = new Usuario();
  }

  ngOnInit(): void {
    let stringUser = localStorage.getItem("userLogin");
    if (stringUser != null) {
      this.user = JSON.parse(stringUser);
      console.log(this.user);
    } else {
      this.router.navigate(['/login']);
    }
  }
  salir(){
    this.sesion.salir();
  }

  realizarConsulta(){
    
  }

}
