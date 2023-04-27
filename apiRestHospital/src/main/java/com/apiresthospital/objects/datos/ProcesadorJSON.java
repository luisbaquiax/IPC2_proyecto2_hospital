/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apiresthospital.objects.datos;

import com.apiresthospital.model.Consulta;
import com.apiresthospital.model.Especialidad;
import com.apiresthospital.model.ExamenConsulta;
import com.apiresthospital.model.ExamenSolicitado;
import com.apiresthospital.model.Horario;
import com.apiresthospital.model.MedicoEspecialidad;
import com.apiresthospital.model.PrecioExamen;
import com.apiresthospital.model.SolicitudExamen;
import com.apiresthospital.model.TipoExamen;
import com.apiresthospital.model.Usuario;
import com.apiresthospital.objects.ListaTiposUsuario;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.ToString;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author luis
 */
@Getter
@ToString
public class ProcesadorJSON {

    private List<Usuario> usuarios;
    private List<TipoExamen> tiposExamens;
    private List<Especialidad> especialidades;
    private List<Consulta> consultas;
    private List<List<ExamenConsulta>> examenConsultas;
    private List<SolicitudExamen> solicitudExamens;
    private List<List<ExamenSolicitado>> examenesSolicitados;
    private List<List<MedicoEspecialidad>> especialidadesMedicos;
    private List<List<Horario>> horariosMedicos;
    private List<List<PrecioExamen>> preciosExamens;

    /**
     * data base
     */
    public ProcesadorJSON() {
        this.usuarios = new ArrayList<>();
        this.tiposExamens = new ArrayList<>();
        this.especialidades = new ArrayList<>();
        this.consultas = new ArrayList<>();
        this.examenConsultas = new ArrayList<>();
        this.solicitudExamens = new ArrayList<>();
        this.examenesSolicitados = new ArrayList<>();
        this.especialidadesMedicos = new ArrayList<>();
        this.horariosMedicos = new ArrayList<>();
        this.preciosExamens = new ArrayList<>();
    }

    public void procesarContendioJSON(String content) throws ParseException {
        JSONParser parser = new JSONParser();
        Object jsonObj = parser.parse(content);

        procesarEspecialidades((JSONObject) jsonObj);
        procesarTiposExamen((JSONObject) jsonObj);
        procesarUsuariosAdmin((JSONObject) jsonObj);
        procesarMedicos((JSONObject) jsonObj);
        procesarPacientes((JSONObject) jsonObj);
        procesarLaboratorios((JSONObject) jsonObj);
        procesarConsultas((JSONObject) jsonObj);
        procesarSolicitudesExamenes((JSONObject) jsonObj);

    }

    private void procesarUsuariosAdmin(JSONObject jsonObject) {
        JSONArray admins = (JSONArray) jsonObject.get("admin");
        for (int i = 0; i < admins.size(); i++) {
            JSONObject admin = (JSONObject) admins.get(i);
            if (!existeUsuario(Integer.parseInt(admin.get("id").toString()), admin.get("username").toString(), usuarios)) {
                this.usuarios.add(new Usuario(
                        Integer.parseInt(admin.get("id").toString()),
                        admin.get("nombre").toString(),
                        admin.get("username").toString(),
                        admin.get("password").toString(),
                        admin.get("email").toString(),
                        admin.get("fecha_nacimiento").toString(),
                        Double.parseDouble(admin.get("saldo").toString()),
                        ListaTiposUsuario.ADMIN,
                        "",
                        "",
                        ""));
            } else {
                //usuario repetido
            }
        }
    }

    private void procesarPacientes(JSONObject jsonObject) {
        JSONArray pacientesJson = (JSONArray) jsonObject.get("pacientes");
        procesarUsers(pacientesJson);
    }

