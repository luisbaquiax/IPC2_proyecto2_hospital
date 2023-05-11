export class Consulta {
    public static readonly AGENDADA = "AGENDADA";
    public static readonly EXAMEN_PENDIENTE = "EXAMEN_PENDIENTE";
    public static readonly FINALIZADA = "FINALIZADA";
    public static readonly PENDIENTE_REVISION = "PENDIENTE_REVISION";
    id!: number;
    idPaciente!: number;
    idMedico!: number;
    idEspecialidad!: number;
    porcentaje!: number;
    fechaCreacion!: string;
    fechaAgendada!: String;
    precio!: number;
    informe!: string;
    estado!: string;
    gananciaMedico!: number;
    gananciaAdmin!: number;
    fecha!:string;
    hora!:string;
}