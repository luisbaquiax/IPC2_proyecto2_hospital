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
import { PacienteReporteConsultasComponent } from './module/paciente/report/paciente-reporte-consultas/paciente-reporte-consultas.component';
import { PacienteReportExamenesComponent } from './module/paciente/report/paciente-report-examenes/paciente-report-examenes.component';
import { LabTopPacientesComponent } from './module/laboratorio/report/lab-top-pacientes/lab-top-pacientes.component';
import { LabTopExamenesComponent } from './module/laboratorio/report/lab-top-examenes/lab-top-examenes.component';
import { HistorialComponent } from './module/admin/historial/historial.component';
import { RevisionSolicitudesExamenComponent } from './module/admin/revision-solicitudes-examen/revision-solicitudes-examen.component';
import { RevisionSolicitudesEspecialidadComponent } from './module/admin/revision-solicitudes-especialidad/revision-solicitudes-especialidad.component';
import { ModalEditEspecialidadComponent } from './module/medico/medico-add-especialidad/modal-edit-especialidad.component';
import { SolicitudEspecialidadComponent } from './module/medico/solicitud-especialidad/solicitud-especialidad.component';
import { SolicitudTipoExamenComponent } from './module/laboratorio/solicitud-tipo-examen/solicitud-tipo-examen.component';
import { LabEditExamenComponent } from './module/laboratorio/lab-edit-examen/lab-edit-examen.component';
import { MedicoConsultasAgendasComponent } from './module/medico/medico-consultas-agendas/medico-consultas-agendas.component';
import { MedicoAddExamenComponent } from './module/medico/medico-add-examen/medico-add-examen.component';
import { MedicoConsultaPendienteRevisionComponent } from './module/medico/medico-consulta-pendiente-revision/medico-consulta-pendiente-revision.component';
import { MedicoExamensRevisionComponent } from './module/medico/medico-examens-revision/medico-examens-revision.component';
import { RecargaComponent } from './module/paciente/recarga/recarga.component';
import { PacienteCreateSolicitudExamenComponent } from './module/paciente/paciente-create-solicitud-examen/paciente-create-solicitud-examen.component';
import { LabExamenesPendienteComponent } from './module/laboratorio/lab-examenes-pendiente/lab-examenes-pendiente.component';
import { LabExamenesSolicitudComponent } from './module/laboratorio/lab-examenes-solicitud/lab-examenes-solicitud.component';
import { PacienteResultadosLaboratorioComponent } from './module/paciente/paciente-resultados-laboratorio/paciente-resultados-laboratorio.component';
import { PacienteRevisionExamenesComponent } from './module/paciente/paciente-revision-examenes/paciente-revision-examenes.component';
import { PacienteConsultasPendienteResultadosComponent } from './module/paciente/paciente-consultas-pendiente-resultados/paciente-consultas-pendiente-resultados.component';
import { PacienteUploadResultadoConsultaComponent } from './module/paciente/paciente-upload-resultado-consulta/paciente-upload-resultado-consulta.component';
import { EditUserComponent } from './module/users/edit-user/edit-user.component';

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
    PacienteHistorialRecargasComponent,
    PacienteReporteConsultasComponent,
    PacienteReportExamenesComponent,
    LabTopPacientesComponent,
    LabTopExamenesComponent,
    HistorialComponent,
    RevisionSolicitudesExamenComponent,
    RevisionSolicitudesEspecialidadComponent,
    ModalEditEspecialidadComponent,
    SolicitudEspecialidadComponent,
    SolicitudTipoExamenComponent,
    LabEditExamenComponent,
    MedicoConsultasAgendasComponent,
    MedicoAddExamenComponent,
    MedicoConsultaPendienteRevisionComponent,
    MedicoExamensRevisionComponent,
    RecargaComponent,
    PacienteCreateSolicitudExamenComponent,
    LabExamenesPendienteComponent,
    LabExamenesSolicitudComponent,
    PacienteResultadosLaboratorioComponent,
    PacienteRevisionExamenesComponent,
    PacienteConsultasPendienteResultadosComponent,
    PacienteUploadResultadoConsultaComponent,
    EditUserComponent
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
