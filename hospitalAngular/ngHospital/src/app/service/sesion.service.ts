import { Injectable } from '@angular/core';
import { Usuario } from '../../entidad/Usuario';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class SesionService {
  user: Usuario;
  constructor(private router: Router) {
    this.user = new Usuario();
   }

  public validarSesion(){
    let stringUser = localStorage.getItem('userLogin');
    if (stringUser != null) {
      this.user = JSON.parse(stringUser);
      console.log('validando sesion '+this.user)
    } else {
      this.router.navigate(['/login']);
    }
  }
  public salir(){
    localStorage.removeItem('userLogin');
    this.router.navigate(['/login']);
  }
}
