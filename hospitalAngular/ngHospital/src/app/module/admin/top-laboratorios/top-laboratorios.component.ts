import { Component, OnInit } from '@angular/core';
import { LaboratorioIngresos } from '../../../../entidad/LaboratorioIngresos';
import { SesionService } from '../../../service/sesion.service';
import { ReportAdminService } from '../../../service/report-admin.service';
import { DownloadService } from '../../../service/download/download.service';

@Component({
  selector: 'app-top-laboratorios',
  templateUrl: './top-laboratorios.component.html',
  styleUrls: ['./top-laboratorios.component.css']
})
export class TopLaboratoriosComponent implements OnInit {

  laboratorios: LaboratorioIngresos[]=[];
  
  constructor(private sesion: SesionService, private report: ReportAdminService, private down: DownloadService) {
    this.report.getTopLaboratorios().subscribe(
      (list: LaboratorioIngresos[])=>{
        this.laboratorios = list;
      }
    );
   }

  ngOnInit(): void {
    this.sesion.validarSesion();
  }
  descargar(){
    console.log('descargando');
    this.down.downTopLabs().subscribe(
      blob=>{
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'informeTopLaboratorios.pdf';
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
        window.URL.revokeObjectURL(url);
        console.log('Todo bien')
      },error=>{  
        console.log('falló')
        console.log(error)
      }
    );
  }

}
