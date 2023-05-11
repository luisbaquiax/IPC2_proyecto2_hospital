import { Component, OnInit } from '@angular/core';
import { Consulta } from '../../../../entidad/Consulta';
import { MedicoService } from '../../../service/medicoService/medico.service';
import { ExamenesLaboratorioService } from '../../../service/examenesLaboratorio/examenes-laboratorio.service';
import { TipoExamen } from '../../../../entidad/TipoExamen';
import { FormGroup, FormControl } from '@angular/forms';
import { ExamenesConsultaService } from '../../../service/medicoService/examenes-consulta.service';
import { ExamenConsulta } from '../../../../entidad/ExamenConsulta';
import { Router } from '@angular/router';

@Component({
  selector: 'app-medico-add-examen',
  templateUrl: './medico-add-examen.component.html',
  styleUrls: ['./medico-add-examen.component.css']
})
export class MedicoAddExamenComponent implements OnInit {

  consulta!: Consulta;
  examenesDisponibles: TipoExamen[] = [];
  examenesAgregadas: TipoExamen[] = [];
  agregando!: TipoExamen;
  form = new FormGroup({
    examen: new FormControl()
  });

  examenesConsulta: ExamenConsulta[] = [];

  constructor(private serviceMedico: MedicoService, private serviceLab: ExamenesLaboratorioService,
    private serviceExamens: ExamenesConsultaService,
    private route: Router) {
    this.consulta = new Consulta();
    this.agregando = new TipoExamen();
    this.consulta.id = 0;

    this.serviceLab.getExamenesDisponibles().subscribe(
      (list: TipoExamen[]) => {
        this.examenesDisponibles = list;
      }
    );
    let json = localStorage.getItem('consultaEnviando');
    this.consulta = json ? JSON.parse(json) : null;
  }

  ngOnInit(): void {
  }
  agregar() {
    let existe: Boolean = false;
    this.agregando.name = this.form.value.examen;
    for (let i = 0; i < this.examenesAgregadas.length; i++) {
      if (this.agregando.name == this.examenesAgregadas[i].name) {
        existe = true;
        break;
      }
    }
    if (existe == false) {
      for (let i = 0; i < this.examenesDisponibles.length; i++) {
        if (this.agregando.name == this.examenesDisponibles[i].name) {
          this.agregando.id = this.examenesDisponibles[i].id;
          this.agregando.description = this.examenesDisponibles[i].description;
          break;
        }
      }
      let examConsulta: ExamenConsulta = new ExamenConsulta();
      examConsulta.idExamen = this.agregando.id;
      examConsulta.indConsulta = this.consulta.id;
      this.examenesConsulta.push(examConsulta);
      this.examenesAgregadas.push(this.agregando);
      this.agregando = new TipoExamen();
    } else {
      alert('El examen ya se ha agreado.');
    }
  }

  guardarCambios() {
    console.log('guardando cambios.')
    console.log(this.consulta.id);
    console.log(this.examenesConsulta);
    if (this.examenesConsulta.length > 0) {
      this.serviceExamens.insertExamenConsulta(this.examenesConsulta).subscribe(
        response => {
          this.consulta.estado = Consulta.EXAMEN_PENDIENTE;
          this.serviceMedico.updateConsulta(this.consulta).subscribe(
            response => {
              alert('Se ha agregado los exámenes correspondientes.');
              location.reload();
            }
          );
        }, error => {
          console.log(error)
        }
      );
    } else {
      alert('Debe agregar exáemenes a la consulta.')
    }
    this.route.navigate(['/medConsultasAgendadas']);
  }

  reiniciar() { }


}
