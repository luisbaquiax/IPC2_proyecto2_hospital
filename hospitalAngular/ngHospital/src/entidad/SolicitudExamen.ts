export class SolicitudExamen {
    public static readonly PENDIENTE: string = "PENDIENTE";
    public static readonly FINALIZADA: string = "FINALIZADA";
    id!: number;
    idPaciente!: number;
    idLaboratorio!: number;
    porcentaje!: number;
    fechaSolicitado!: string;
    fechaRealizada!: string;
    estado!: string;
    gananciaLab!: number;
    gananciaAdmin!: number;
    costoTotal!: number;
}