/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apiresthospital.objects.datos;

import com.apiresthospital.data.modelDB.AdminDB;
import com.apiresthospital.data.modelDB.ConsultaDB;
import com.apiresthospital.data.modelDB.EspecialidadDB;
import com.apiresthospital.data.modelDB.ExamenConsultaDB;
import com.apiresthospital.data.modelDB.ExamenesSolicitudDB;
import com.apiresthospital.data.modelDB.HistorialPorcentajDB;
import com.apiresthospital.data.modelDB.LaboratorioDB;
import com.apiresthospital.data.modelDB.MedicoDB;
import com.apiresthospital.data.modelDB.MedicoEspecialidadDB;
import com.apiresthospital.data.modelDB.PacienteDB;
import com.apiresthospital.data.modelDB.PrecioExamenDB;
import com.apiresthospital.data.modelDB.SolicitudExamenDB;
import com.apiresthospital.data.modelDB.TipoExamenDB;
import com.apiresthospital.data.modelDB.UserDB;
import com.apiresthospital.model.Consulta;
import com.apiresthospital.model.Especialidad;
import com.apiresthospital.model.ExamenConsulta;
import com.apiresthospital.model.ExamenSolicitado;
import com.apiresthospital.model.PrecioExamen;
import com.apiresthospital.model.SolicitudExamen;
import com.apiresthospital.model.TipoExamen;
import com.apiresthospital.model.Usuario;
import com.apiresthospital.objects.ListaTiposUsuario;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author luis
 */
@Getter
@ToString
public class CargaDatos {

    private ProcesadorJSON procesadorJSON;

    //DB
    private EspecialidadDB especialidadDB;
    private MedicoEspecialidadDB medicoEspecialidadDB;
    private UserDB userDB;
    private MedicoDB medicoDB;
    private PacienteDB pacienteDB;
    private LaboratorioDB laboratorioDB;
    private AdminDB adminDB;
    private ConsultaDB consultaDB;
    private ExamenConsultaDB examenConsultaDB;
    private SolicitudExamenDB solicitudExamenDB;
    private ExamenesSolicitudDB examenesSolicitudDB;
    private TipoExamenDB tipoExamenDB;
    private PrecioExamenDB precioExamenDB;

    public CargaDatos(ProcesadorJSON procesadorJSON) {
        this.procesadorJSON = procesadorJSON;
        //DB
        this.especialidadDB = new EspecialidadDB();
        this.medicoEspecialidadDB = new MedicoEspecialidadDB();
        this.userDB = new UserDB();
        this.medicoDB = new MedicoDB();
        this.pacienteDB = new PacienteDB();
        this.laboratorioDB = new LaboratorioDB();
        this.adminDB = new AdminDB();
        this.consultaDB = new ConsultaDB();
        this.examenConsultaDB = new ExamenConsultaDB();
        this.solicitudExamenDB = new SolicitudExamenDB();
        this.examenesSolicitudDB = new ExamenesSolicitudDB();
        this.tipoExamenDB = new TipoExamenDB();
        this.precioExamenDB = new PrecioExamenDB();
    }

    public void subirDatos(ProcesadorJSON procesadorJSON) {
        subirTipoExamen(procesadorJSON.getTiposExamens());
        subirEspecialidades(procesadorJSON.getEspecialidades());
        subirUsuarios(procesadorJSON.getUsuarios());
        subirPrecioExamenLaboratorio(procesadorJSON.getPreciosExamens());
        subirConsultas(procesadorJSON.getConsultas());
        subirExamenesConsulta(procesadorJSON.getExamenConsultas());
        subirSolicitudExamenLab(procesadorJSON.getSolicitudExamens());
        subirExamenesSolicitudLab(procesadorJSON.getExamenesSolicitados());
    }

    private void subirTipoExamen(List<TipoExamen> tipoExamenes) {
        for (TipoExamen tipoExamen : tipoExamenes) {
            tipoExamenDB.insertTipoExamen(tipoExamen);
        }
    }

    private void subirEspecialidades(List<Especialidad> especialidads) {
        for (Especialidad especialidad : especialidads) {
            especialidadDB.insert(especialidad);
        }
    }

    private void subirUsuarios(List<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            if (userDB.insert(usuario)) {
                switch (usuario.getTipo()) {
                    case ListaTiposUsuario.ADMIN:
                        adminDB.insert(usuario.getId());
                        break;
                    case ListaTiposUsuario.MEDICO:
                        medicoDB.insert(usuario.getId());
                        break;
                    case ListaTiposUsuario.PACIENTE:
                        pacienteDB.insert(usuario.getId());
                        break;
                    case ListaTiposUsuario.LABORATORIO:
                        pacienteDB.insert(usuario.getId());
                        break;
                }
            }

        }
    }

    private void subirPrecioExamenLaboratorio(List<List<PrecioExamen>> preciosExamenes) {
        for (List<PrecioExamen> preciosExamene : preciosExamenes) {
            for (PrecioExamen precioExamen : preciosExamene) {
                precioExamenDB.insert(precioExamen);
            }
        }
    }

    private void subirConsultas(List<Consulta> consultas) {
        for (Consulta consulta : consultas) {
            if (consultaDB.insert(consulta)) {
            }
        }
    }

    private void subirExamenesConsulta(List<List<ExamenConsulta>> examenesConsulta) {
        for (List<ExamenConsulta> list : examenesConsulta) {
            for (ExamenConsulta examenConsulta : list) {
                examenConsultaDB.insert(examenConsulta);
            }
        }
    }

    private void subirSolicitudExamenLab(List<SolicitudExamen> solicitudes) {
        for (SolicitudExamen solicitud : solicitudes) {
            solicitudExamenDB.insert(solicitud);
        }
    }

    private void subirExamenesSolicitudLab(List<List<ExamenSolicitado>> examaenes) {
        for (List<ExamenSolicitado> examaene : examaenes) {
            for (ExamenSolicitado examenSolicitado : examaene) {
                examenesSolicitudDB.insert(examenSolicitado);
            }
        }
    }

}
