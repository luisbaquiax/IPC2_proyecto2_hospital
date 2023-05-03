import { Component, OnInit } from '@angular/core';
import { LaboratorioIngresos } from '../../../../entidad/LaboratorioIngresos';
import { SesionService } from '../../../service/sesion.service';
import { ReportAdminService } from '../../../service/report-admin.service';

@Component({
  selector: 'app-top-laboratorios',
  templateUrl: './top-laboratorios.component.html',
  styleUrls: ['./top-laboratorios.component.css']
})
export class TopLaboratoriosComponent implements OnInit {

  laboratorios: LaboratorioIngresos[]=[];
  
  constructor(private sesion: SesionService, private report: ReportAdminService) {
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
  }

}
