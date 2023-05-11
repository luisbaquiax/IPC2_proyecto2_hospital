DROP SCHEMA IF EXISTS hospital;
CREATE SCHEMA IF NOT EXISTS hospital;
USE hospital;

CREATE TABLE IF NOT EXISTS historial_porcentajes(
    id INT NOT NULL AUTO_INCREMENT,
    porcentaje DOUBLE NOT NULL,
    fecha_inicial DATE NOT NULL,
    fecha_final DATE NOT NULL,
    estado VARCHAR(10) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS solicitud_especialidad(
    nombre VARCHAR(45) NOT NULL,
    estado VARCHAR(15) NOT NULL,
    descripcion VARCHAR(100),
    PRIMARY KEY(nombre)
);

CREATE TABLE IF NOT EXISTS solicitud_tipo_examen(
    nombre VARCHAR(45) NOT NULL,
    estado VARCHAR(15) NOT NULL,
    descripcion VARCHAR(100),
    PRIMARY KEY(nombre)
);

CREATE TABLE IF NOT EXISTS usuario(
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    username VARCHAR(45) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(45) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    saldo DOUBLE NOT NULL,
    tipo VARCHAR(45) NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    telefono VARCHAR(8) NOT NULL,
    cui VARCHAR(13) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS admin(
    id INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id) REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS paciente(
    id INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id) REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS medico(
    id INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id) REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS laboratorio(
    id INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id) REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS recarga(
    id INT NOT NULL AUTO_INCREMENT,
    monto DOUBLE NOT NULL,
    fecha_hora DATETIME NOT NULL,
    paciente INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(paciente) REFERENCES paciente(id)
);

CREATE TABLE IF NOT EXISTS tipo_examen(
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL UNIQUE,
    descripcion VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS precio_examen(
    examen INT NOT NULL,
    laboratorio INT NOT NULL,
    precio DOUBLE NOT NULL,
    PRIMARY KEY(examen, laboratorio),
    FOREIGN KEY(examen) REFERENCES tipo_examen(id),
    FOREIGN KEY(laboratorio) REFERENCES laboratorio(id)
);

CREATE TABLE IF NOT EXISTS especialidad(
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL UNIQUE,
    descripcion VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS medico_especialidad(
    id_especialidad INT NOT NULL,
    medico INT NOT NULL,
    precio DOUBLE NOT NULL,
    PRIMARY KEY(id_especialidad, medico),
    FOREIGN KEY(id_especialidad) REFERENCES especialidad(id),
    FOREIGN KEY(medico) REFERENCES medico(id)
);

CREATE TABLE IF NOT EXISTS consulta(
    id INT NOT NULL AUTO_INCREMENT,
    paciente INT NOT NULL,
    medico INT NOT NULL,
    especialidad INT NOT NULL,
    porcentaje DOUBLE NOT NULL,
    fecha_creacion DATE NOT NULL,
    fecha_agendada DATETIME NOT NULL,
    precio DOUBLE NOT NULL,
    informe TEXT NOT NULL,
    estado VARCHAR(45) NOT NULL,
    ganancia_medico DOUBLE NOT NULL,
    ganancia_admin DOUBLE NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(paciente) REFERENCES paciente(id),
    FOREIGN KEY(medico) REFERENCES medico(id),
    FOREIGN KEY(especialidad) REFERENCES especialidad(id)
);

CREATE TABLE IF NOT EXISTS examenes_consulta(
    examen INT NOT NULL,
    consulta INT NOT NULL,
    estado BOOLEAN,
    PRIMARY KEY(examen, consulta),
    FOREIGN KEY(examen) REFERENCES tipo_examen(id),
    FOREIGN KEY(consulta) REFERENCES consulta(id)
);

CREATE TABLE IF NOT EXISTS solicitud_examen(
    id INT NOT NULL AUTO_INCREMENT,
    paciente INT NOT NULL,
    laboratorio INT NOT NULL,
    porcentaje DOUBLE NOT NULL,
    fecha_solicitado DATE NOT NULL,
    fecha_finalizada DATE NOT NULL,
    estado VARCHAR(45) NOT NULL,
    ganancia_lab DOUBLE NOT NULL,
    ganancia_admin DOUBLE NOT NULL,
    costo_total DOUBLE NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(paciente) REFERENCES paciente(id),
    FOREIGN KEY(laboratorio) REFERENCES laboratorio(id)
);

CREATE TABLE IF NOT EXISTS examenes_solicitud(
    tipo_examen INT NOT NULL,
    solicitud INT NOT NULL,
    precio DOUBLE NOT NULL,
    estado boolean,
    PRIMARY KEY(tipo_examen, solicitud),
    FOREIGN KEY(tipo_examen) REFERENCES tipo_examen(id),
    FOREIGN KEY(solicitud) REFERENCES solicitud_examen(id)
);

CREATE TABLE IF NOT EXISTS horario(
    id INT AUTO_INCREMENT NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_final TIME NOT NULL,
    idMedico INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(idMedico) REFERENCES medico(id)
);

CREATE TABLE IF NOT EXISTS resultados_laboratorio(
    id INT NOT NULL AUTO_INCREMENT,
    id_solicitud INT NOT NULL,
    id_examen INT NOT NULL,
    nombre_archivo VARCHAR(100) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id_solicitud) REFERENCES solicitud_examen(id),
    FOREIGN KEY(id_examen) REFERENCES tipo_examen(id)
);

CREATE TABLE IF NOT EXISTS resultados_consulta(
    id INT NOT NULL AUTO_INCREMENT,
    id_consulta INT NOT NULL,
    id_examen INT NOT NULL,
    nombre_archivo VARCHAR(100) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id_consulta) REFERENCES consulta(id),
    FOREIGN KEY(id_examen) REFERENCES tipo_examen(id)
);
USE hospital;
INSERT INTO historial_porcentajes(porcentaje,fecha_inicial,fecha_final,estado) VALUES(0.04,'2023-04-30','2023-04-30','ACTUAL');
