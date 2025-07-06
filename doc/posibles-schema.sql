create table if not exists rol (
	id bigint auto_increment not null primary key,
	nombre varchar(255) not null unique
);

insert into rol(nombre) values
('ADMIN'),
('DEV'),
('EMPLEADO')
on duplicate key update nombre = nombre;

create table if not exists empleado (
	id bigint auto_increment not null primary key,
	nombre varchar(255) not null,
	username varchar(255) not null unique,
	password varchar(255) not null,
	id_rol bigint not null,
	constraint fk_empleado_rol foreign key (id_rol) references rol(id)
);

insert into empleado(nombre, username, password, id_rol) values
('Sol', 'solpm', 'tinta2025!', 1),
('Andrea', 'andreapm', 'tinta2025!', 1),
('Ben', 'benpm', 'BenYMar2605!', 2),
('Anto', 'antopm', 'tinta2025!', 3),
('Jose', 'josepm', 'tinta2025!', 3),
('Myrna', 'myrnapm', 'tinta2025!', 3),
('Meli', 'melipm', 'tinta2025!', 3),
('Mari', 'maripm', 'tinta2025!', 3);

-- agendas

-- anotadores

-- carpetas con solapas

-- catálogos

-- cierra bolsas

-- cuadernos anillados

-- entradas

-- etiquetas

