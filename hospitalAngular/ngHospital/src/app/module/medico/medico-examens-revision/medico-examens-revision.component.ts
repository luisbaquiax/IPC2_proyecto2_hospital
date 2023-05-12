import { Component, OnInit } from '@angular/core';
import { ResultadoConsulta } from '../../../../entidad/ResultadoConsulta';
import { Consulta } from '../../../../entidad/Consulta';
import { FileService } from '../../../service/file/file.service';
import { ResultadoConsultaService } from '../../../service/resultado-consulta/resultado-consulta.service';
import { MedicoService } from '../../../service/medicoService/medico.service';
import { Usuario } from '../../../../entidad/Usuario';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-medico-examens-revision',
  templateUrl: './medico-examens-revision.component.html',
  styleUrls: ['./medico-examens-revision.component.css']
})
export class MedicoExamensRevisionComponent implements OnInit {

  resultados: ResultadoConsulta[] = [];
  consulta: Consulta;
  user: Usuario;
  form =new FormGroup({
    informe: new FormControl()
  });
  constructor(private fileService: FileService, private serviceConsulta: ResultadoConsultaService, private serviceMedico: MedicoService,
    private router: Router) {
    let consultaRevision = localStorage.getItem('consultaRevision');
    this.consulta = consultaRevision ? JSON.parse(consultaRevision) : null;
    let userJson = localStorage.getItem('userLogin');
    this.user = userJson ? JSON.parse(userJson) : null;

  }

  ngOnInit(): void {
    this.serviceConsulta.getResultadoConsulta(this.consulta.id).subscribe(
      (list: ResultadoConsulta[]) => {
        this.resultados = list
      }
    );
  }

  descargar(result: ResultadoConsulta) {

    this.fileService.downloadaPDF('paciente', result.nombreArchivo).subscribe(
      blob => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'resultados.pdf';
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
        window.URL.revokeObjectURL(url);
        console.log('Todo bien')
      }, error => {
        console.log('falló')
        console.log(error)
      }
    );
  }
  marcar(){
    this.consulta.informe = this.form.value.informe;
    this.consulta.estado = Consulta.FINALIZADA;
    this.serviceMedico.updateConsulta(this.consulta).subscribe(
      response=>{
        alert('Se marcado la consulta como finalizada.')
        this.router.navigate(['/medConsultasPendienteRevision']);
      }, error=>{
        alert('No se pudo realizar la acción.')
      }
    );
  }
}
