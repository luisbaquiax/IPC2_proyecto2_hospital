import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { UsuarioServiceService } from '../../service/usuario-service.service';
import { Usuario } from '../../../entidad/Usuario';
import { AddUserService } from '../../service/user/add-user.service';

@Component({
  selector: 'app-file',
  templateUrl: './file.component.html',
  styleUrls: ['./file.component.css']
})
export class FileComponent implements OnInit {

  jsonFile: File | undefined;
  url: string = "http://localhost:8080/HospitalApi/servletControllerData";
  menssage: String = '';
  users: Usuario[] = [];


  constructor(private http: HttpClient, private router: Router, private serviceUser: AddUserService) { }

  ngOnInit(): void {
    this.revisarUsuarios();
  }
  revisarUsuarios() {
    this.serviceUser.getAllUser().subscribe(
      (list: Usuario[]) => {
        this.users = list;
        if (this.users.length > 0) {
          this.router.navigate(['/login']);
        }
      }, (error) => {

      }
    );
  }
  chooseFile(event: any) {
    this.jsonFile = event.target.files[0];
  }
  sendFile() {
    console.log('hola sendfile');
    if (!this.jsonFile) {
      alert('Debe elegir el archivo antes de procesar la información');
      return;
    }

    const form = new FormData();

    form.append("archivo", this.jsonFile);
    this.http.post(this.url, form).subscribe(
      response => {
        this.menssage = 'Se ha procesado correctamente el archivo.';
        console.log('Se ha enviado el archivo. ', response);
      }
    );

  }

  subirDatos() {
    this.http.get(this.url).subscribe(
      response => {
        console.log('Se ha subido la información.');
        this.menssage = 'Se ha subido la información.';
        this.router.navigate(['/login']);
      },
      (error) => {
        //this.menssage = 'No se ha podido subir la información / Se debe procesar el archivo primero';
        this.revisarUsuarios();
        console.log(error)
      }
    );
  }

}