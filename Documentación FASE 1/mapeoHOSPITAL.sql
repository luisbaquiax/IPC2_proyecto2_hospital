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
    estado BOOLEAN NOT NULL,
    PRIMARY KEY(nombre)
);

CREATE TABLE IF NOT EXISTS solicitud_tipo_examen(
    nombre VARCHAR(45) NOT NULL,
    estado BOOLEAN NOT NULL,
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
    cui VARCHAR(13) NOT NULL,
    PRIMARY KEY(id)
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
    PRIMARY KEY(id_especialidad),
    FOREIGN KEY(id_especialidad) REFERENCES especialidad(id),
    FOREIGN KEY(medico) REFERENCES medico(id)
);

CREATE TABLE IF NOT EXISTS consulta(
    id INT NOT NULL AUTO_INCREMENT,
    paciente INT NOT NULL,
    medico INT NOT NULL,
    especialidad VARCHAR(45) NOT NULL,
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
    FOREIGN KEY(medico) REFERENCES medico(id)
);

CREATE TABLE IF NOT EXISTS examenes_consulta(
    examen INT NOT NULL,
    consulta INT NOT NULL,
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
    PRIMARY KEY(tipo_examen, solicitud),
    FOREIGN KEY(tipo_examen) REFERENCES tipo_examen(id),
    FOREIGN KEY(solicitud) REFERENCES solicitud_examen(id)
);
