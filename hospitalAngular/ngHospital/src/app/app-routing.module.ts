import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InicioSesionComponent } from './module/users/inicio-sesion/inicio-sesion.component';
import { NavAdmin } from './module/admin/nav/nav.component';
import { CreateCountComponent } from './module/users/create-count/create-count.component';
import { AddInformacionMedicoComponent } from './module/medico/add-informacion-medico/add-informacion-medico.component';
import { HistorialPorcentajesComponent } from './module/admin/historial-porcentajes/historial-porcentajes.component';
import { NavPacienteComponent } from './module/paciente/nav-paciente/nav-paciente.component';
import { NavLaboratorioComponent } from './module/laboratorio/nav-laboratorio/nav-laboratorio.component';
import { ManejoSesionComponent } from './module/users/manejo-sesion/manejo-sesion.component';
import { LabAddInfoComponent } from './module/laboratorio/lab-add-info/lab-add-info.component';
import { FileComponent } from './module/file/file.component';
import { TopMedicosComponent } from './module/admin/top-medicos/top-medicos.component';
import { TopLaboratoriosComponent } from './module/admin/top-laboratorios/top-laboratorios.component';
import { ReportExamenesComponent } from './module/admin/report-examenes/report-examenes.component';
import { ReportConsultasComponent } from './module/admin/report-consultas/report-consultas.component';
import { ReportTopEspecialidadesComponent } from './module/medico/report/report-top-especialidades/report-top-especialidades.component';
import { ReportTopPacientesComponent } from './module/medico/report/report-top-pacientes/report-top-pacientes.component';
import { MenuMedicoComponent } from './module/medico/menu-medico/menu-medico.component';
import { PacienteHistorialMedicoComponent } from './module/paciente/report/historial-medico/historial-medico.component';
import { PacienteHistorialRecargasComponent } from './module/paciente/report/historial-recargas/historial-recargas.component';
import { PacienteReporteConsultasComponent } from './module/paciente/report/paciente-reporte-consultas/paciente-reporte-consultas.component';
import { PacienteReportExamenesComponent } from './module/paciente/report/paciente-report-examenes/paciente-report-examenes.component';
import { HistorialMedicoPacienteComponent } from './module/medico/report/historial-medico-paciente/historial-medico-paciente.component';
import { LabTopPacientesComponent } from './module/laboratorio/report/lab-top-pacientes/lab-top-pacientes.component';
import { LabTopExamenesComponent } from './module/laboratorio/report/lab-top-examenes/lab-top-examenes.component';
import { RevisionSolicitudesExamenComponent } from './module/admin/revision-solicitudes-examen/revision-solicitudes-examen.component';
import { RevisionSolicitudesEspecialidadComponent } from './module/admin/revision-solicitudes-especialidad/revision-solicitudes-especialidad.component';
import { ModalEditEspecialidadComponent } from './module/medico/medico-add-especialidad/modal-edit-especialidad.component';
import { SolicitudEspecialidadComponent } from './module/medico/solicitud-especialidad/solicitud-especialidad.component';
import { SolicitudTipoExamenComponent } from './module/laboratorio/solicitud-tipo-examen/solicitud-tipo-examen.component';
import { MedicoConsultasAgendasComponent } from './module/medico/medico-consultas-agendas/medico-consultas-agendas.component';
import { MedicoAddExamenComponent } from './module/medico/medico-add-examen/medico-add-examen.component';
import { MedicoConsultaPendienteRevisionComponent } from './module/medico/medico-consulta-pendiente-revision/medico-consulta-pendiente-revision.component';
import { MedicoExamensRevisionComponent } from './module/medico/medico-examens-revision/medico-examens-revision.component';
import { ConsultasComponent } from './module/paciente/consultas/consultas.component';
import { PacienteCreateSolicitudExamenComponent } from './module/paciente/paciente-create-solicitud-examen/paciente-create-solicitud-examen.component';
import { LabExamenesPendienteComponent } from './module/laboratorio/lab-examenes-pendiente/lab-examenes-pendiente.component';
import { LabExamenesSolicitudComponent } from './module/laboratorio/lab-examenes-solicitud/lab-examenes-solicitud.component';
import { PacienteRevisionExamenesComponent } from './module/paciente/paciente-revision-examenes/paciente-revision-examenes.component';
import { PacienteResultadosLaboratorioComponent } from './module/paciente/paciente-resultados-laboratorio/paciente-resultados-laboratorio.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  {
    path: "login", component: InicioSesionComponent
  },
  {
    path: "file", component: FileComponent
  },
  {
    path: "nav-admin", component: NavAdmin
  },
  {
    path: "adminSolicitudesExamens", component: RevisionSolicitudesExamenComponent
  },
  {
    path: "adminSolicitudesEspecialidad", component: RevisionSolicitudesEspecialidadComponent
  },
  {
    path: "create-count", component: CreateCountComponent
  },
  {
    path: "menuMedico", component: MenuMedicoComponent
  },
  {
    path: "medicoHistorialMedico", component: HistorialMedicoPacienteComponent
  },
  {
    path: "medicoAddInfo", component: AddInformacionMedicoComponent
  },
  {
    path: "medicoAddEspecialidad", component: ModalEditEspecialidadComponent
  },
  {
    path: "medicoSolicitudEspecialidad", component: SolicitudEspecialidadComponent
  },
  {
    path: "medConsultasAgendadas", component: MedicoConsultasAgendasComponent
  },
  {
    path: "medConsultasPendienteRevision", component: MedicoConsultaPendienteRevisionComponent
  },
  {
    path: "medExamenesRevision", component: MedicoExamensRevisionComponent
  },
  {
    path: "medAddExamenesConsulta", component: MedicoAddExamenComponent
  },
  {
    path: "historialPorcentajes", component: HistorialPorcentajesComponent
  },
  {
    path: "navPaciente", component: NavPacienteComponent
  },
  {
    path: "navLab", component: NavLaboratorioComponent
  },
  {
    path: "manejoSesion", component: ManejoSesionComponent
  },
  {
    path: "labAddInfo", component: LabAddInfoComponent
  },
  {
    path: "labTopPacientes", component: LabTopPacientesComponent
  },
  {
    path: "labTopExaemns", component: LabTopExamenesComponent
  },
  {
    path: "labSolicitudTipoExamen", component: SolicitudTipoExamenComponent
  },
  {
    path: "labExamenesPendientes", component: LabExamenesPendienteComponent
  },
  {
    path: "labExamenesSolicitud", component: LabExamenesSolicitudComponent
  },
  {
    path: "topMedico", component: TopMedicosComponent
  },
  {
    path: "topLab", component: TopLaboratoriosComponent
  },
  {
    path: "reportExamenes", component: ReportExamenesComponent
  },
  {
    path: "reportConsultas", component: ReportConsultasComponent
  },
  {
    path: "reportMedicoTopPacientes", component: ReportTopPacientesComponent
  },
  {
    path: "reportMedicoTopEspecialidades", component: ReportTopEspecialidadesComponent
  },
  {
    path: "pacienteHistorialMedico", component: PacienteHistorialMedicoComponent
  },
  {
    path: "pacienteHistorialRecargas", component: PacienteHistorialRecargasComponent
  },
  {
    path: "pacienteReportConsultas", component: PacienteReporteConsultasComponent
  },
  {
    path: "pacienteReportExamenes", component: PacienteReportExamenesComponent
  },
  {
    path: "pacienteConsultas", component: ConsultasComponent
  },
  {
    path: "pacienteCreateSolicitudExamen", component: PacienteCreateSolicitudExamenComponent
  },
  {
    path: "pacienteRevisionResultados", component: PacienteRevisionExamenesComponent
  },
  {
    path: "pacienteResultadosLab", component: PacienteResultadosLaboratorioComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
