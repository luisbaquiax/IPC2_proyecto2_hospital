import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { HistorialMedicoService } from '../../../../service/historialMedico/report-historial-medico.service';
import { HistorialMecioReport } from '../../../../../entidad/model/reports/HistorialMedicoReport';
import { Usuario } from '../../../../../entidad/Usuario';
import { SesionService } from '../../../../service/sesion.service';

@Component({
  selector: 'app-historial-medico-paciente',
  templateUrl: './historial-medico-paciente.component.html',
  styleUrls: ['./historial-medico-paciente.component.css']
})
export class HistorialMedicoPacienteComponent implements OnInit {

  historial: HistorialMecioReport[] = [];
  user: Usuario;
  form = new FormGroup({
    idPaciente: new FormControl()
  });

  constructor(private serviceHistorialMedico: HistorialMedicoService, private sesion: SesionService) {
    let userJson = localStorage.getItem('userLogin');
    this.user = userJson ? JSON.parse(userJson) : null;
    this.serviceHistorialMedico.getHistorialMedico(this.user).subscribe(
      (list: HistorialMecioReport[]) => {
        this.historial = list;
      }
    );
  }

  ngOnInit(): void {
    this.sesion.validarSesion();
  }

  realizarConsulta() {
    this.serviceHistorialMedico.getHistorialByMedico(this.user, this.form.value.idPaciente).subscribe(
      (list: HistorialMecioReport[]) => {
        this.historial = list;
      }
    );
  }

}
