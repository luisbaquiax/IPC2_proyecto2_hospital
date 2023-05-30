 REPORTE ADMINISTRADOR

2. reporte top médicos

SELECT SUM(ganancia_medico) AS total_medico, SUM(ganancia_admin) AS total_app, COUNT(medico) AS cantidad, u.nombre, u.id
FROM consulta c
INNER JOIN medico m
ON m.id = c.medico
INNER JOIN usuario u
ON u.id = m.id
GROUP BY medico
ORDER BY SUM(ganancia_medico) DESC
LIMIT 5;

3. reporte top laboratorios

SELECT SUM(ganancia_lab) AS total_lab, SUM(ganancia_admin) AS total_app, COUNT(laboratorio) AS cantidad, u.nombre, u.id
FROM solicitud_examen s
INNER JOIN laboratorio p
ON p.id = s.laboratorio
INNER JOIN usuario u
ON u.id = p.id
GROUP BY laboratorio
ORDER BY SUM(ganancia_lab) DESC
LIMIT 5;

REPORTE MEDICO

** historial medico de paciente

SELECT fecha_creacion, fecha_agendada, informe, estado, t.nombre AS examen, u.nombre as medico, precio, c.id as consulta
FROM consulta c
INNER JOIN examenes_consulta e
ON c.id = e.consulta
INNER JOIN tipo_examen t
ON t.id = e.examen
INNER JOIN medico m
ON c.medico = m.id
INNER JOIN usuario u
ON m.id = u.id
WHERE paciente = 111
AND medico = 1 ORDER BY fecha_creacion DESC;

2. top pacientes

SELECT SUM(ganancia_medico) AS ganancia_medico, COUNT(paciente) AS cantidad_consultas, fecha_creacion, u.nombre, u.id
FROM consulta c
INNER JOIN paciente p
ON c.paciente = p.id
INNER JOIN usuario u
ON p.id = u.id
WHERE c.medico = '1'
GROUP BY paciente
ORDER BY SUM(ganancia_medico) DESC
LIMIT 5;

SELECT SUM(ganancia_medico) AS ganancia_medico, COUNT(paciente) AS cantidad_consultas, fecha_creacion, u.nombre, u.id
FROM consulta c
INNER JOIN paciente p
ON c.paciente = p.id
INNER JOIN usuario u
ON p.id = u.id
WHERE c.medico = '1' AND fecha_creacion BETWEEN ? AND ?
GROUP BY paciente
ORDER BY SUM(ganancia_medico) DESC
LIMIT 5;

2. top especialidades

SELECT SUM(ganancia_medico) AS ganancia_medico, COUNT(especialidad) AS consultas, fecha_creacion, e.nombre, e.id
FROM consulta c
INNER JOIN especialidad e
ON c.especialidad = e.id
WHERE c.medico = '1'
GROUP BY especialidad
ORDER BY SUM(ganancia_medico) DESC
LIMIT 5;

SELECT SUM(ganancia_medico) AS ganancia_medico, COUNT(especialidad) AS consultas, fecha_creacion, e.nombre, e.id
FROM consulta c
INNER JOIN especialidad e
ON c.especialidad = e.id
WHERE c.medico = '1' AND fecha_creacion BETWEEN ? AND ?
GROUP BY especialidad
ORDER BY SUM(ganancia_medico) DESC
LIMIT 5;

REPORTES PACIENTE

1. historial médico

SELECT fecha_creacion, fecha_agendada, informe, estado, t.nombre AS examen, u.nombre as medico, precio, c.id as consulta
FROM consulta c
INNER JOIN examenes_consulta e
ON c.id = e.consulta
INNER JOIN tipo_examen t
ON t.id = e.examen
INNER JOIN medico m
ON c.medico = m.id
INNER JOIN usuario u
ON m.id = u.id
WHERE paciente = 111;

