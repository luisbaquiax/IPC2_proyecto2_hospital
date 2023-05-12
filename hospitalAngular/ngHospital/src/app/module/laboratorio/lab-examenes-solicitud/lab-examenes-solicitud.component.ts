import { Component, OnInit } from '@angular/core';
import { ExamenTipoSolicitud } from '../../../../entidad/ExamenTipoSolicitud';
import { SolicitudExamenService } from '../../../service/solicitude-examen/solicitud-examen.service';
import { SolicitudExamen } from '../../../../entidad/SolicitudExamen';
import { FileService } from '../../../service/file/file.service';
import { Usuario } from '../../../../entidad/Usuario';
import { Router } from '@angular/router';
import { ResultadoConsulta } from '../../../../entidad/ResultadoConsulta';

@Component({
  selector: 'app-lab-examenes-solicitud',
  templateUrl: './lab-examenes-solicitud.component.html',
  styleUrls: ['./lab-examenes-solicitud.component.css']
})
export class LabExamenesSolicitudComponent implements OnInit {

  pdfFile: File | undefined;
  examenes: ExamenTipoSolicitud[] = [];

  solicitud: SolicitudExamen;
  user: Usuario;

  encontrado: Boolean;
  listoParaMarcar: Boolean;

  constructor(private serviceSolicitud: SolicitudExamenService, private serviceFile: FileService, private router: Router) {
    let solcitudJson = localStorage.getItem('solicitud');
    this.solicitud = solcitudJson ? JSON.parse(solcitudJson) : null;
    let userJSON = localStorage.getItem('userLogin');
    this.user = userJSON ? JSON.parse(userJSON) : null;
    this.encontrado = false;
    this.listoParaMarcar = false;

  }

  ngOnInit(): void {
    this.serviceSolicitud.getListExamensBySolicitud(this.solicitud.id).subscribe(
      (list: ExamenTipoSolicitud[]) => {
        this.examenes = list;
        this.revisarExamens(this.examenes);
      }
    );

  }

  chooseFile(event: any) {
    this.pdfFile = event.target.files[0];
  }

  saveFile(examen: ExamenTipoSolicitud) {
    console.log('hola sendfile');
    if (!this.pdfFile) {
      alert('Debe elegir el archivo antes de guardar.');
      return;
    }
    const form = new FormData();

    form.append("pdf", this.pdfFile);
    console.log('enviando archivo');
    this.serviceFile.enviarArchivoPDF(form, '1', examen.nombre, examen.examen.idExamen, examen.examen.idSolicitud, this.user.username).subscribe(
      response => {
        console.log('todo bien');
        alert('Se enviado el archivo.')
        location.reload();
      }, error => {
        alert('No se pudo enviar el archivo.')
      }

    );
  }

  revisarExamens(examenes: ExamenTipoSolicitud[]) {
    for (let i = 0; i < examenes.length; i++) {
      if (examenes[i].examen.estado == false) {
        this.encontrado = true;
      }
    }
    if (this.encontrado == false) {
      //actualizar solicitud a finalizada.
      this.listoParaMarcar = true;
    }
  }

  marcar() {
    console.log("marcnado");
    this.solicitud.estado = SolicitudExamen.FINALIZADA;
    //ACTUALIZAR EN DB
    this.serviceSolicitud.updateSolicitud(this.solicitud).subscribe(
      reponse => {
        alert('Se ha marcado la solicitud como finalizada');
        this.router.navigate(['/labExamenesPendientes'])
      }, error => {
        alert('No se pudo actualizar la solicitud.')
      }
    );
  }


}

