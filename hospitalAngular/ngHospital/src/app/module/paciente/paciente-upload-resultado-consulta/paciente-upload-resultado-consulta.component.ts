import { Component, OnInit } from '@angular/core';
import { Usuario } from '../../../../entidad/Usuario';
import { AddUserService } from '../../../service/user/add-user.service';
import { ResultadoConsultaService } from '../../../service/resultado-consulta/resultado-consulta.service';
import { ResultadoConsulta } from '../../../../entidad/ResultadoConsulta';
import { Consulta } from '../../../../entidad/Consulta';
import { FileService } from '../../../service/file/file.service';
import { ExamenConsulta } from '../../../../entidad/ExamenConsulta';
import { ExamenesConsultaService } from '../../../service/medicoService/examenes-consulta.service';
import { MedicoService } from '../../../service/medicoService/medico.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-paciente-download-resultado-consulta',
  templateUrl: './paciente-upload-resultado-consulta.component.html',
  styleUrls: ['./paciente-upload-resultado-consulta.component.css']
})
export class PacienteUploadResultadoConsultaComponent implements OnInit {

  pdfFile: File | undefined;

  user: Usuario;
  examenes: ExamenConsulta[] = [];
  consulta: Consulta;

  listoParaMarcar: Boolean;
  existe: Boolean;

  constructor(private serviceUser: AddUserService, private serviceExamenConsulta: ExamenesConsultaService, private serviceFile: FileService,
    private serviceMedico: MedicoService, private router: Router) {
    let userJson = localStorage.getItem('userLogin');
    this.user = userJson ? JSON.parse(userJson) : null;

    let consultaJSON = localStorage.getItem('consultaPendienteExamen');
    this.consulta = consultaJSON ? JSON.parse(consultaJSON) : null;

    this.listoParaMarcar = false;
    this.existe = false;

  }

  ngOnInit(): void {
    this.serviceExamenConsulta.getList(this.consulta.id).subscribe(
      (list: ExamenConsulta[]) => {
        this.examenes = list;
        this.revisarExmanes(this.examenes);
      }
    );
  }

  chooseFile(event: any) {
    this.pdfFile = event.target.files[0];
  }

  saveFile(examen: ExamenConsulta) {
    console.log('hola sendfile');
    if (!this.pdfFile) {
      alert('Debe elegir el archivo antes de guardar.');
      return;
    }
    const form = new FormData();

    form.append("pdf", this.pdfFile);
    console.log('enviando archivo');
    this.serviceFile.enviarArchivoPDF(form, '2', examen.nombre, examen.idExamen, examen.indConsulta, this.user.username).subscribe(
      response => {
        console.log('todo bien');
        alert('Se enviado el archivo.')
        location.reload();
      }, error => {
        alert('No se pudo enviar el archivo.')
      }
    );
  }

  revisarExmanes(examenes: ExamenConsulta[]) {
    console.log('revisando')
    console.log(this.examenes)
    for (let i = 0; i < examenes.length; i++) {
      if (examenes[i].estado == false) {
        this.existe = true;
        break;
      }
    }
    if (this.existe == false) {
      this.listoParaMarcar = true;
    }

  }
  marcar() {
    this.consulta.estado = Consulta.PENDIENTE_REVISION;
    this.serviceMedico.updateConsulta(this.consulta).subscribe(
      response => {
        alert('Se marcado la consulta como PENDIENTE DE REVISIÓN DE RESULTADOS.');
        this.router.navigate(['/pacienteConsultasPendienteResultados']);
      }, error => {
        alert('No se pudo actualizar la consulta.');
      }
    );
  }

}