SELECT fecha_creacion, fecha_agendada, informe, estado, t.nombre AS examen, u.nombre as medico, precio, c.id as consulta
FROM consulta c
INNER JOIN examenes_consulta e
ON c.id = e.consulta
INNER JOIN tipo_examen t
ON t.id = e.examen
INNER JOIN medico m
ON c.medico = m.id
INNER JOIN usuario u
ON m.id = u.id
WHERE paciente = 111
AND fecha_creacion BETWEEN ? AND ?;

3. reporte de consulta

SELECT precio, informe, fecha_creacion, fecha_agendada, estado, e.nombre as especialidad, u.nombre as medico
FROM consulta c
INNER JOIN especialidad e
ON c.especialidad = e.id
INNER JOIN medico m
ON c.medico = m.id
INNER JOIN usuario u
ON m.id = u.id
WHERE paciente = 111;

SELECT precio, informe, fecha_creacion, fecha_agendada, estado, e.nombre as especialidad, u.nombre as medico
FROM consulta c
INNER JOIN especialidad e
ON c.especialidad = e.id
INNER JOIN medico m
ON c.medico = m.id
INNER JOIN usuario u
ON m.id = u.id
WHERE paciente = 111 AND fecha_creacion BETWEEN ? AND ?;

SELECT precio, informe, fecha_creacion, fecha_agendada, estado, e.nombre as especialidad, u.nombre as medico
FROM consulta c
INNER JOIN especialidad e
ON c.especialidad = e.id
INNER JOIN medico m
ON c.medico = m.id
INNER JOIN usuario u
ON m.id = u.id
WHERE paciente = 111 
AND e.nombre = 'EspecialidadC';

SELECT precio, informe, fecha_creacion, fecha_agendada, estado, e.nombre as especialidad, u.nombre as medico
FROM consulta c
INNER JOIN especialidad e
ON c.especialidad = e.id
INNER JOIN medico m
ON c.medico = m.id
INNER JOIN usuario u
ON m.id = u.id
WHERE paciente = 111 AND fecha_creacion BETWEEN '2023-03-03' AND '2023-03-03'
AND e.nombre = 'EspecialidadC';

*****

SELECT precio, informe, fecha_creacion, fecha_agendada, estado, e.nombre
FROM consulta c
INNER JOIN especialidad e
ON c.especialidad = e.id
WHERE paciente = 111;

SELECT precio, informe, fecha_creacion, fecha_agendada, estado, e.nombre
FROM consulta c
INNER JOIN especialidad e
ON c.especialidad = e.id
WHERE paciente = 111 AND fecha_creacion BETWEEN ? AND ?;


SELECT precio, informe, fecha_creacion, fecha_agendada, estado, e.nombre
FROM consulta c
INNER JOIN especialidad e
ON c.especialidad = e.id
WHERE paciente = 111 
AND e.nombre = 'EspecialidadC';

SELECT precio, informe, fecha_creacion, fecha_agendada, estado, e.nombre
FROM consulta c
INNER JOIN especialidad e
ON c.especialidad = e.id
WHERE paciente = 111 AND fecha_creacion BETWEEN '2023-03-03' AND '2023-03-03'
AND e.nombre = 'EspecialidadC';


4. reporte de exámenes

SELECT s.id as solicitud, fecha_solicitado, fecha_finalizada, t.nombre as examen, estado, u.nombre, e.precio 
FROM solicitud_examen s
INNER JOIN examenes_solicitud e
ON s.id = e.solicitud
INNER JOIN tipo_examen t
ON t.id = e.tipo_examen
INNER JOIN laboratorio l
ON l.id = s.laboratorio
INNER JOIN usuario u
ON l.id = u.id
WHERE paciente = 222;

SELECT s.id as solicitud, fecha_solicitado, fecha_finalizada, t.nombre as examen, estado, u.nombre, e.precio
FROM solicitud_examen s
INNER JOIN examenes_solicitud e
ON s.id = e.solicitud
INNER JOIN tipo_examen t
ON t.id = e.tipo_examen
INNER JOIN laboratorio l
ON l.id = s.laboratorio
INNER JOIN usuario u
ON l.id = u.id
WHERE paciente = 222
AND fecha_solicitado BETWEEN ? AND ?;

