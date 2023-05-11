import { Component, OnInit } from '@angular/core';
import { SesionService } from '../../../service/sesion.service';
import { ReportAdminService } from '../../../service/report-admin.service';
import { MedicoIngresos } from '../../../../entidad/MedicoIngresos';
import { DownloadService } from '../../../service/download/download.service';

@Component({
  selector: 'app-top-medicos',
  templateUrl: './top-medicos.component.html',
  styleUrls: ['./top-medicos.component.css']
})
export class TopMedicosComponent implements OnInit {
  
  medicos: MedicoIngresos[] = [];

  constructor(private sesion: SesionService, private report: ReportAdminService, private down: DownloadService) {
    this.report.getTopMedicos().subscribe(
      (list: MedicoIngresos[]) => {
        this.medicos = list;
      }
    );

  }

  ngOnInit(): void {
    this.sesion.validarSesion();
  }
  descargar(){
    console.log('descargando');
    this.down.downTopMedicos().subscribe(
      blob=>{
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'informeTopMédicos.pdf';
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
