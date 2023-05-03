import { Component, OnInit } from '@angular/core';
import { Usuario } from '../../../../entidad/Usuario';

@Component({
  selector: 'app-nav-laboratorio',
  templateUrl: './nav-laboratorio.component.html',
  styleUrls: ['./nav-laboratorio.component.css']
})
export class NavLaboratorioComponent implements OnInit {
  user: Usuario
  constructor() {
    this.user = new Usuario();
   }

  ngOnInit(): void {
  }

}
