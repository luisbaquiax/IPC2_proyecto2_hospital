import { Component, OnInit } from '@angular/core';
import { SesionService } from '../../../service/sesion.service';
import { Usuario } from '../../../../entidad/Usuario';
import { Router } from '@angular/router';
import { AddUserService } from '../../../service/user/add-user.service';

@Component({
  selector: 'app-nav-admin',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavAdmin implements OnInit {

  user: Usuario;
  constructor(private router: Router, private sesion: SesionService, private serviceUser: AddUserService) {
    this.user = new Usuario();
    let stringUser = localStorage.getItem("userLogin");
    this.user = stringUser? JSON.parse(stringUser): null;
  }

  ngOnInit(): void {
    this.serviceUser.getUserNamePassword(this.user).subscribe(
      (data)=>{
        this.user = data;
      }
    );
  }
  salir(){
    this.sesion.salir();
  }

}
