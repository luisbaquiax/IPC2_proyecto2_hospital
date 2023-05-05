import { Component, OnInit } from '@angular/core';
import { SesionService } from '../../../../service/sesion.service';
import { Usuario } from '../../../../../entidad/Usuario';
import { HistorialMecioReport } from '../../../../../entidad/model/reports/HistorialMedicoReport';
import { FormGroup, FormControl } from '@angular/forms';
import { HistorialMedicoService } from '../../../../service/historialMedico/report-historial-medico.service';

@Component({
  selector: 'app-historial-medico',
  templateUrl: './historial-medico.component.html',
  styleUrls: ['./historial-medico.component.css']
})
export class PacienteHistorialMedicoComponent implements OnInit {

  user: Usuario;
  historial: HistorialMecioReport[] = [];

  form = new FormGroup({
    fecha1: new FormControl(),
    fecha2: new FormControl()
  });

  constructor(private sesion: SesionService, private serviceHistorial: HistorialMedicoService) {

    let userJson = localStorage.getItem('userLogin');
    this.user = userJson ? JSON.parse(userJson) : null;
    this.serviceHistorial.getHistorial(this.user).subscribe(
      (list: HistorialMecioReport[]) => {
        this.historial = list;
      }
    );
  }

  ngOnInit(): void {
    this.sesion.validarSesion();
  }

  realizarConsulta() {
    this.serviceHistorial.getHistorialBetweenDate(this.user, this.form.value.fecha1, this.form.value.fecha2).subscribe(
      (list: HistorialMecioReport[]) => {
        this.historial = list;
      }
    );
  }
  descargar(){}
}
