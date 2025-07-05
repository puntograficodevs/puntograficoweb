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

-- tipos de producto (categorías)
create table if not exists tipo_producto (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null unique
);

insert into tipo_producto(id, tipo) values
(1, 'AGENDAS'), -- listo
(2, 'ANOTADORES'), -- listo
(3, 'CARPETAS CON SOLAPAS'), -- listo
(4, 'CATÁLOGO'), -- listo
(5, 'CIERRA BOLSAS'), -- listo
(6, 'CUADERNOS ANILLADOS'), -- listo
(7, 'ENTRADAS'), -- listo
(8, 'ETIQUETAS'), -- listo
(9, 'FOLLETOS'), -- listo
(10, 'HOJAS MEMBRETEADAS'), -- listo
(11, 'IMPRESIONES'), -- listo
(12, 'LONAS'), -- listo
(13, 'LONAS PUBLICITARIAS'), -- listo
(14, 'RIFAS/BONOS CONTRIBUCIÓN'), -- listo
(15, 'ROTULACIONES'),
(16, 'SELLOS AUTOMÁTICOS'),
(17, 'SELLOS AUTOMÁTICOS ESCOLARES'),
(18, 'SELLOS DE MADERA'),
(19, 'SOBRES'),
(20, 'STICKERS'),
(21, 'SUBLIMACIONES'),
(22, 'TALONARIOS (PRESUPUESTOS, X, RECIBOS)'),
(23, 'TARJETAS'),
(24, 'TURNEROS/R.P'),
(25, 'VINILOS'),
(26, 'VINILOS + PLÁSTICO CORRUGADO'),
(27, 'VINILOS DE CORTE'),
(28, 'OTROS');

-- agendas
create table if not exists agenda (
    id bigint auto_increment not null primary key,
    medida varchar(255) not null,
    tipo_tapa varchar(255) not null,
    cantidad_hojas int not null,
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int not null default 0,
    cantidad int not null default 1,
    enlace_archivo varchar(255) not null default '-',
    informacion_adicional varchar(1000) not null default '-',
    id_tipo_producto bigint not null default 1,
    constraint fk_tipo_producto_agenda foreign key (id_tipo_producto) references tipo_producto(id)
);

-- anotadores
create table if not exists anotador (
    id bigint auto_increment not null primary key,
    medida varchar(255) not null,
    tipo_tapa varchar(255) not null,
    cantidad_hojas int not null,
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int not null default 0,
    cantidad int not null default 1,
    enlace_archivo varchar(255) not null default '-',
    informacion_adicional varchar(1000) not null default '-',
    id_tipo_producto bigint not null default 2,
    constraint fk_tipo_producto_anotador foreign key (id_tipo_producto) references tipo_producto(id)
);

