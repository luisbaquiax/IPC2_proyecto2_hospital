import { Component, OnInit } from '@angular/core';
import { Usuario } from '../../../../entidad/Usuario';
import { AddUserService } from '../../../service/user/add-user.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {
  usuario: Usuario;
  msgCreatCount: string;
  constructor(private serviceUser: AddUserService) {
    let userJSON = localStorage.getItem('userLogin');
    this.usuario = userJSON ? JSON.parse(userJSON) : null;
    this.msgCreatCount = '';
  }

  ngOnInit(): void {
    this.serviceUser.getUserNamePassword(this.usuario).subscribe(
      data => {
        this.usuario = data;
      }
    );
  }
  guardarCambios() {
    this.serviceUser.updateUser(this.usuario).subscribe(
      response => {
        alert('Se ha acualizado sus datos con éxito.')
        location.reload();
      }, error => {
        alert('El nombre de usuario no está disponible.')
        location.reload();
      }
    );
  }

  verificarUsername() {

  }
}
