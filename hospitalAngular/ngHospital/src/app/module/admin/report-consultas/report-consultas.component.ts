import { Component, OnInit } from '@angular/core';
import { Consulta } from '../../../../entidad/Consulta';
import { SesionService } from '../../../service/sesion.service';
import { ReportAdminService } from '../../../service/report-admin.service';
import { FormGroup,FormControl } from '@angular/forms';

@Component({
  selector: 'app-report-consultas',
  templateUrl: './report-consultas.component.html',
  styleUrls: ['./report-consultas.component.css']
})
export class ReportConsultasComponent implements OnInit {

  consultas: Consulta[] = [];
  total: number;
  cantidad: number;
  form = new FormGroup({
    fecha1: new FormControl(),
    fecha2: new FormControl()
  });

  constructor(private sesion: SesionService, private serviceReport: ReportAdminService) {
    this.total = 0;
    this.cantidad = 0;
    this.serviceReport.getConsultas().subscribe(
      (list: Consulta[]) => {
        this.consultas = list;
        console.log(this.consultas);
        this.cantidad = this.consultas.length;
        for (let i = 0; i < this.consultas.length; i++) {
          console.log(this.consultas[i].gananciaAdmin)
          this.total += this.consultas[i].gananciaAdmin;
        }
      }
    );
  }

  ngOnInit(): void {
    this.sesion.validarSesion();
   
  }
  realizarConsulta(){
    console.log(this.form.value)
    this.total = 0;
    this.serviceReport.getConsultasFechas(this.form.value.fecha1, this.form.value.fecha2).subscribe(
      (list:Consulta[])=>{
        this.consultas = list;
        this.cantidad = this.consultas.length;
        for (let i = 0; i < this.consultas.length; i++) {
          console.log(this.consultas[i].gananciaAdmin)
          this.total += this.consultas[i].gananciaAdmin;
        }
      }
    );
  }
  mostrarTodos(){
    this.total = 0;
    this.serviceReport.getConsultas().subscribe(
      (list:Consulta[])=>{
        this.consultas = list;
        this.cantidad = this.consultas.length;
        for (let i = 0; i < this.consultas.length; i++) {
          console.log(this.consultas[i].gananciaAdmin)
          this.total += this.consultas[i].gananciaAdmin;
        }
      }
    );
  }
  descargar(){
    console.log(this.consultas)
  }
}