SELECT s.id as solicitud, fecha_solicitado, fecha_finalizada, t.nombre as examen, estado, u.nombre, e.precio
FROM solicitud_examen s
INNER JOIN examenes_solicitud e
ON s.id = e.solicitud
INNER JOIN tipo_examen t
ON t.id = e.tipo_examen
INNER JOIN laboratorio l
ON l.id = s.laboratorio
INNER JOIN usuario u
ON l.id = u.id
WHERE paciente = 222 
AND t.nombre = 'Nombre tipo examen C';

SELECT s.id as solicitud, fecha_solicitado, fecha_finalizada, t.nombre as examen, estado, u.nombre, e.precio
FROM solicitud_examen s
INNER JOIN examenes_solicitud e
ON s.id = e.solicitud
INNER JOIN tipo_examen t
ON t.id = e.tipo_examen
INNER JOIN laboratorio l
ON l.id = s.laboratorio
INNER JOIN usuario u
ON l.id = u.id
WHERE paciente = 222
AND fecha_solicitado BETWEEN '2023-04-01' AND '2023-05-10'
AND t.nombre = 'Nombre tipo examen C';

REPORTE LABORATORIO

2. top pacientes

SELECT SUM(ganancia_lab) AS ganancia, COUNT(paciente) AS cantidad, u.nombre as paciente, u.id
FROM solicitud_examen s
INNER JOIN paciente p
ON s.paciente = p.id
INNER JOIN usuario u
ON p.id = u.id
WHERE laboratorio = 33
GROUP BY paciente
ORDER BY SUM(ganancia_lab) DESC
LIMIT 5;

SELECT SUM(ganancia_lab) AS ganancia, COUNT(paciente) AS cantidad, u.nombre as paciente, u.id
FROM solicitud_examen s
INNER JOIN paciente p
ON s.paciente = p.id
INNER JOIN usuario u
ON p.id = u.id
WHERE laboratorio = 33 AND s.fecha_solicitado BETWEEN '2023-03-02' AND '2023-05-01'
GROUP BY paciente
ORDER BY SUM(ganancia_lab) DESC
LIMIT 5;

3. top exámenes

SELECT SUM(precio) AS ganancia, COUNT(tipo_examen) AS cantidad, t.nombre as examen, t.id
FROM examenes_solicitud e
INNER JOIN solicitud_examen s
ON e.solicitud = s.id
INNER JOIN tipo_examen t
ON e.tipo_examen = t.id
WHERE s.laboratorio = 33 
GROUP BY tipo_examen
ORDER BY SUM(precio) DESC
LIMIT 5;

SELECT SUM(precio) AS ganancia, COUNT(tipo_examen) AS cantidad, t.nombre as examen, t.id
FROM examenes_solicitud e
INNER JOIN solicitud_examen s
ON e.solicitud = s.id
INNER JOIN tipo_examen t
ON e.tipo_examen = t.id
WHERE s.laboratorio = 33 AND s.fecha_solicitado BETWEEN '2023-03-02' AND '2023-05-01'
GROUP BY tipo_examen
ORDER BY SUM(precio) DESC
LIMIT 5;

UTILES 

1. especialidad por medico
SELECT nombre e, descripcion e, precio p
FROM especialidad e
INNER JOIN medico_especialidad p
ON p.id_especialidad = e.id
INNER JOIN medico m
ON m.id = p.medico
WHERE p.medico = 1;

2. examens por laboratorio
SELECT nombre e, descripcion e, precio p
FROM especialidad e
INNER JOIN medico_especialidad p
ON p.id_especialidad = e.id
INNER JOIN medico m
ON m.id = p.medico
WHERE p.medico = 1;


