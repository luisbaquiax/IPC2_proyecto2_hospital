import { Component, OnInit } from '@angular/core';
import { ResultadosLaboratorio } from '../../../../entidad/ResultadoLaboratorio';
import { ResultadoLaboratorioService } from '../../../service/resultadoLaboratorio/resultado-laboratorio.service';
import { SolicitudExamen } from '../../../../entidad/SolicitudExamen';
import { FileService } from '../../../service/file/file.service';

@Component({
  selector: 'app-paciente-resultados-laboratorio',
  templateUrl: './paciente-resultados-laboratorio.component.html',
  styleUrls: ['./paciente-resultados-laboratorio.component.css']
})
export class PacienteResultadosLaboratorioComponent implements OnInit {

  resultados: ResultadosLaboratorio[] = [];
  solicitud: SolicitudExamen;

  constructor(private serviceReult: ResultadoLaboratorioService, private serviceDown: FileService) {
    let solicituJSON = localStorage.getItem('solicitudEnviado');
    this.solicitud = solicituJSON ? JSON.parse(solicituJSON) : null;
  }

  ngOnInit(): void {
    this.serviceReult.getResultados(this.solicitud.id).subscribe(
      (list: ResultadosLaboratorio[]) => {
        this.resultados = list;
      }
    );
  }

  descargar(resultado: ResultadosLaboratorio) {
    this.serviceDown.downloadaPDF('lab', resultado.nombreArchivo).subscribe(
      blob => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = resultado.nombreArchivo + '.pdf';
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

}