-- tipo de laminado
create table if not exists tipo_laminado (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_laminado(id, tipo) values
(1, 'BRILLANTE'),
(2, 'MATE'),
(3, 'SIN LAMINAR');

-- tipo simple faz o doble faz
create table if not exists tipo_faz (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_faz(id, tipo) values
(1, 'SIMPLE FAZ'),
(2, 'DOBLE FAZ');

-- carpetas con solapas
create table if not exists precio_carpeta_con_solapas (
    id bigint auto_increment not null primary key,
    cantidad int not null,
    precio int not null
);

insert into precio_carpeta_con_solapas(id, cantidad, precio) values
(1, 1, 2850),
(2, 50, 121000),
(3, 100, 217800),
(4, 150, 293700);

create table if not exists carpeta_con_solapas (
    id bigint auto_increment not null primary key,
    tipo_papel_hojas varchar(255) not null,
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int not null default 0,
    cantidad int not null default 1,
    enlace_archivo varchar(255) not null default '-',
    informacion_adicional varchar(1000) not null default '-',
    id_tipo_laminado bigint not null default 3,
    id_tipo_faz bigint not null default 1,
    id_tipo_producto bigint not null default 3,
    constraint fk_tipo_laminado_carpeta_solapas foreign key (id_tipo_laminado) references tipo_laminado(id),
    constraint fk_tipo_faz_carpeta_solapas foreign key (id_tipo_faz) references tipo_faz(id),
    constraint fk_tipo_producto_carpeta_solapas foreign key (id_tipo_producto) references tipo_producto(id)
);

-- catálogo
create table if not exists catalogo (
    id bigint auto_increment not null primary key,
    tipo_papel_hojas varchar(255) not null,
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int not null default 0,
    cantidad int not null default 1,
    enlace_archivo varchar(255) not null default '-',
    informacion_adicional varchar(1000) not null default '-',
    id_tipo_laminado bigint not null default 3,
    id_tipo_faz bigint not null default 1,
    id_tipo_producto bigint not null default 4,
    constraint fk_tipo_laminado_catalogo foreign key (id_tipo_laminado) references tipo_laminado(id),
    constraint fk_tipo_faz_catalogo foreign key (id_tipo_faz) references tipo_faz(id),
    constraint fk_tipo_producto_catalogo foreign key (id_tipo_producto) references tipo_producto(id)
);

-- tipo troquelado
create table if not exists tipo_troquelado (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_troquelado(id, tipo) values
(1, 'CUADRADO'),
(2, 'CIRCULAR'),
(3, 'POR EL CONTORNO'),
(4, 'CORTE INDIVIDUAL'),
(5, 'SIN TROQUELAR');

-- cierra bolsas
create table if not exists precio_cierra_bolsas (
    id bigint auto_increment not null primary key,
    cantidad int not null,
    precio int not null
);

insert into precio_cierra_bolsas(id, cantidad, precio) values
(1, 100, 9850),
(2, 200, 18600),
(3, 500, 43600);

create table if not exists cierra_bolsas (
    id bigint auto_increment not null primary key,
    con_troquelado tinyint(1) not null default 0,
    medida varchar(255) not null default '8X4 CM',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 2500,
    precio int not null default 0,
    cantidad int not null default 1,
    enlace_archivo varchar(255) not null default '-',
    informacion_adicional varchar(1000) not null default '-',
    id_tipo_troquelado bigint not null default 5,
    id_tipo_producto bigint not null default 5,
    constraint fk_tipo_troquelado_cierra_bolsas foreign key (id_tipo_troquelado) references tipo_troquelado(id),
    constraint fk_tipo_producto_cierra_bolsas foreign key (id_tipo_producto) references tipo_producto(id)
);

-- cuadernos anillados
create table if not exists cuaderno_anillado (
    id bigint auto_increment not null primary key,
    medida varchar(255) not null,
    tipo_tapa varchar(255) not null,
    cantidad_hojas int not null,
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int not null default 0,
    cantidad int not null default 1,
    enlace_archivo varchar(255) not null default '-',
    informacion_adicional varchar(1000) not null default '-',
    id_tipo_producto bigint not null default 6,
    constraint fk_tipo_producto_cuaderno_anillado foreign key (id_tipo_producto) references tipo_producto(id)
);

-- tipo troquelado talonarios
create table if not exists tipo_troquelado_talonario (
    id bigint auto_increment not null primary key,
    tipo varchar(255)
);

insert into tipo_troquelado_talonario(id, tipo) values
(1, 'TROQUEL SIMPLE'),
(2, 'DOBLE TROQUEL'),
(3, 'SIN TROQUELAR');

-- tipo numerado
create table if not exists tipo_numerado (
    id bigint auto_increment not null primary key,
    tipo varchar(255)
);

insert into tipo_numerado(id, tipo) values
(1, 'NUMERADO SIMPLE'),
(2, 'DOBLE NUMERADO'),
(3, 'SIN NUMERADO');

-- tipo color
create table if not exists tipo_color (
    id bigint auto_increment not null primary key,
    tipo varchar(255)
);

insert into tipo_color(id, tipo) values
(1, 'BLANCO Y NEGRO'),
(2, 'COLOR');

-- tipo papel talonario
create table if not exists tipo_papel_talonario (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_papel_talonario(id, tipo) values
(1, 'OBRA 75GRS'),
(2, 'ILUSTRACIÓN 150GRS');

-- entradas
create table if not exists precio_entradas (
    id bigint auto_increment not null primary key,
    id_tipo_papel_talonario not null,
    id_tipo_color bigint not null,
    id_tipo_troquelado_talonario bigint not null,
    id_tipo_numerado bigint not null,
    medida varchar(255) not null,
    cantidad int not null,
    precio int not null,
    constraint fk_tipo_papel_precio_entradas foreign key (id_tipo_papel_talonario) references tipo_papel_talonario(id),
    constraint fk_tipo_color_precio_entradas foreign key (id_tipo_color) references tipo_calor(id),
    constraint fk_tipo_troquelado_preico_entradas foreign key (id_tipo_troquelado_talonario) references tipo_troquelado_talonario(id),
    constraint fk_tipo_numerado_precio_entradas foreign key (id_tipo_numerado) references tipo_numerado(id)
);

insert into precio_entradas(id, id_tipo_papel_talonario, id_tipo_color, id_tipo_troquelado_talonario, id_tipo_numerado, medida, cantidad, precio) values
(1, 2, 2, 2, 2, '17X6 CM', 100, 29800),
(2, 2, 2, 2, 2, '17X6 CM', 150, 43200),
(3, 2, 2, 2, 2, '17X6 CM', 200, 58900),
(4, 2, 2, 2, 2, '17X6 CM', 300, 82500),
(5, 2, 2, 2, 2, '17X6 CM', 500, 138700),
(6, 2, 2, 2, 2, '17X6 CM', 1000, 255000);

create table if not exists entrada (
    id bigint auto_increment not null primary key,
    medida varchar(255) not null default '17X6 CM',
    detalle_numerado varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int not null default 0,
    cantidad int not null default 1,
    enlace_archivo varchar(255) not null default '-',
    informacion_adicional varchar(1000) not null default '-',
    id_tipo_troquelado_talonario bigint not null default 3,
    id_tipo_numerado bigint not null default 3,
    id_tipo_color bigint not null default 1,
    id_tipo_papel_talonario not null default 2,
    id_tipo_producto bigint not null default 7,
    constraint fk_tipo_troquelado_talonario_entrada foreign key (id_tipo_troquelado_talonario) references tipo_troquelado_talonario(id),
    constraint fk_tipo_numerado_entrada foreign key (id_tipo_numerado) references tipo_numerado(id),
    constraint fk_tipo_color_entrada foreign key (id_tipo_color) references tipo_color(id),
    constraint fk_tipo_papel_entrada foreign key (id_tipo_papel_talonario) references tipo_papel_talonario(id),
    constraint fk_tipo_producto_entrada foreign key (id_tipo_producto) references tipo_producto(id)
);

-- tipo papel etiqueta
create table if not exists tipo_papel_etiqueta (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_papel_etiqueta(id, tipo) values
(1, 'OPALINA 180GRS'),
(2, 'OPALINA 210GRS'),
(3, 'ILUSTRACIÓN 250GRS'),
(4, 'KRAFT'),
(5, 'ILUSTRACIÓN 220GRS');

-- etiquetas
create table if not exists tamanio_perforacion (
    id bigint auto_increment not null primary key,
    tamanio varchar(255) not null
);

insert into tamanio_perforacion(id, tamanio) values
(1, 'CHICA'),
(2, 'MEDIANA'),
(3, 'GRANDE');

create table if not exists precio_etiquetas (
    id bigint auto_increment not null primary key,
    con_perforacion tinyint(1) not null,
    id_tipo_papel_etiqueta bigint not null,
    id_tipo_faz bigint not null default 1,
    cantidad int not null,
    precio int not null,
    constraint fk_tipo_papel_etiqueta foreign key (id_tipo_papel_etiqueta) references tipo_papel_etiqueta(id),
    constraint fk_tipo_faz_etiqueta foreign key (id_tipo_faz) references tipo_faz(id)
);

insert into precio_etiquetas(id, con_perforacion, id_tipo_papel_etiqueta, id_tipo_faz, cantidad, precio) values
(1, 0, 4, 1, 100, 9700),
(2, 0, 4, 1, 200, 17050),
(3, 0, 4, 1, 500, 40150),
(4, 0, 4, 1, 1000, 70100),
(5, 0, 4, 2, 100, 11150),
(6, 0, 4, 2, 200, 19750),
(7, 0, 4, 2, 500, 46200),
(8, 0, 4, 2, 1000, 88250),
(9, 0, 5, 1, 100, 9700),
(10, 0, 5, 1, 200, 17050),
(11, 0, 5, 1, 500, 40150),
(12, 0, 5, 1, 1000, 70100),
(13, 0, 5, 2, 100, 11150),
(14, 0, 5, 2, 200, 19750),
(15, 0, 5, 2, 500, 46200),
(16, 0, 5, 2, 1000, 88250),
(17, 0, 1, 1, 100, 7950),
(18, 0, 1, 1, 200, 14100),
(19, 0, 1, 1, 500, 33200),
(20, 0, 1, 1, 1000, 59250),
(21, 0, 1, 2, 100, 9700),
(22, 0, 1, 2, 200, 17150),
(23, 0, 1, 2, 500, 40150),
(24, 0, 1, 2, 1000, 70100);

create table if not exists etiqueta (
    id bigint auto_increment not null primary key,
    medida varchar(255) not null default '7X5 CM',
    con_perforacion tinyint(1) not null default 0,
    con_marca_adicional tinyint(1) not null default 0,
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 3500,
    precio int not null default 0,
    cantidad int not null default 1,
    enlace_archivo varchar(255) not null default '-',
    informacion_adicional varchar(1000) not null default '-',
    id_tipo_papel_etiqueta bigint not null,
    id_tipo_laminado bigint not null default 3,
    id_tamanio_perforacion bigint null,
    id_tipo_faz bigint not null default 1,
    id_tipo_producto bigint not null default 8,
    constraint fk_tipo_papel_etiqueta foreign key (id_tipo_papel_etiqueta) references tipo_papel_etiqueta(id),
    constraint fk_tipo_laminado_etiqueta foreign key (id_tipo_laminado) references tipo_laminado(id),
    constraint fk_tamanio_perforacion_etiqueta foreign key (id_tamanio_perforacion) references tamanio_perforacion(id),
    constraint fk_tipo_faz_etiqueta foreign key (id_tipo_faz) references tipo_faz(id),
    constraint fk_tipo_producto_etiqueta foreign key (id_tipo_producto) references tipo_producto(id)
);

-- folletos
create table if not exists tipo_papel_folleto (
    id bigint auto_increment primary key not null,
    tipo varchar(255) not null
);

insert into tipo_papel_folleto(id, tipo) values
(1, 'OBRA 75 GRS'),
(2, 'ILUSTRACION 115 GRS'),
(3, 'ILUSTRACION 150 GRS');

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

create table if not exists tipo_folleto (
    id  bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_folleto(id, tipo) values
(1, 'COMÚN'),
(2, 'DÍPTICO'),
(3, 'TRÍPTICO');

create table if not exists cantidad_folletos (
    id bigint auto_increment not null primary key,
    cantidad int not null
);

insert into cantidad_folletos(id, cantidad) values
(1, 50),
(2, 100),
(3, 150),
(4, 200),
(5, 300),
(6, 400),
(7, 500),
(8, 1000);

create table if not exists precio_folletos(
    id bigint auto_increment not null primary key,
    precio int not null,
    id_tipo_papel_folleto bigint not null,
    id_tipo_color_folleto bigint not null,
    id_tamanio_hoja_folleto bigint not null,
    id_cantidad_folletos bigint not null,
    id_tipo_faz bigint not null,
    constraint fk_tipo_papel_precio_folleto foreign key (id_tipo_papel_folleto) references tipo_papel_folleto(id),
    constraint fk_tipo_color_precio_folleto foreign key (id_tipo_color_folleto) references tipo_color(id),
    constraint fk_tamanio_hoja_precio_folleto foreign key (id_tamanio_hoja_folleto) references tamanio_hoja_folleto(id),
    constraint fk_cantidad_precio_folletos foreign key (id_cantidad_folletos) references cantidad_folletos(id),
    constraint fk_tipo_faz_precio_folletos foreign key (id_tipo_faz) references tipo_faz(id)
);

insert into precio_folletos(id, id_tipo_papel_folleto, id_tipo_color_folleto, id_tamanio_hoja_folleto, id_cantidad_folletos, id_tipo_faz, precio) values
(1, 1, 1, 3, 2, 1, 6400),
(2, 1, 1, 3, 2, 2, 7600),
(3, 1, 1, 3, 4, 1, 9750),
(4, 1, 1, 3, 4, 2, 13500),
(5, 1, 1, 3, 7, 1, 21900),
(6, 1, 1, 3, 7, 2, 27300),
(7, 1, 1, 3, 8, 1, 41100),
(8, 1, 1, 3, 8, 2, 47500),
(9, 1, 2, 3, 2, 1, 14850),
(10, 1, 2, 3, 2, 2, 16750),
(11, 1, 2, 3, 4, 1, 26700),
(12, 1, 2, 3, 4, 2, 29800),
(13, 1, 2, 3, 7, 1, 64000),
(14, 1, 2, 3, 7, 2, 77500),
(15, 1, 2, 3, 8, 1, 111400),
(16, 1, 2, 3, 8, 2, 139600),
(17, 2, 2, 3, 2, 1, 18150),
(18, 2, 2, 3, 2, 2, 21600),
(19, 2, 2, 3, 4, 1, 31950),
(20, 2, 2, 3, 4, 2, 36900),
(21, 2, 2, 3, 7, 1, 77550),
(22, 2, 2, 3, 7, 2, 87100),
(23, 2, 2, 3, 8, 1, 140450),
(24, 2, 2, 3, 8, 2, 159100);

create table if not exists folleto (
    id bigint auto_increment not null primary key,
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    con_plegado tinyint(1) not null default 0,
    precio int not null default 0,
    cantidad int not null default 1,
    enlace_archivo varchar(255) not null default '-',
    informacion_adicional varchar(1000) not null default '-',
    id_tipo_papel_folleto bigint not null,
    id_tipo_color bigint not null,
    id_tipo_faz bigint not null,
    id_tamanio_hoja_folleto bigint not null,
    id_tipo_folleto bigint not null,
    id_cantidad_folletos bigint not null,
    id_tipo_producto bigint not null default 9,
    constraint fk_tipo_papel_folleto foreign key (id_tipo_papel_folleto) references tipo_papel_folleto(id),
    constraint fk_tipo_color_folleto foreign key (id_tipo_color) references tipo_color(id),
    constraint fk_tipo_faz_folleto foreign key (id_tipo_faz) references tipo_faz(id),
    constraint fk_tamanio_hoja_folleto foreign key (id_tamanio_hoja_folleto) references tamanio_hoja_folleto(id),
    constraint fk_tipo_folleto foreign key (id_tipo_folleto) references tipo_folleto(id),
    constraint fk_cantidad_folletos foreign key (id_cantidad_folletos) references cantidad_folletos(id),
    constraint fk_tipo_producto_folleto foreign key (id_tipo_producto) references tipo_producto(id)
);

-- hojas membreteadas
create table if not exists precio_hojas_membreteadas (
    id bigint auto_increment not null primary key,
    con_encolado tinyint(1) not null,
    cantidad_hojas int not null,
    cantidad_talonarios int not null,
    medida varchar(255) not null,
    precio int not null,
    id_tipo_color bigint not null,
    constraint fk_color_hojas_membreteadas foreign key (id_tipo_color) references tipo_color(id),
);

insert into precio_hojas_membreteadas(id, con_encolado, cantidad_hojas, cantidad_talonarios, id_tipo_color, medida, precio) values
(1, 1, 100, 1, 1, 'A4', 11900),
(2, 1, 100, 2, 1, 'A4', 21550),
(3, 1, 100, 4, 1, 'A4', 41000),
(4, 1, 100, 1, 2, 'A4', 36150),
(5, 1, 100, 2, 2, 'A4', 64200),
(6, 1, 100, 4, 2, 'A4', 119900);

create table if not exists hojas_membreteadas (
    id bigint auto_increment not null primary key,
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    con_encolado bigint tinyint(1) not null default 0,
    cantidad_hojas int not null default 100,
    medida varchar(255) not null,
    precio int not null default 0,
    cantidad_talonarios int not null default 1,
    enlace_archivo varchar(255) not null default '-',
    informacion_adicional varchar(1000) not null default '-',
    id_tipo_color bigint not null,
    id_tipo_producto bigint not null default 10,
    constraint fk_color_hoja_membreteada foreign key (id_tipo_color) references tipo_color(id),
    constraint fk_tipo_producto_hojas_membreteadas foreign key (id_tipo_producto) references tipo_producto(id)
);

-- impresiones
create table if not exists tamanio_hoja_impresion (
    id bigint auto_increment not null primary key,
    tamanio varchar(255) not null
);

insert into tamanio_hoja_impresion(id, tamanio) values
(1, 'A4'),
(2, 'A5'),
(3, 'OFICIO'),
(4, 'A3'),
(5, 'A3+');

create table if not exists tipo_papel_impresion (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_papel_impresion(id, tipo) values
(1, 'OBRA'),
(2, 'ILUSTRACIÓN 90 GRS'),
(3, 'ILUSTRACIÓN 115 GRS'),
(4, 'ILUSTRACIÓN 150 GRS'),
(5, 'ILUSTRACIÓN 200 GRS'),
(6, 'ILUSTRACIÓN 220 GRS'),
(7, 'ILUSTRACIÓN 250 GRS'),
(8, 'ILUSTRACIÓN 300 GRS'),
(9, 'OPALINA 150 GRS'),
(10, 'OPALINA 180 GRS'),
(11, 'OPALINA 210 GRS'),
(12, 'KRAFT'),
(13, 'OPALINA 200 GRS');

create table if not exists cantidades_impresion (
    id bigint auto_increment not null primary key,
    cantidad_minima int not null,
    cantidad_maxima int null
);

insert into cantidades_impresion(id, cantidad_minima, cantidad_maxima) values
(1, 1, 10),
(2, 11, 100),
(3, 101, null);

create table if not exists tipo_impresion (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_impresion(id, tipo) values
(1, 'PARTICULAR'),
(2, 'ESCOLAR');

create table if not exists precio_impresion (
    id bigint auto_increment not null primary key,
    id_tipo_papel bigint not null,
    id_tamanio_impresion bigint not null,
    id_tipo_color bigint not null,
    id_tipo_faz bigint not null,
    id_cantidades_impresion bigint not null,
    id_tipo_impresion bigint not null,
    precio int not null,
    constraint fk_precio_tipo_papel foreign key (id_tipo_papel) references tipo_papel_impresion(id),
    constraint fk_precio_tamanio_hoja_impresion foreign key (id_tamanio_impresion) references tamanio_hoja_impresion(id),
    constraint fk_precio_color_impresion foreign key (id_tipo_color) references tipo_color(id),
    constraint fk_precio_faz_impresion foreign key (id_tipo_faz) references tipo_faz(id),
    constraint fk_precio_cantidades_impresion foreign key (id_cantidades_impresion) references cantidades_impresion(id),
    constraint fk_precio_tipo_impresion foreign key (id_tipo_impresion) references tipo_impresion(id)
);

insert into precio_impresion(id_tipo_papel, id_tamanio_impresion, id_tipo_color, id_tipo_faz, id_cantidades_impresion, id_tipo_impresion, precio) values
(1, 1, 1, 1, 1, 1, 100),
(1, 1, 1, 1, 2, 1, 80),
(1, 1, 1, 1, 3, 1, 60),
(1, 1, 1, 2, 1, 1, 150),
(1, 1, 1, 2, 2, 1, 120),
(1, 1, 1, 2, 3, 1, 100),
(1, 3, 1, 1, 1, 1, 150),
(1, 3, 1, 1, 2, 1, 130),
(1, 3, 1, 1, 3, 1, 100),
(1, 3, 1, 2, 1, 1, 170),
(1, 3, 1, 2, 2, 1, 150),
(1, 3, 1, 2, 3, 1, 120),
(1, 1, 1, 1, 1, 2, 60),
(1, 1, 1, 1, 2, 2, 60),
(1, 1, 1, 1, 3, 2, 60),
(1, 1, 1, 2, 1, 2, 80),
(1, 1, 1, 2, 2, 2, 80),
(1, 1, 1, 2, 3, 2, 80),
(1, 3, 1, 1, 1, 2, 80),
(1, 3, 1, 1, 2, 2, 80),
(1, 3, 1, 1, 3, 2, 80),
(1, 3, 1, 2, 1, 2, 100),
(1, 3, 1, 2, 2, 2, 100),
(1, 3, 1, 2, 3, 2, 100),
(1, 1, 2, 1, 1, 2, 400),
(1, 1, 2, 1, 2, 2, 400),
(1, 1, 2, 1, 3, 2, 400),
(1, 1, 2, 2, 1, 2, 650),
(1, 1, 2, 2, 2, 2, 650),
(1, 1, 2, 2, 3, 2, 650),
(1, 3, 2, 1, 1, 2, 500),
(1, 3, 2, 1, 2, 2, 500),
(1, 3, 2, 1, 3, 2, 500),
(1, 3, 2, 2, 1, 2, 750),
(1, 3, 2, 2, 2, 2, 750),
(1, 3, 2, 2, 3, 2, 750),
(1, 1, 2, 1, 1, 1, 500),
(1, 1, 2, 1, 2, 1, 500),
(1, 1, 2, 1, 3, 1, 500),
(1, 1, 2, 1, 1, 2, 500),
(1, 1, 2, 1, 2, 2, 500),
(1, 1, 2, 1, 3, 2, 500),
(1, 1, 2, 2, 1, 1, 800),
(1, 1, 2, 2, 2, 1, 800),
(1, 1, 2, 2, 3, 1, 800),
(1, 1, 2, 2, 1, 2, 800),
(1, 1, 2, 2, 2, 2, 800),
(1, 1, 2, 2, 3, 2, 800),
(2, 1, 2, 1, 1, 1, 900), -- simple faz, 1-10, part
(2, 1, 2, 1, 1, 2, 900), -- simple faz, 1-10, esc
(2, 1, 2, 1, 2, 1, 900), -- simple faz, 10-100, part
(2, 1, 2, 1, 2, 2, 900), -- simple faz, 10-100, esc
(2, 1, 2, 1, 3, 1, 900), -- simple faz, 100+, part
(2, 1, 2, 1, 3, 2, 900), -- simple faz, 100+, esc
(2, 1, 2, 2, 1, 1, 900), -- doble faz, 1-10, part
(2, 1, 2, 2, 1, 2, 900), -- doble faz, 1-10, esc
(2, 1, 2, 2, 2, 1, 900), -- doble faz, 10-100, part
(2, 1, 2, 2, 2, 2, 900), -- doble faz, 10-100, esc
(2, 1, 2, 2, 3, 1, 900), -- doble faz, 100+, part
(2, 1, 2, 2, 3, 2, 900), -- doble faz, 100+, esc
(3, 1, 2, 1, 1, 1, 900), -- simple faz, 1-10, part
(3, 1, 2, 1, 1, 2, 900), -- simple faz, 1-10, esc
(3, 1, 2, 1, 2, 1, 900), -- simple faz, 10-100, part
(3, 1, 2, 1, 2, 2, 900), -- simple faz, 10-100, esc
(3, 1, 2, 1, 3, 1, 900), -- simple faz, 100+, part
(3, 1, 2, 1, 3, 2, 900), -- simple faz, 100+, esc
(3, 1, 2, 2, 1, 1, 900), -- doble faz, 1-10, part
(3, 1, 2, 2, 1, 2, 900), -- doble faz, 1-10, esc
(3, 1, 2, 2, 2, 1, 900), -- doble faz, 10-100, part
(3, 1, 2, 2, 2, 2, 900), -- doble faz, 10-100, esc
(3, 1, 2, 2, 3, 1, 900), -- doble faz, 100+, part
(3, 1, 2, 2, 3, 2, 900), -- doble faz, 100+, esc
(4, 1, 2, 1, 1, 1, 1100), -- simple faz, 1-10, part
(4, 1, 2, 1, 1, 2, 1100), -- simple faz, 1-10, esc
(4, 1, 2, 1, 2, 1, 1100), -- simple faz, 10-100, part
(4, 1, 2, 1, 2, 2, 1100), -- simple faz, 10-100, esc
(4, 1, 2, 1, 3, 1, 1100), -- simple faz, 100+, part
(4, 1, 2, 1, 3, 2, 1100), -- simple faz, 100+, esc
(4, 1, 2, 2, 1, 1, 1100), -- doble faz, 1-10, part
(4, 1, 2, 2, 1, 2, 1100), -- doble faz, 1-10, esc
(4, 1, 2, 2, 2, 1, 1100), -- doble faz, 10-100, part
(4, 1, 2, 2, 2, 2, 1100), -- doble faz, 10-100, esc
(4, 1, 2, 2, 3, 1, 1100), -- doble faz, 100+, part
(4, 1, 2, 2, 3, 2, 1100), -- doble faz, 100+, esc
(5, 1, 2, 1, 1, 1, 1350), -- simple faz, 1-10, part
(5, 1, 2, 1, 1, 2, 1350), -- simple faz, 1-10, esc
(5, 1, 2, 1, 2, 1, 1350), -- simple faz, 10-100, part
(5, 1, 2, 1, 2, 2, 1350), -- simple faz, 10-100, esc
(5, 1, 2, 1, 3, 1, 1350), -- simple faz, 100+, part
(5, 1, 2, 1, 3, 2, 1350), -- simple faz, 100+, esc
(5, 1, 2, 2, 1, 1, 1350), -- doble faz, 1-10, part
(5, 1, 2, 2, 1, 2, 1350), -- doble faz, 1-10, esc
(5, 1, 2, 2, 2, 1, 1350), -- doble faz, 10-100, part
(5, 1, 2, 2, 2, 2, 1350), -- doble faz, 10-100, esc
(5, 1, 2, 2, 3, 1, 1350), -- doble faz, 100+, part
(5, 1, 2, 2, 3, 2, 1350), -- doble faz, 100+, esc
(6, 1, 2, 1, 1, 1, 1350), -- simple faz, 1-10, part
(6, 1, 2, 1, 1, 2, 1350), -- simple faz, 1-10, esc
(6, 1, 2, 1, 2, 1, 1350), -- simple faz, 10-100, part
(6, 1, 2, 1, 2, 2, 1350), -- simple faz, 10-100, esc
(6, 1, 2, 1, 3, 1, 1350), -- simple faz, 100+, part
(6, 1, 2, 1, 3, 2, 1350), -- simple faz, 100+, esc
(6, 1, 2, 2, 1, 1, 1350), -- doble faz, 1-10, part
(6, 1, 2, 2, 1, 2, 1350), -- doble faz, 1-10, esc
(6, 1, 2, 2, 2, 1, 1350), -- doble faz, 10-100, part
(6, 1, 2, 2, 2, 2, 1350), -- doble faz, 10-100, esc
(6, 1, 2, 2, 3, 1, 1350), -- doble faz, 100+, part
(6, 1, 2, 2, 3, 2, 1350), -- doble faz, 100+, esc
(7, 1, 2, 1, 1, 1, 1500), -- simple faz, 1-10, part
(7, 1, 2, 1, 1, 2, 1500), -- simple faz, 1-10, esc
(7, 1, 2, 1, 2, 1, 1500), -- simple faz, 10-100, part
(7, 1, 2, 1, 2, 2, 1500), -- simple faz, 10-100, esc
(7, 1, 2, 1, 3, 1, 1500), -- simple faz, 100+, part
(7, 1, 2, 1, 3, 2, 1500), -- simple faz, 100+, esc
(7, 1, 2, 2, 1, 1, 1500), -- doble faz, 1-10, part
(7, 1, 2, 2, 1, 2, 1500), -- doble faz, 1-10, esc
(7, 1, 2, 2, 2, 1, 1500), -- doble faz, 10-100, part
(7, 1, 2, 2, 2, 2, 1500), -- doble faz, 10-100, esc
(7, 1, 2, 2, 3, 1, 1500), -- doble faz, 100+, part
(7, 1, 2, 2, 3, 2, 1500), -- doble faz, 100+, esc
(8, 1, 2, 1, 1, 1, 1700), -- simple faz, 1-10, part
(8, 1, 2, 1, 1, 2, 1700), -- simple faz, 1-10, esc
(8, 1, 2, 1, 2, 1, 1700), -- simple faz, 10-100, part
(8, 1, 2, 1, 2, 2, 1700), -- simple faz, 10-100, esc
(8, 1, 2, 1, 3, 1, 1700), -- simple faz, 100+, part
(8, 1, 2, 1, 3, 2, 1700), -- simple faz, 100+, esc
(8, 1, 2, 2, 1, 1, 1700), -- doble faz, 1-10, part
(8, 1, 2, 2, 1, 2, 1700), -- doble faz, 1-10, esc
(8, 1, 2, 2, 2, 1, 1700), -- doble faz, 10-100, part
(8, 1, 2, 2, 2, 2, 1700), -- doble faz, 10-100, esc
(8, 1, 2, 2, 3, 1, 1700), -- doble faz, 100+, part
(8, 1, 2, 2, 3, 2, 1700), -- doble faz, 100+, esc
(9, 1, 2, 1, 1, 1, 950), -- simple faz, 1-10, part
(9, 1, 2, 1, 1, 2, 950), -- simple faz, 1-10, esc
(9, 1, 2, 1, 2, 1, 950), -- simple faz, 10-100, part
(9, 1, 2, 1, 2, 2, 950), -- simple faz, 10-100, esc
(9, 1, 2, 1, 3, 1, 950), -- simple faz, 100+, part
(9, 1, 2, 1, 3, 2, 950), -- simple faz, 100+, esc
(9, 1, 2, 2, 1, 1, 950), -- doble faz, 1-10, part
(9, 1, 2, 2, 1, 2, 950), -- doble faz, 1-10, esc
(9, 1, 2, 2, 2, 1, 950), -- doble faz, 10-100, part
(9, 1, 2, 2, 2, 2, 950), -- doble faz, 10-100, esc
(9, 1, 2, 2, 3, 1, 950), -- doble faz, 100+, part
(9, 1, 2, 2, 3, 2, 950), -- doble faz, 100+, esc
(10, 1, 2, 1, 1, 1, 1200), -- simple faz, 1-10, part
(10, 1, 2, 1, 1, 2, 1200), -- simple faz, 1-10, esc
(10, 1, 2, 1, 2, 1, 1200), -- simple faz, 10-100, part
(10, 1, 2, 1, 2, 2, 1200), -- simple faz, 10-100, esc
(10, 1, 2, 1, 3, 1, 1200), -- simple faz, 100+, part
(10, 1, 2, 1, 3, 2, 1200), -- simple faz, 100+, esc
(10, 1, 2, 2, 1, 1, 1200), -- doble faz, 1-10, part
(10, 1, 2, 2, 1, 2, 1200), -- doble faz, 1-10, esc
(10, 1, 2, 2, 2, 1, 1200), -- doble faz, 10-100, part
(10, 1, 2, 2, 2, 2, 1200), -- doble faz, 10-100, esc
(10, 1, 2, 2, 3, 1, 1200), -- doble faz, 100+, part
(10, 1, 2, 2, 3, 2, 1200), -- doble faz, 100+, esc
(11, 1, 2, 1, 1, 1, 1350), -- simple faz, 1-10, part
(11, 1, 2, 1, 1, 2, 1350), -- simple faz, 1-10, esc
(11, 1, 2, 1, 2, 1, 1350), -- simple faz, 10-100, part
(11, 1, 2, 1, 2, 2, 1350), -- simple faz, 10-100, esc
(11, 1, 2, 1, 3, 1, 1350), -- simple faz, 100+, part
(11, 1, 2, 1, 3, 2, 1350), -- simple faz, 100+, esc
(11, 1, 2, 2, 1, 1, 1350), -- doble faz, 1-10, part
(11, 1, 2, 2, 1, 2, 1350), -- doble faz, 1-10, esc
(11, 1, 2, 2, 2, 1, 1350), -- doble faz, 10-100, part
(11, 1, 2, 2, 2, 2, 1350), -- doble faz, 10-100, esc
(11, 1, 2, 2, 3, 1, 1350), -- doble faz, 100+, part
(11, 1, 2, 2, 3, 2, 1350), -- doble faz, 100+, esc
(12, 1, 1, 1, 1, 1, 1400), -- simple faz, 1-10, part
(12, 1, 1, 1, 1, 2, 1400), -- simple faz, 1-10, esc
(12, 1, 1, 1, 2, 1, 1400), -- simple faz, 10-100, part
(12, 1, 1, 1, 2, 2, 1400), -- simple faz, 10-100, esc
(12, 1, 1, 1, 3, 1, 1400), -- simple faz, 100+, part
(12, 1, 1, 1, 3, 2, 1400), -- simple faz, 100+, esc
(12, 1, 1, 2, 1, 1, 1400), -- doble faz, 1-10, part
(12, 1, 1, 2, 1, 2, 1400), -- doble faz, 1-10, esc
(12, 1, 1, 2, 2, 1, 1400), -- doble faz, 10-100, part
(12, 1, 1, 2, 2, 2, 1400), -- doble faz, 10-100, esc
(12, 1, 1, 2, 3, 1, 1400), -- doble faz, 100+, part
(12, 1, 1, 2, 3, 2, 1400), -- doble faz, 100+, esc
(1, 4, 2, 1, 1, 1, 900),
(1, 4, 2, 1, 2, 1, 900),
(1, 4, 2, 1, 3, 1, 900),
(1, 4, 2, 1, 1, 2, 900),
(1, 4, 2, 1, 2, 2, 900),
(1, 4, 2, 1, 3, 2, 900),
(1, 4, 2, 2, 1, 1, 1100),
(1, 4, 2, 2, 2, 1, 1100),
(1, 4, 2, 2, 3, 1, 1100),
(1, 4, 2, 2, 1, 2, 1100),
(1, 4, 2, 2, 2, 2, 1100),
(1, 4, 2, 2, 3, 2, 1100),
(4, 4, 2, 1, 1, 1, 2100), -- simple faz, 1-10, part
(4, 4, 2, 1, 1, 2, 2100), -- simple faz, 1-10, esc
(4, 4, 2, 1, 2, 1, 2100), -- simple faz, 10-100, part
(4, 4, 2, 1, 2, 2, 2100), -- simple faz, 10-100, esc
(4, 4, 2, 1, 3, 1, 2100), -- simple faz, 100+, part
(4, 4, 2, 1, 3, 2, 2100), -- simple faz, 100+, esc
(4, 4, 2, 2, 1, 1, 2100), -- doble faz, 1-10, part
(4, 4, 2, 2, 1, 2, 2100), -- doble faz, 1-10, esc
(4, 4, 2, 2, 2, 1, 2100), -- doble faz, 10-100, part
(4, 4, 2, 2, 2, 2, 2100), -- doble faz, 10-100, esc
(4, 4, 2, 2, 3, 1, 2100), -- doble faz, 100+, part
(4, 4, 2, 2, 3, 2, 2100), -- doble faz, 100+, esc
(5, 4, 2, 1, 1, 1, 2300), -- simple faz, 1-10, part
(5, 4, 2, 1, 1, 2, 2300), -- simple faz, 1-10, esc
(5, 4, 2, 1, 2, 1, 2300), -- simple faz, 10-100, part
(5, 4, 2, 1, 2, 2, 2300), -- simple faz, 10-100, esc
(5, 4, 2, 1, 3, 1, 2300), -- simple faz, 100+, part
(5, 4, 2, 1, 3, 2, 2300), -- simple faz, 100+, esc
(5, 4, 2, 2, 1, 1, 2300), -- doble faz, 1-10, part
(5, 4, 2, 2, 1, 2, 2300), -- doble faz, 1-10, esc
(5, 4, 2, 2, 2, 1, 2300), -- doble faz, 10-100, part
(5, 4, 2, 2, 2, 2, 2300), -- doble faz, 10-100, esc
(5, 4, 2, 2, 3, 1, 2300), -- doble faz, 100+, part
(5, 4, 2, 2, 3, 2, 2300), -- doble faz, 100+, esc
(7, 4, 2, 1, 1, 1, 2500), -- simple faz, 1-10, part
(7, 4, 2, 1, 1, 2, 2500), -- simple faz, 1-10, esc
(7, 4, 2, 1, 2, 1, 2500), -- simple faz, 10-100, part
(7, 4, 2, 1, 2, 2, 2500), -- simple faz, 10-100, esc
(7, 4, 2, 1, 3, 1, 2500), -- simple faz, 100+, part
(7, 4, 2, 1, 3, 2, 2500), -- simple faz, 100+, esc
(7, 4, 2, 2, 1, 1, 2500), -- doble faz, 1-10, part
(7, 4, 2, 2, 1, 2, 2500), -- doble faz, 1-10, esc
(7, 4, 2, 2, 2, 1, 2500), -- doble faz, 10-100, part
(7, 4, 2, 2, 2, 2, 2500), -- doble faz, 10-100, esc
(7, 4, 2, 2, 3, 1, 2500), -- doble faz, 100+, part
(7, 4, 2, 2, 3, 2, 2500), -- doble faz, 100+, esc
(8, 4, 2, 1, 1, 1, 2800), -- simple faz, 1-10, part
(8, 4, 2, 1, 1, 2, 2800), -- simple faz, 1-10, esc
(8, 4, 2, 1, 2, 1, 2800), -- simple faz, 10-100, part
(8, 4, 2, 1, 2, 2, 2800), -- simple faz, 10-100, esc
(8, 4, 2, 1, 3, 1, 2800), -- simple faz, 100+, part
(8, 4, 2, 1, 3, 2, 2800), -- simple faz, 100+, esc
(8, 4, 2, 2, 1, 1, 2800), -- doble faz, 1-10, part
(8, 4, 2, 2, 1, 2, 2800), -- doble faz, 1-10, esc
(8, 4, 2, 2, 2, 1, 2800), -- doble faz, 10-100, part
(8, 4, 2, 2, 2, 2, 2800), -- doble faz, 10-100, esc
(8, 4, 2, 2, 3, 1, 2800), -- doble faz, 100+, part
(8, 4, 2, 2, 3, 2, 2800), -- doble faz, 100+, esc
(9, 4, 2, 1, 1, 1, 1900), -- simple faz, 1-10, part
(9, 4, 2, 1, 1, 2, 1900), -- simple faz, 1-10, esc
(9, 4, 2, 1, 2, 1, 1900), -- simple faz, 10-100, part
(9, 4, 2, 1, 2, 2, 1900), -- simple faz, 10-100, esc
(9, 4, 2, 1, 3, 1, 1900), -- simple faz, 100+, part
(9, 4, 2, 1, 3, 2, 1900), -- simple faz, 100+, esc
(9, 4, 2, 2, 1, 1, 1900), -- doble faz, 1-10, part
(9, 4, 2, 2, 1, 2, 1900), -- doble faz, 1-10, esc
(9, 4, 2, 2, 2, 1, 1900), -- doble faz, 10-100, part
(9, 4, 2, 2, 2, 2, 1900), -- doble faz, 10-100, esc
(9, 4, 2, 2, 3, 1, 1900), -- doble faz, 100+, part
(9, 4, 2, 2, 3, 2, 1900), -- doble faz, 100+, esc
(10, 4, 2, 1, 1, 1, 2100), -- simple faz, 1-10, part
(10, 4, 2, 1, 1, 2, 2100), -- simple faz, 1-10, esc
(10, 4, 2, 1, 2, 1, 2100), -- simple faz, 10-100, part
(10, 4, 2, 1, 2, 2, 2100), -- simple faz, 10-100, esc
(10, 4, 2, 1, 3, 1, 2100), -- simple faz, 100+, part
(10, 4, 2, 1, 3, 2, 2100), -- simple faz, 100+, esc
(10, 4, 2, 2, 1, 1, 2100), -- doble faz, 1-10, part
(10, 4, 2, 2, 1, 2, 2100), -- doble faz, 1-10, esc
(10, 4, 2, 2, 2, 1, 2100), -- doble faz, 10-100, part
(10, 4, 2, 2, 2, 2, 2100), -- doble faz, 10-100, esc
(10, 4, 2, 2, 3, 1, 2100), -- doble faz, 100+, part
(10, 4, 2, 2, 3, 2, 2100), -- doble faz, 100+, esc
(13, 4, 2, 1, 1, 1, 2300), -- simple faz, 1-10, part
(13, 4, 2, 1, 1, 2, 2300), -- simple faz, 1-10, esc
(13, 4, 2, 1, 2, 1, 2300), -- simple faz, 10-100, part
(13, 4, 2, 1, 2, 2, 2300), -- simple faz, 10-100, esc
(13, 4, 2, 1, 3, 1, 2300), -- simple faz, 100+, part
(13, 4, 2, 1, 3, 2, 2300), -- simple faz, 100+, esc
(13, 4, 2, 2, 1, 1, 2300), -- doble faz, 1-10, part
(13, 4, 2, 2, 1, 2, 2300), -- doble faz, 1-10, esc
(13, 4, 2, 2, 2, 1, 2300), -- doble faz, 10-100, part
(13, 4, 2, 2, 2, 2, 2300), -- doble faz, 10-100, esc
(13, 4, 2, 2, 3, 1, 2300), -- doble faz, 100+, part
(13, 4, 2, 2, 3, 2, 2300), -- doble faz, 100+, esc
(12, 4, 1, 1, 1, 1, 2300), -- simple faz, 1-10, part
(12, 4, 1, 1, 1, 2, 2300), -- simple faz, 1-10, esc
(12, 4, 1, 1, 2, 1, 2300), -- simple faz, 10-100, part
(12, 4, 1, 1, 2, 2, 2300), -- simple faz, 10-100, esc
(12, 4, 1, 1, 3, 1, 2300), -- simple faz, 100+, part
(12, 4, 1, 1, 3, 2, 2300), -- simple faz, 100+, esc
(12, 4, 1, 2, 1, 1, 2300), -- doble faz, 1-10, part
(12, 4, 1, 2, 1, 2, 2300), -- doble faz, 1-10, esc
(12, 4, 1, 2, 2, 1, 2300), -- doble faz, 10-100, part
(12, 4, 1, 2, 2, 2, 2300), -- doble faz, 10-100, esc
(12, 4, 1, 2, 3, 1, 2300), -- doble faz, 100+, part
(12, 4, 1, 2, 3, 2, 2300), -- doble faz, 100+, esc
(4, 5, 2, 1, 1, 1, 2600), -- simple faz, 1-10, part
(4, 5, 2, 1, 1, 2, 2600), -- simple faz, 1-10, esc
(4, 5, 2, 1, 2, 1, 2600), -- simple faz, 10-100, part
(4, 5, 2, 1, 2, 2, 2600), -- simple faz, 10-100, esc
(4, 5, 2, 1, 3, 1, 2600), -- simple faz, 100+, part
(4, 5, 2, 1, 3, 2, 2600), -- simple faz, 100+, esc
(4, 5, 2, 2, 1, 1, 2600), -- doble faz, 1-10, part
(4, 5, 2, 2, 1, 2, 2600), -- doble faz, 1-10, esc
(4, 5, 2, 2, 2, 1, 2600), -- doble faz, 10-100, part
(4, 5, 2, 2, 2, 2, 2600), -- doble faz, 10-100, esc
(4, 5, 2, 2, 3, 1, 2600), -- doble faz, 100+, part
(4, 5, 2, 2, 3, 2, 2600), -- doble faz, 100+, esc
(5, 5, 2, 1, 1, 1, 2800), -- simple faz, 1-10, part
(5, 5, 2, 1, 1, 2, 2800), -- simple faz, 1-10, esc
(5, 5, 2, 1, 2, 1, 2800), -- simple faz, 10-100, part
(5, 5, 2, 1, 2, 2, 2800), -- simple faz, 10-100, esc
(5, 5, 2, 1, 3, 1, 2800), -- simple faz, 100+, part
(5, 5, 2, 1, 3, 2, 2800), -- simple faz, 100+, esc
(5, 5, 2, 2, 1, 1, 2800), -- doble faz, 1-10, part
(5, 5, 2, 2, 1, 2, 2800), -- doble faz, 1-10, esc
(5, 5, 2, 2, 2, 1, 2800), -- doble faz, 10-100, part
(5, 5, 2, 2, 2, 2, 2800), -- doble faz, 10-100, esc
(5, 5, 2, 2, 3, 1, 2800), -- doble faz, 100+, part
(5, 5, 2, 2, 3, 2, 2800), -- doble faz, 100+, esc
(7, 5, 2, 1, 1, 1, 3000), -- simple faz, 1-10, part
(7, 5, 2, 1, 1, 2, 3000), -- simple faz, 1-10, esc
(7, 5, 2, 1, 2, 1, 3000), -- simple faz, 10-100, part
(7, 5, 2, 1, 2, 2, 3000), -- simple faz, 10-100, esc
(7, 5, 2, 1, 3, 1, 3000), -- simple faz, 100+, part
(7, 5, 2, 1, 3, 2, 3000), -- simple faz, 100+, esc
(7, 5, 2, 2, 1, 1, 3000), -- doble faz, 1-10, part
(7, 5, 2, 2, 1, 2, 3000), -- doble faz, 1-10, esc
(7, 5, 2, 2, 2, 1, 3000), -- doble faz, 10-100, part
(7, 5, 2, 2, 2, 2, 3000), -- doble faz, 10-100, esc
(7, 5, 2, 2, 3, 1, 3000), -- doble faz, 100+, part
(7, 5, 2, 2, 3, 2, 3000); -- doble faz, 100+, esc

create table if not exists impresion (
    id bigint auto_increment not null primary key,
    es_anillado tinyint(1) not null default 0,
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int not null default 0,
    cantidad int not null default 1,
    enlace_archivo varchar(255) not null default '-',
    informacion_adicional varchar(1000) not null default '-',
    id_tipo_color bigint not null,
    id_tamanio_hoja bigint not null,
    id_tipo_faz bigint not null,
    id_tipo_papel bigint not null,
    id_cantidades_impresion bigint not null,
    id_tipo_impresion bigint not null default 1,
    id_tipo_producto bigint not null default 11,
    constraint fk_color_impresion foreign key (id_tipo_color) references tipo_color(id),
    constraint fk_tamanio_hoja_impresion foreign key (id_tamanio_hoja) references tamanio_hoja_impresion(id),
    constraint fk_tipo_faz_impresion foreign key (id_tipo_faz) references tipo_faz(id),
    constraint fk_tipo_papel_impresion foreign key (id_tipo_papel) references tipo_papel_impresion(id),
    constraint fk_cantidades_impresion foreign key (id_cantidades_impresion) references cantidades_impresion(id),
    constraint fk_tipo_impresion foreign key (id_tipo_impresion) references tipo_impresion(id),
    constraint fk_tipo_producto_impresion foreign key (id_tipo_producto) references tipo_producto(id)
);

-- lona
create table if not exists medida_lona_comun (
    id bigint auto_increment not null primary key,
    medida varchar(255) not null
);

insert into medida_lona_comun (id, medida) values
(1, '40X60 CM'),
(2, '60X90 CM'),
(3, '70X100 CM'),
(4, '90X120 CM')
(5, 'OTRA');

create table if not exists tipo_lona (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_lona(id, tipo) values
(1, 'LONA FRONT'),
(2, 'LONA BACK LIGHT'),
(3, 'LONA BLOCKOUT');

create table if not exists precio_lona (
    id bigint auto_increment not null primary key,
    con_ojales tinyint(1) not null,
    con_ojales_con_refuerzo tinyint(1) not null,
    con_bolsillos tinyint(1) not null,
    con_demasia_para_tensado tinyint(1) not null,
    con_solapado tinyint(1) not null,
    id_medida_lona_comun bigint not null,
    id_tipo_lona bigint not null,
    precio int not null,
    constraint fk_medida_lona_precio foreign key (id_medida_lona_comun) references medida_lona_comun(id),
    constraint fk_tipo_lona_precio foreign key (id_tipo_lona) references tipo_lona(id)
);

insert into precio_lona(con_ojales, con_ojales_refuerzo, con_bolsillos, con_demasiada_para_tensado, con_solapado, id_medida_lona_comun, id_tipo_lona, precio) values
(0, 0, 1, 0, 0, 1, 1, 20440),
(0, 0, 1, 0, 0, 2, 1, 23600),
(0, 0, 1, 0, 0, 3, 1, 27650),
(0, 0, 1, 0, 0, 4, 1, 29930);

create table if not exists lona (
    id bigint auto_increment not null primary key,
    medida_personalizada varchar(255) null,
    con_ojales tinyint(1) not null default 0,
    con_ojales_con_refuerzo tinyint(1) not null default 0,
    con_bolsillos tinyint(1) not null default 0,
    con_demasia_para_tensado tinyint(1) not null default 0,
    con_solapado tinyint(1) not null default 0,
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int not null default 0,
    cantidad int not null default 1,
    enlace_archivo varchar(255) not null default '-',
    informacion_adicional varchar(1000) not null default '-',
    id_medida_lona_comun bigint not null,
    id_tipo_lona bigint not null,
    id_tipo_producto bigint not null default 12,
    constraint fk_medida_lona foreign key (id_medida_lona_comun) references medida_lona_comun(id),
    constraint fk_tipo_lona foreign key (id_tipo_lona) references tipo_lona(id),
    constraint fk_tipo_producto_lona foreign key (id_tipo_producto) references tipo_producto(id)
);

-- lona publicitaria
create table if not exists medida_lona_publicitaria (
    id bigint auto_increment not null primary key,
    medida varchar(255) not null
);

insert into medida_lona_publicitaria(id, medida) values
(1, 'A3 MINI'),
(2, '90X190 CM'),
(3, '60X160 CM'),
(4, '85X200 CM');

create table if not exists precio_lona_publicitaria (
    id bigint auto_increment not null primary key,
    con_ojales tinyint(1) not null default 0,
    con_ojales_con_refuerzo tinyint(1) not null default 0,
    con_bolsillos tinyint(1) not null default 0,
    con_demasia_para_tensado tinyint(1) not null default 0,
    con_solapado tinyint(1) not null default 0,
    con_adicional_portabanner tinyint(1) not null default 0,
    id_medida_lona bigint not null,
    id_tipo_lona bigint not null,
    precio int not null,
    constraint fk_medida_lona_publicitaria_precio foreign key(id_medida_lona) references medida_lona_publicitaria(id)
);

insert into precio_lona_publicitaria(con_ojales, con_ojales_refuerzo, con_bolsillos, con_demasia_para_tensado, con_solapado, con_adicional_portabanner, id_medida_lona, id_tipo_lona, precio) values
(0, 0, 0, 1, 0, 1, 1, 1, 19500),
(0, 0, 0, 1, 0, 1, 2, 1, 81480),
(0, 1, 0, 1, 0, 1, 3, 1, 56016),
(0, 0, 0, 1, 0, 1, 4, 1, 85510),
(0, 1, 0, 0, 0, 0, 1, 1, 15900),
(0, 1, 0, 0, 0, 0, 3, 1, 29800),
(0, 0, 1, 0, 0, 0, 2, 1, 39800),
(0, 0, 0, 1, 0, 0, 4, 1, 42200);

create table if not exists lona_publicitaria (
    id bigint auto_increment not null primary key,
    con_ojales tinyint(1) not null default 0,
    con_ojales_con_refuerzo tinyint(1) not null default 0,
    con_bolsillos tinyint(1) not null default 0,
    con_demasia_para_tensado tinyint(1) not null default 0,
    con_solapado tinyint(1) not null default 0,
    con_adicional_portabanner tinyint(1) not null default 0,
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 8000,
    precio int not null default 0,
    cantidad int not null default 1,
    enlace_archivo varchar(255) not null default '-',
    informacion_adicional varchar(1000) not null default '-',
    id_medida_lona bigint not null,
    id_tipo_lona bigint not null,
    id_tipo_producto bigint not null default 13,
    constraint fk_medida_lona_publicitaria foreign key (id_medida_lona) references medida_lona_publicitaria(1),
    constraint fk_tipo_lona_publicitaria foreign key (id_tipo_lona) references tipo_lona(id),
    constraint fk_tipo_producto_lona_publicitaria foreign key (id_tipo_producto) references tipo_producto(id)
);

-- rifas / bonos contribución
create table if not exists rifa_bono_contribucion (
    id bigint auto_increment not null primary key,
    con_numerado tinyint(1) not null default 0,
    detalle_numerado varchar(255) null,
    es_encolado tinyint(1) not null default 0,
    medida varchar(255) not null,
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int not null default 0,
    cantidad int not null default 1,
    enlace_archivo varchar(255) not null default '-',
    informacion_adicional varchar(1000) not null default '-',
    id_tipo_troquelado bigint not null,
    id_tipo_color bigint not null,
    id_tipo_papel bigint not null,
    id_tipo_producto bigint not null default 14,
    constraint fk_tipo_troquelado_rifas foreign key (id_tipo_troquelado) references tipo_troquelado_talonario(id),
    constraint fk_tipo_color_rifas foreign key (id_tipo_color) references tipo_color(id),
    constraint fk_tipo_papel_rifas foreign key (id_tipo_papel) references tipo_papel_talonario(id),
    constraint fk_tipo_producto_rifa_bono_contribucion foreign key (id_tipo_producto) references tipo_producto(id)
);

-- tablas para ordenes de trabajo
create table if not exists medio_pago (
    id bigint auto_increment not null primary key,
    medio_de_pago varchar(255) not null
);

insert into medio_pago(id, medio_de_pago) values
(1, 'DÉBITO'),
(2, 'CRÉDITO'),
(3, 'TRANSFERENCIA'),
(4, 'EFECTIVO');

create table if not exists estado_pago (
    id bigint auto_increment not null primary key,
    estado_de_pago varchar(255) not null
);

insert into estado_pago(id, estado_de_pago) values
(1, 'SIN PAGAR'),
(2, 'SEÑADO'),
(3, 'PAGADO');

create table if not exists estado_orden (
    id bigint auto_increment not null primary key,
    estado_de_orden varchar(255) not null
);

insert into estado_orden(id, estado_de_orden) values
(1, 'TOMADA'),
(2, 'EN PROCESO'),
(3, 'LISTA PARA RETIRAR'),
(4, 'RETIRADA');

-- orden de trabajo
create table if not exists orden_trabajo (
    id bigint auto_increment not null primary key,
    nombre_cliente varchar(255) not null,
    telefono_cliente varchar(255) not null,
    es_cuenta_corriente tinyint(1) not null default 0,
    fecha_pedido date not null,
    fecha_muestra date null,
    fecha_entrega date not null,
    hora_entrega varchar(255) null,
    necesita_factura tinyint(1) not null default 0,
    total int not null,
    abonado int not null default 0,
    resta int not null,
    id_producto bigint not null, -- se relaciona dinámicamente, no con fk explícita
    id_tipo_producto bigint not null,
    id_medio_pago bigint null,
    id_estado_pago bigint not null default 1,
    id_estado_orden bigint not null default 1,
    id_empleado bigint not null,
    constraint fk_tipo_producto_orden foreign key (id_tipo_producto) references tipo_producto(id),
    constraint fk_medio_pago_orden foreign key (id_medio_pago) references medio_pago(id),
    constraint fk_estado_pago_orden foreign key (id_estado_pago) references estado_pago(id),
    constraint fk_estado_orden foreign key (id_estado_orden) references estado_orden(id),
    constraint fk_empleado_orden foreign key (id_empleado) references empleado(id)
);