-- folletos
create table if not exists tipo_color_folleto (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_color_folleto(id, tipo) values
(1, 'BLANCO Y NEGRO'),
(2, 'A COLOR');

create table if not exists tamanio_hoja_folleto (
    id bigint auto_increment not null primary key,
    tamanio varchar(255) not null
);

insert into tamanio_hoja_folleto(id, tamanio) values
(1, 'A4'),
(2, 'A5'),
(3, '1/4 DE A4'),
(4, 'A3'),
(5, 'OFICIO');

create table if not exists cantidad_estandar_folleto (
    id bigint auto_increment not null primary key,
    cantidad varchar(255) not null
);

insert into cantidad_estandar_folleto(id, cantidad) values
(1, '50'),
(2, '100'),
(3, '150'),
(4, '200'),
(5, '300'),
(6, '400'),
(7, '500'),
(8, '1000'),
(9, 'OTRA');

create table if not exists tipo_papel_folleto (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_papel_folleto(id, tipo) values
(1, 'OBRA 75 GRS'),
(2, 'ILUSTRACIÓN 115 GRS'),
(3, 'ILUSTRACIÓN 150 GRS');

create table if not exists tipo_folleto (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_folleto(id, tipo) values
(1, 'COMÚN'),
(2, 'DÍPTICO'),
(3, 'TRÍPTICO');

-- hojas membreteadas

-- impresiones
create table if not exists tipo_color_impresion (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_color_impresion(id, tipo) values
(1, 'BLANCO Y NEGRO'),
(2, 'A COLOR');

-- lonas

-- lonas publicitarias

-- rifas / bonos contribución

-- rotulaciones
create table if not exists tipo_rotulacion (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_rotulacion(id, tipo) values
(1, 'ARLON BRILLANTE'),
(2, 'ARLON MATE'),
(3, 'CLEAR'),
(4, 'MICROPERFORADO'),
(5, 'VINILO DE CORTE');

-- sellos automáticos
create table if not exists modelo_sello_automatico (
    id bigint auto_increment not null primary key,
    modelo varchar(255) not null
);

insert into modelo_sello_automatico(id, modelo) values
(1, 'MOUSE STAMP 20 COLOP - 14X38 MM'),
(2, 'POCKET 20 COLOP - 14X38 MM'),
(3, 'AUT 20 COLOP - 14X38 MM'),
(4, 'AUT 10 COLOP - 10X27 MM'),
(5, 'PRINTER C30 - 18X47 MM'),
(6, 'PRINTER 45 - 82X25 MM'),
(7, 'PRINTER C50 - 30X69 MM'),
(8, 'PRINTER 55 DATER - 40X60 MM'),
(9, 'PRINTER C60 - 76X37 MM'),
(10, 'S260 - 45X24 MM'),
(11, 'MINI DATER S120 - 40X45 MM'),
(12, 'MINI DATER S160 - 40X20 MM'),
(13, 'PRINTER R30'),
(13, 'PRINTER R40');

-- sellos automáticos escolares
create table if not exists modelo_sello_automatico_escolar (
    id bigint auto_increment not null primary key,
    modelo varchar(255) not null
);

insert into modelo_sello_automatico_escolar(id, modelo) values
(1, '10X27 MM - 10 COLOP'),
(2, '14X38 MM - 20 COLOP');

-- sellos de madera
create table if not exists tamanio_sello_madera (
    id bigint auto_increment not null primary key,
    tamanio varchar(255) not null
);

insert into tamanio_sello_madera(id, tamanio) values
(1, '6X3 CM'),
(2, '6X4 CM'),
(3, '6X5 CM'),
(4, '6X7 CM'),
(5, '6X8 CM'),
(6, '7X3 CM'),
(7, '7X5 CM'),
(8, '1X1 CM'),
(9, '2X2 CM'),
(10, '3X3 CM'),
(11, '4X4 CM'),
(12, '5X5 CM'),
(13, '6X6 CM'),
(14, '7X7 CM'),
(15, '8X8 CM'),
(16, '9X9 CM'),
(17, '10X10 CM'),
(18, '11X11 CM'),
(19, '12X12 CM'),
(20, '13X13 CM'),
(21, '14X14 CM'),
(22, '15X15 CM'),
(23, '10X15 CM'),
(24, 'OTRO');

-- sobres
create table if not exists tipo_color_sobre (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_color_sobre(id, tipo) values
(1, 'BLANCO Y NEGRO'),
(2, 'A COLOR');

-- stickers

-- sublimaciones
create table if not exists material_sublimacion (
    id bigint auto_increment not null primary key,
    material varchar(255) not null
);

insert into material_sublimacion(id, material) values
(1, 'BIROME'),
(2, 'TAZA DE POLÍMERO'),
(3, 'TAZA DE CERÁMICA'),
(4, 'TAZA DE CERÁMICA CON DETALLE'),
(5, 'TAZA MÁGICA'),
(6, 'TELA'),
(7, 'MATE DE POLÍMERO'),
(8, 'MATE DE CERÁMICA'),
(9, 'JARRO DE CAFÉ DE POLÍMERO'),
(10, 'LAPICERO DE POLÍMERO'),
(11, 'CINTA FALLETINA'),
(12, 'LLAVERO CORTO LANYARD'),
(13, 'LLAVERO LARGO LANYARD'),
(14, 'MOUSEPAD');

-- talonarios (presupuestos, x, recibos)
create table if not exists modo_talonario (
    id bigint auto_increment not null primary key,
    modo varchar(255) not null
);

insert into modo_talonario(id, modo) values
(1, 'ORIGINAL'),
(2, 'ORIGINAL DUPLICADO');

create table if not exists tipo_talonario (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_talonario(id, tipo) values
(1, 'RECIBO'),
(2, 'PRESUPUESTO'),
(3, 'X');

create table if not exists tipo_papel_talonario (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_papel_talonario(id, tipo) values
(1, 'OBRA 75 GRS'),
(2, 'ILUSTRACIÓN 150 GRS');

create table if not exists medida_talonario (
    id bigint auto_increment not null primary key,
    medida varchar(255) not null
);

insert into medida_talonario(id, medida) values
(1, '1/4 DE A4'),
(2, 'A4'),
(3, 'A5'),
(4, '1/2 DE A4'),
(5, 'OTRA');

create table if not exists tipo_troquelado_talonario (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_troquelado_talonario(id, tipo) values
(1, 'SIN TROQUELAR'),
(2, 'TROQUELADO SIMPLE'),
(3, 'TROQUELADO DOBLE');

create table if not exists tipo_color_talonario (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_color_talonario(id, tipo) values
(1, 'BLANCO Y NEGRO'),
(2, 'A COLOR');

-- tarjetas
create table if not exists tipo_color_tarjeta (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_color_tarjeta(id, tipo) values
(1, 'BLANCO Y NEGRO'),
(2, 'A COLOR');

create table if not exists tipo_papel_tarjeta (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_papel_tarjeta(id, tipo) values
(1, 'OPALINA 180 GRS'),
(2, 'OPALINA 210 GRS'),
(3, 'ILUSTRACIÓN 250 GRS'),
(4, 'ILUSTRACIÓN 300 GRS');

create table if not exists medida_tarjeta (
    id bigint auto_increment not null primary key,
    medida varchar(255) not null
);

insert into medida_tarjeta(id, medida) values
(1, '9X5 CM'),
(2, 'OTRA');

create table if not exists cantidad_estandar_tarjeta (
    id bigint auto_increment not null primary key,
    cantidad varchar(255) not null
);

insert into cantidad_estandar_tarjeta(id, cantidad) values
(1, '50'),
(2, '100'),
(3, '150'),
(4, '200'),
(5, '300'),
(6, '400'),
(7, '500'),
(8, '1000'),
(9, 'OTRA');

-- turneros / r.p
create table if not exists tipo_color_turnero (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_color_turnero(id, tipo) values
(1, 'BLANCO Y NEGRO'),
(2, 'A COLOR');

-- vinilos

-- vinilos + plástico corrugado

-- vinilos de corte

-- otros
create table if not exists tipo_color_otro (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_color_otro(id, tipo) values
(1, 'BLANCO Y NEGRO'),
(2, 'A COLOR');