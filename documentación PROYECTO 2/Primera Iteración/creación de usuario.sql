 /*usuario para la conexión a la base de datos store*/
CREATE USER 'hospitalUser'@'localhost' IDENTIFIED BY 'adminHospital1234@';
GRANT ALL PRIVILEGES ON hospital.* TO 'hospitalUser'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;
