import { Injectable } from '@angular/core';
import { User } from '../../../entidad/User';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from '../../../entidad/Usuario';

@Injectable({
  providedIn: 'root'
})
export class AddUserService {
  url: string = "http://localhost:8080/HospitalApi/UserController?tarea=";

  constructor(private http: HttpClient) { }

  public inserUser(usuario: Usuario) {
    return this.http.post<User>(`${this.url}ingresarNuevo`, usuario);
  }
  public getAllUser() {
    return this.http.get<Usuario[]>(`${this.url}allUsers`);
  }
  public getAllUserByUserName(username: String) {
    return this.http.get<User>(`${this.url}userUsername&username=${username}`);
  }
  public getUserNamePassword(user: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(this.url + "search", user);
  }

  public getUserMedicoFilterName(name: string) {
    return this.http.get<Usuario[]>(`${this.url}filterUserMedico&filter=${name}`);

  }
  public getUserMedicoByEspecialida(name: string) {
    return this.http.get<Usuario[]>(this.url + "getMedicosByEspecialidad&especialidad=" + name);
  }
  public getUsersLabsByName(name: string) {
    return this.http.get<Usuario[]>(this.url + "filterLabByName&name=" + name);
  }

}
