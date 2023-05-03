import { Component, OnInit } from '@angular/core';
import { SesionService } from '../../../service/sesion.service';
import { ReportAdminService } from '../../../service/report-admin.service';
import { MedicoIngresos } from '../../../../entidad/MedicoIngresos';

@Component({
  selector: 'app-top-medicos',
  templateUrl: './top-medicos.component.html',
  styleUrls: ['./top-medicos.component.css']
})
export class TopMedicosComponent implements OnInit {
  
  medicos: MedicoIngresos[] = [];

  constructor(private sesion: SesionService, private report: ReportAdminService) {
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
  }

}
