-- me aseguro de usar la bbdd correcta
use wll8xcj0g5jv7pef;

-- creacion de tablas iniciales
create table if not exists rol (
	id bigint auto_increment not null primary key,
	nombre varchar(255) not null unique
);

create table if not exists empleado(
	id bigint auto_increment not null primary key,
	nombre varchar(255) not null,
	username varchar(255) not null unique,
	password varchar(255) not null,
	id_rol bigint not null,
	constraint fk_empleado_rol foreign key (id_rol) references rol(id)
);

-- insertar datos a las tablas iniciales
insert into rol(nombre) values
('ADMIN'),
('DEV'),
('EMPLEADO')
on duplicate key update nombre = nombre;

insert into empleado(nombre, username, password, id_rol) values
('sol', 'solpm', 'tinta2025!', 1),
('andrea', 'andreapm', 'tinta2025!', 1),
('ben', 'benpm', 'BenYMar2605!', 2),
('anto', 'antopm', 'tinta2025!', 3),
('jose', 'josepm', 'tinta2025!', 3),
('myrna', 'myrnapm', 'tinta2025!', 3),
('meli', 'melipm', 'tinta2025!', 3),
('mari', 'maripm', 'tinta2025!', 3);


