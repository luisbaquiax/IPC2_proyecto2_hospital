import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { InicioSesionComponent } from './module/users/inicio-sesion/inicio-sesion.component';
import { ConsultasComponent } from './module/paciente/consultas/consultas.component';
import { MenuMedicoComponent } from './module/medico/menu-medico/menu-medico.component';
import { AddInformacionMedicoComponent } from './module/medico/add-informacion-medico/add-informacion-medico.component';
import { CreateCountComponent } from './module/users/create-count/create-count.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NavPacienteComponent } from './module/paciente/nav-paciente/nav-paciente.component';
import { NavLaboratorioComponent } from './module/laboratorio/nav-laboratorio/nav-laboratorio.component';
import { HistorialPorcentajesComponent } from './module/admin/historial-porcentajes/historial-porcentajes.component';
import { MainPageComponent } from './module/admin/main-page/main-page.component';
import { NavAdmin } from './module/admin/nav/nav.component';
import { FileComponent } from './module/file/file.component';
import { ManejoSesionComponent } from './module/users/manejo-sesion/manejo-sesion.component';
import { LabAddInfoComponent } from './module/laboratorio/lab-add-info/lab-add-info.component';
import { TopMedicosComponent } from './module/admin/top-medicos/top-medicos.component';
import { TopLaboratoriosComponent } from './module/admin/top-laboratorios/top-laboratorios.component';
import { ReportConsultasComponent } from './module/admin/report-consultas/report-consultas.component';
import { ReportExamenesComponent } from './module/admin/report-examenes/report-examenes.component';
import { ReportTopPacientesComponent } from './module/medico/report/report-top-pacientes/report-top-pacientes.component';
import { ReportTopEspecialidadesComponent } from './module/medico/report/report-top-especialidades/report-top-especialidades.component';
import { HistorialMedicoPacienteComponent } from './module/medico/report/historial-medico-paciente/historial-medico-paciente.component';
import { PacienteHistorialMedicoComponent } from './module/paciente/report/historial-medico/historial-medico.component';
import { PacienteHistorialRecargasComponent } from './module/paciente/report/historial-recargas/historial-recargas.component';

@NgModule({
  declarations: [
    AppComponent,
    InicioSesionComponent,
    ConsultasComponent,
    MenuMedicoComponent,
    AddInformacionMedicoComponent,
    CreateCountComponent,
    NavPacienteComponent,
    NavLaboratorioComponent,
    HistorialPorcentajesComponent,
    MainPageComponent,
    NavAdmin,
    FileComponent,
    ManejoSesionComponent,
    LabAddInfoComponent,
    TopMedicosComponent,
    TopLaboratoriosComponent,
    ReportConsultasComponent,
    ReportExamenesComponent,
    ReportTopPacientesComponent,
    ReportTopEspecialidadesComponent,
    HistorialMedicoPacienteComponent,
    PacienteHistorialMedicoComponent,
    PacienteHistorialRecargasComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