    private void procesarUsers(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.size(); i++) {
            JSONObject userJson = (JSONObject) jSONArray.get(i);
            if (!existeUsuario(Integer.parseInt(userJson.get("id").toString()), userJson.get("username").toString(), usuarios)) {
                Usuario user = new Usuario(
                        Integer.parseInt(userJson.get("id").toString()),
                        userJson.get("nombre").toString(),
                        userJson.get("username").toString(),
                        userJson.get("password").toString(),
                        userJson.get("email").toString(),
                        userJson.get("fecha_nacimiento").toString(),
                        Double.parseDouble(userJson.get("saldo").toString()),
                        ListaTiposUsuario.MEDICO,
                        userJson.get("direccion").toString(),
                        userJson.get("telefono").toString(),
                        userJson.get("cui").toString());
                this.usuarios.add(user);
            } else {
                //usuario repetido
            }
        }
    }

    private void procesarEspecialidades(JSONObject jsonObject) {
        JSONArray especialidadesJson = (JSONArray) jsonObject.get("especialidades");
        for (int i = 0; i < especialidadesJson.size(); i++) {
            JSONObject especialidadJson = (JSONObject) especialidadesJson.get(i);
            if (!existeEspecialidad(Integer.parseInt(especialidadJson.get("id").toString()), especialidadJson.get("nombre").toString(), especialidades)) {
                this.especialidades.add(new Especialidad(
                        Integer.parseInt(especialidadJson.get("id").toString()),
                        especialidadJson.get("nombre").toString(),
                        especialidadJson.get("descripcion").toString()));
            } else {
                //especialidad repetida o id repetido 
            }
        }
    }

    private void procesarTiposExamen(JSONObject jsonObject) {
        JSONArray especialidadesJson = (JSONArray) jsonObject.get("tipos_examenes");
        for (int i = 0; i < especialidadesJson.size(); i++) {
            JSONObject especialidadJson = (JSONObject) especialidadesJson.get(i);
            if (!existeTipoExamen(Integer.parseInt(especialidadJson.get("id").toString()), especialidadJson.get("nombre").toString(), tiposExamens)) {
                this.especialidades.add(new Especialidad(
                        Integer.parseInt(especialidadJson.get("id").toString()),
                        especialidadJson.get("nombre").toString(),
                        especialidadJson.get("descripcion").toString()));
            } else {
                //especialidad repetida o id repetido 
            }
        }
    }

    private void procesarMedicos(JSONObject jsonObject) {
        JSONArray medicosJson = (JSONArray) jsonObject.get("medicos");
        for (int i = 0; i < medicosJson.size(); i++) {
            JSONObject medicoJson = (JSONObject) medicosJson.get(i);

            if (!existeUsuario(Integer.parseInt(medicoJson.get("id").toString()), medicoJson.get("username").toString(), usuarios)) {
                Usuario user = new Usuario(
                        Integer.parseInt(medicoJson.get("id").toString()),
                        medicoJson.get("nombre").toString(),
                        medicoJson.get("username").toString(),
                        medicoJson.get("password").toString(),
                        medicoJson.get("email").toString(),
                        medicoJson.get("fecha_nacimiento").toString(),
                        Double.parseDouble(medicoJson.get("saldo").toString()),
                        ListaTiposUsuario.MEDICO,
                        medicoJson.get("direccion").toString(),
                        medicoJson.get("telefono").toString(),
                        medicoJson.get("cui").toString());
                this.usuarios.add(user);
                //verficamos las especialidades
                JSONArray especialidadesJson = (JSONArray) medicoJson.get("especialidades");
                List<MedicoEspecialidad> listaEpecialidades = new ArrayList<>();
                for (int j = 0; j < especialidadesJson.size(); j++) {
                    JSONObject especialidadJson = (JSONObject) especialidadesJson.get(j);
                    //verificamos en el listado general
                    if (existeEspecialidad(Integer.parseInt(especialidadJson.get("id").toString()), especialidades)) {
                        //verificamos en el listado 'particular'
                        if (!existeEspecialidadMedico(Integer.parseInt(especialidadJson.get("id").toString()), listaEpecialidades)) {
                            listaEpecialidades.add(new MedicoEspecialidad(
                                    Integer.parseInt(especialidadJson.get("id").toString()),
                                    user.getId(),
                                    Double.parseDouble(especialidadJson.get("precio").toString())));
                        } else {
                            //se esta repetiendo la especialidad del medico
                        }
                    }
                }
                //agregamos horarios
                JSONArray horariosJson = (JSONArray) medicoJson.get("horarios");
                List<Horario> listaHorarios = new ArrayList<>();
                for (int j = 0; j < horariosJson.size(); j++) {
                    String[] horaInicioFinal = horariosJson.get(i).toString().split("-");
                    String horaInicio = horaInicioFinal[0];
                    String horaFinal = horaInicioFinal[1];
                    //no valida traslape de horarios
                    if (!existeHorario(horaInicio, horaFinal, listaHorarios)) {
                        listaHorarios.add(new Horario(0, horaInicio, horaFinal, user.getId()));
                    } else {
                        //se repite horarios
                    }
                }
                this.horariosMedicos.add(listaHorarios);
                this.especialidadesMedicos.add(listaEpecialidades);
            } else {
                //usuario repetido
            }
        }
    }

    private void procesarLaboratorios(JSONObject jsonObject) {
        JSONArray laboratoriosJson = (JSONArray) jsonObject.get("laboratorios");
        for (int i = 0; i < laboratoriosJson.size(); i++) {
            JSONObject labJson = (JSONObject) laboratoriosJson.get(i);

            if (!existeUsuario(Integer.parseInt(labJson.get("id").toString()), labJson.get("username").toString(), usuarios)) {
                Usuario user = new Usuario(
                        Integer.parseInt(labJson.get("id").toString()),
                        labJson.get("nombre").toString(),
                        labJson.get("username").toString(),
                        labJson.get("password").toString(),
                        labJson.get("email").toString(),
                        labJson.get("fecha_fundacion").toString(),
                        Double.parseDouble(labJson.get("saldo").toString()),
                        ListaTiposUsuario.LABORATORIO,
                        labJson.get("direccion").toString(),
                        labJson.get("telefono").toString(),
                        labJson.get("cui").toString());
                //verficamos los examenes de laboratorio
                JSONArray examenesLabJson = (JSONArray) labJson.get("examenes");
                List<PrecioExamen> listaExamenesLab = new ArrayList<>();
                for (int j = 0; j < examenesLabJson.size(); j++) {
                    JSONObject examenLabJson = (JSONObject) examenesLabJson.get(j);
                    //verificamos en el listado general
                    if (existeTipoExamen(i, "", tiposExamens)) {
                        if (!existePrecioExamen(Integer.parseInt(examenLabJson.get("id").toString()), listaExamenesLab)) {
                            listaExamenesLab.add(new PrecioExamen(
                                    Integer.parseInt(examenLabJson.get("id").toString()),
                                    user.getId(),
                                    Double.parseDouble(examenLabJson.get("precio").toString())));
                        } else {
                            //se repite los examenes en el laboratorio.
                        }
                    } else {
                        //el tipo-examen no existe en el listado general
                    }
                }
                this.usuarios.add(user);
                this.preciosExamens.add(listaExamenesLab);
            } else {
                //usuario repetido
            }
        }
    }

    private void procesarConsultas(JSONObject jsonObject) {
        JSONArray consultasJson = (JSONArray) jsonObject.get("consultas");
        for (int i = 0; i < consultasJson.size(); i++) {
            JSONObject consultaJson = (JSONObject) consultasJson.get(i);

            Consulta consulta = new Consulta(
                    Integer.parseInt(consultaJson.get("id").toString()),
                    Integer.parseInt(consultaJson.get("paciente").toString()),
                    Integer.parseInt(consultaJson.get("médico").toString()),
                    Integer.parseInt(consultaJson.get("especialidad").toString()),
                    Integer.parseInt(consultaJson.get("id").toString()),
                    consultaJson.get("fecha_creacion").toString(),
                    consultaJson.get("fecha_agendada").toString(),
                    Double.parseDouble(consultaJson.get("precio").toString()),
                    consultaJson.get("informe_finalizacion").toString(),
                    consultaJson.get("estado").toString(),
                    0,
                    0);
            consulta.setGananciaAdmin(consulta.getPorcentaje() * consulta.getPrecio());
            consulta.setGananciaMedico((1 - consulta.getPorcentaje()) * consulta.getPrecio());
            List<ExamenConsulta> examenesConsulta = new ArrayList<>();
            JSONArray examanesConsultaJson = (JSONArray) consultaJson.get("examenes_solicitados");
            for (int j = 0; j < examanesConsultaJson.size(); j++) {
                JSONObject examen = (JSONObject) examanesConsultaJson.get(j);
                //verificamos si existe el examen en la lista general de examenes
                if (existeTipoExamen(Integer.parseInt(examen.get("id").toString()), "", tiposExamens)) {
                    if (!existeExamenConsulta(Integer.parseInt(examen.get("id").toString()), examenesConsulta)) {
                        examanesConsultaJson.add(
                                new ExamenConsulta(Integer.parseInt(examen.get("id").toString()), consulta.getId()));
                    }
                } else {
                    //el examen-solicitado no existe
                }
            }
            this.examenConsultas.add(examenesConsulta);
            this.consultas.add(consulta);
        }
    }

    private void procesarSolicitudesExamenes(JSONObject jsonObject) {
        JSONArray solicitudesJson = (JSONArray) jsonObject.get("solicitudes");
        for (int i = 0; i < solicitudesJson.size(); i++) {
            JSONObject solicitudJson = (JSONObject) solicitudesJson.get(i);
            if (!existeSolicitudExamen(Integer.parseInt(solicitudJson.get("id").toString()), solicitudExamens)) {
                SolicitudExamen solicitud = new SolicitudExamen(
                        Integer.parseInt(solicitudJson.get("id").toString()),
                        Integer.parseInt(solicitudJson.get("paciente").toString()),
                        Integer.parseInt(solicitudJson.get("laboratorio").toString()),
                        Double.parseDouble(solicitudJson.get("porcentaje_aplicacion").toString()),
                        solicitudJson.get("fecha_solicitado").toString(),
                        solicitudJson.get("fecha_finalizado").toString(),
                        solicitudJson.get("estado_solicitud").toString(),
                        0,
                        0,
                        0);

                JSONArray examenesSolicitudJson = (JSONArray) solicitudJson.get("examenes");
                List<ExamenSolicitado> examenes = new ArrayList<>();

                for (int j = 0; j < examenesSolicitudJson.size(); j++) {
                    JSONObject examenJson = (JSONObject) examenesSolicitudJson.get(j);

                    ExamenSolicitado solicitado = new ExamenSolicitado(
                            Integer.parseInt(examenJson.get("id").toString()),
                            solicitud.getId(),
                            Double.parseDouble(examenJson.get("precio").toString()));

                    if (existeTipoExamen(solicitado.getIdExamen(), "", tiposExamens)) {
                        if (!existeExamenSolicitud(solicitado.getIdExamen(), examenes)) {
                            examenes.add(solicitado);
                        } else {
                            //el examen está repetido en la solicitud-examenes
                        }
                    } else {
                        //el examen no esté en listado general
                    }
                }
                this.examenesSolicitados.add(examenes);
                this.solicitudExamens.add(solicitud);
            } else {
                // solicitud con Id reptido
            }
        }
    }

    public boolean existeUsuario(int id, String username, List<Usuario> users) {
        for (Usuario user : users) {
            return user.getId() == id || user.getUserName().equals(username);
        }
        return false;
    }

    public boolean existeEspecialidad(int id, String name, List<Especialidad> especialidades) {
        for (Especialidad e : especialidades) {
            return e.getId() == id || e.getName().equals(name);
        }
        return false;
    }

    public boolean existeEspecialidad(int id, List<Especialidad> especialidades) {
        for (Especialidad e : especialidades) {
            return e.getId() == id;
        }
        return false;
    }

    public boolean existeEspecialidadMedico(int id, List<MedicoEspecialidad> especialidades) {
        for (MedicoEspecialidad e : especialidades) {
            return e.getIdEspecialidad() == id;
        }
        return false;
    }

    public boolean existeTipoExamen(int id, String name, List<TipoExamen> tipsExamens) {
        for (TipoExamen t : tipsExamens) {
            return t.getId() == id || t.getName().equals(name);
        }
        return false;
    }

    public boolean existeHorario(String horaInicio, String horaFinal, List<Horario> horarios) {
        for (Horario horario : horarios) {
            return (horario.getHoraInicio().equals(horaInicio) && (horario.getHoraFinal().equals(horaFinal)));
        }
        return false;
    }

    public boolean existePrecioExamen(int id, List<PrecioExamen> examenes) {
        for (PrecioExamen e : examenes) {
            return e.getIdExamen() == id;
        }
        return false;
    }

    public boolean existeExamenConsulta(int id, List<ExamenConsulta> examenes) {
        for (ExamenConsulta e : examenes) {
            return e.getIdExamen() == id;
        }
        return false;
    }

    public boolean existeExamenSolicitud(int idExamen, List<ExamenSolicitado> examens) {
        for (ExamenSolicitado examen : examens) {
            return examen.getIdExamen() == idExamen;
        }
        return false;
    }

    public boolean existeSolicitudExamen(int idExamen, List<SolicitudExamen> examens) {
        for (SolicitudExamen examen : examens) {
            return examen.getId() == idExamen;
        }
        return false;
    }

}
