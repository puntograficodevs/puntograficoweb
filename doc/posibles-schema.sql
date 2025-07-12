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
create table if not exists tipo_tapa_agenda (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_tapa_agenda(id, tipo) values
(1, 'TAPA DURA'),
(2, 'OTRA');

create table if not exists tipo_color_agenda (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null,
);

insert into tipo_color_agenda(id, tipo) values
(1, 'BLANCO Y NEGRO'),
(2, 'A COLOR');

create table if not exists agenda (
    id bigint auto_increment not null primary key,
    medida varchar(255) not null,
    tipo_tapa_personalizada varchar(255) null,
    cantidad_hojas int not null,
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int null,
    informacion_adicional varchar(255) null,
    id_tipo_tapa_agenda bigint not null,
    id_tipo_color_agenda bigint not null,
    constraint fk_tipo_tapa_agenda foreign key(id_tipo_tapa_agenda) references tipo_tapa_agenda(id),
    constraint fk_tipo_color_agenda foreign key(id_tipo_color_agenda) references tipo_color_agenda(id)
);

create table if not exists plantilla_agenda (
    id bigint auto_increment not null primary key,
    precio int not null,
    cantidad_hojas int not null,
    id_tipo_tapa_agenda bigint not null,
    id_tipo_color_agenda bigint not null,
    constraint fk_plantilla_tipo_tapa_agenda foreign key(id_tipo_tapa_agenda) references tipo_tapa_agenda(id),
    constraint fk_plantilla_tipo_color_agenda foreign key(id_tipo_color_agenda) references tipo_color_agenda(id)
);

insert into plantilla_agenda(cantidad_hojas, id_tipo_tapa_agenda, id_tipo_color_agenda, precio) values
(90, 1, 1, 24850); -- 90 hojas, tapa dura, blanco y negro

-- anotadores
create table if not exists anotador (
    id bigint auto_increment not null primary key,
    medida varchar(255) not null,
    tipo_tapa varchar(255) not null,
    cantidad_hojas int not null,
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int null,
    informacion_adicional varchar(255) null
);

-- carpetas con solapas
create table if not exists tipo_laminado_carpeta_solapa (
    id bigint auto_increment not null primary key,
    laminado varchar(255) not null
);

insert into tipo_laminado_carpeta_solapa(id, laminado) values
(1, 'BRILLANTE'),
(2, 'MATE'),
(3, 'NINGUNO');

create table if not exists tipo_faz_carpeta_solapa (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_faz_carpeta_solapa(id, tipo) values
(1, 'SIMPLE FAZ'),
(2, 'DOBLE FAZ');

create table if not exists carpeta_solapa (
    id bigint auto_increment not null primary key,
    tipo_papel varchar(255) not null,
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int null,
    informacion_adicional varchar(255) null,
    id_tipo_laminado_carpeta_solapa bigint not null,
    id_tipo_faz_carpeta_solapa bigint not null,
    constraint fk_tipo_laminado_carpeta_solapa foreign key(id_tipo_laminado_carpeta_solapa) references tipo_laminado_carpeta_solapa(id),
    constraint fk_tipo_faz_carpeta_solapa foreign key(id_tipo_faz_carpeta_solapa) references tipo_faz_carpeta_solapa(id)
);

-- catálogos
create table if not exists tipo_laminado_catalogo (
    id bigint auto_increment not null primary key,
    laminado varchar(255) not null
);

insert into tipo_laminado_catalogo(id, laminado) values
(1, 'BRILLANTE'),
(2, 'MATE'),
(3, 'NINGUNO');

create table if not exists tipo_faz_catalogo (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_faz_catalogo(id, tipo) values
(1, 'SIMPLE FAZ'),
(2, 'DOBLE FAZ');

create table if not exists catalogo (
    id bigint auto_increment not null primary key,
    tipo_papel varchar(255) not null,
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int null,
    informacion_adicional varchar(255) null,
    id_tipo_laminado_catalogo bigint not null,
    id_tipo_faz_catalogo bigint not null,
    constraint fk_tipo_laminado_catalogo foreign key(id_tipo_laminado_catalogo) references tipo_laminado_catalogo(id),
    constraint fk_tipo_faz_catalogo foreign key(id_tipo_faz_catalogo) references tipo_faz_catalogo(id)
);

-- cierra bolsas
create table if not exists tipo_troquelado_cierra_bolsas (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_troquelado_cierra_bolsas(id, tipo) values
(1, 'CUADRADO'),
(2, 'CIRCULAR'),
(3, 'POR EL CONTORNO'),
(4, 'CORTE INDIVIDUAL'),
(5, 'SIN TROQUELAR');

create table if not exists medida_cierra_bolsas (
    id bigint auto_increment not null primary key,
    medida varchar(255) not null
);

insert into medida_cierra_bolsas(id, medida) values
(1, '8X4 CM'),
(2, 'OTRA');

create table if not exists cantidad_cierra_bolsas (
    id bigint auto_increment not null primary key,
    cantidad varchar(255) not null
);

insert into cantidad_cierra_bolsas(id, cantidad) values
(1, '100'),
(2, '200'),
(3, '500'),
(4, 'OTRA');

create table if not exists cierra_bolsas (
    id bigint auto_increment not null primary key,
    medida_personalizada varchar(255) null,
    cantidad_personalizada varchar(255) null
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 2500,
    precio int null,
    informacion_adicional varchar(255) null,
    id_tipo_troquelado_cierra_bolsas bigint not null,
    id_medida_cierra_bolsas bigint not null,
    id_cantidad_cierra_bolsas bigint not null,
    constraint fk_tipo_troquelado_cierra_bolsas foreign key(id_tipo_troquelado_cierra_bolsas) references tipo_troquelado_cierra_bolsas(id),
    constraint fk_medida_cierra_bolsas foreign key(id_medida_cierra_bolsas) references medida_cierra_bolsas(id),
    constraint fk_cantidad_cierra_bolsas foreign key(id_cantidad_cierra_bolsas) references cantidad_cierra_bolsas(id)
);

create table if not exists plantilla_cierra_bolsas (
    id bigint auto_increment not null primary key,
    precio int not null,
    id_tipo_troquelado_cierra_bolsas bigint not null,
    id_medida_cierra_bolsas bigint not null,
    id_cantidad_cierra_bolsas bigint not null,
    constraint fk_plantilla_tipo_troquelado_cierra_bolsas foreign key(id_tipo_troquelado_cierra_bolsas) references tipo_troquelado_cierra_bolsas(id),
    constraint fk_plantilla_medida_cierra_bolsas foreign key(id_medida_cierra_bolsas) references medida_cierra_bolsas(id),
    constraint fk_plantilla_cantidad_cierra_bolsas foreign key(id_cantidad_cierra_bolsas) references cantidad_cierra_bolsas(id)
);

insert into plantilla_cierra_bolsas(id_tipo_troquelado_cierra_bolsas, id_medida_cierra_bolsas, id_cantidad_cierra_bolsas, precio) values
(4, 1, 1, 9850), -- corte individual, 8x4, 100
(4, 1, 2, 18600), -- corte individual 8x4, 200
(4, 1, 3, 43600); -- corte individual, 8x4, 500

-- cuadernos anillados
create table if not exists tipo_tapa_cuaderno_anillado (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_tapa_cuaderno_anillado(id, tipo) values
(1, 'TAPA DURA'),
(2, 'OTRA');

create table if not exists medida_cuaderno_anillado (
    id bigint auto_increment not null primary key,
    medida varchar(255) not null
);

insert into medida_cuaderno_anillado(id, medida) values
(1, 'A4'),
(2, 'A5'),
(3, 'OTRA');

create table if not exists cuaderno_anillado (
    id bigint auto_increment not null primary key,
    medida_personalizada varchar(255) null,
    tipo_tapa_personalizada varchar(255) null,
    cantidad_hojas int not null,
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int null,
    informacion_adicional varchar(255) null,
    id_tipo_tapa_cuaderno_anillado bigint not null,
    id_medida_cuaderno_anillado bigint not null,
    constraint fk_tipo_tapa_cuaderno_anillado foreign key(id_tipo_tapa_cuaderno_anillado) references tipo_tapa_cuaderno_anillado(id),
    constraint fk_medida_cuaderno_anillado foreign key(id_medida_cuaderno_anillado) references medida_cuaderno_anillado(id)
);

create table if not exists plantilla_cuaderno_anillado (
    id bigint auto_increment not null primary key,
    precio int not null,
    cantidad_hojas int not null,
    id_tipo_tapa_cuaderno_anillado bigint not null,
    id_medida_cuaderno_anillado bigint not null,
    constraint fk_plantilla_tipo_tapa_cuaderno_anillado foreign key(id_tipo_tapa_cuaderno_anillado) references tipo_tapa_cuaderno_anillado(id),
    constraint fk_plantilla_medida_cuaderno_anillado foreign key(id_medida_cuaderno_anillado) references medida_cuaderno_anillado(id)
);

insert into plantilla_cuaderno_anillado(cantidad_hojas, id_tipo_tapa_cuaderno_anillado, id_medida_cuaderno_anillado, precio) values
(80, 1, 1, 28950), -- 80 hojas, tapa dura, a4
(80, 1, 2, 22650); -- 80 hojas, tapa dura, a5

-- combos
create table if not exists tipo_combo (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_combo(id, tipo) values
(1, 'OFICINA'),
(2, 'PROFESIONAL'),
(3, 'EMPRENDEDOR X100'),
(4, 'EMPRENDEDOR X200'),
(5, 'EMPRENDEDOR X500');

create table if not exists cantidad_combo (
    id bigint auto_increment not null primary key,
    cantidad int not null
);

create table if not exists combo (
    id bigint auto_increment not null primary key,
    descripcion_combo varchar(1000) not null default '-',
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int null,
    informacion_adicional varchar(255) null,
    id_tipo_combo bigint not null,
    constraint fk_tipo_combo foreign key(id_tipo_combo) references tipo_combo(id)
);

create table if not exists plantilla_combo (
    id bigint auto_increment not null primary key,
    precio int not null,
    descripcion_combo varchar(1000) not null,
    id_tipo_combo bigint not null,
    constraint fk_plantilla_tipo_combo foreign key(id_tipo_combo) references tipo_combo(id)
);

insert into plantilla_combo(id_tipo_combo, descripcion_combo, precio) values
(3, '100 cierra bolsas (8x4 cm), 100 etiquetas colgantes (7x5 cm), 100 circulares troqueladas (5 cm)', 22500), -- emprendedor 100
(4, '200 cierra bolsas (8x4 cm), 200 eitquetas colgantes (7x5 cm), 200 circulares troqueladas (5 cm)', 38250), -- emprendedor 200
(5, '500 cierra bolsas (8x4 cm), 500 etiquetas colgamtes (7x5 cm), 500 circulares troqueladas (5 cm)', 78400), -- emprendedor 500
(2, '100 tarjetas simple faz, 100 sobres oficio inglés, 2 talonarios RP blanco y negro (13x18 cm)', 75800),    -- profesional
(1, '100 tarjetas simple faz, 100 sobres oficio inglés, 1 talonario A4 a color con membrete', 101300);         -- oficina

-- entradas
create table if not exists tipo_papel_entrada (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_papel_entrada(id, tipo) values
(1, 'OBRA 75 GRS'),
(2, 'ILUSTRACIÓN 150 GRS');

create table if not exists tipo_color_entrada (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_color_entrada(id, tipo) values
(1, 'BLANCO Y NEGRO'),
(2, 'A COLOR');

create table if not exists tipo_troquelado_entrada (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_troquelado_entrada(id, tipo) values
(1, 'SIN TROQUELAR'),
(2, 'TROQUELADO SIMPLE'),
(3, 'TROQUELADO DOBLE');

create table if not exists entrada (
    id bigint auto_increment not null primary key,
    medida varchar(255) not null default '17X6 CM',
    con_numerado tinyint(1) not null default 0,
    detalle_numerado varchar(255) null,
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int null,
    informacion_adicional varchar(255) null,
    id_tipo_papel_entrada bigint not null,
    id_tipo_color_entrada bigint not null,
    id_tipo_troquelado_entrada bigint not null,
    constraint fk_tipo_papel_entrada foreign key(id_tipo_papel_entrada) references tipo_papel_entrada(id),
    constraint fk_tipo_color_entrada foreign key(id_tipo_color_entrada) references tipo_color_entrada(id),
    constraint fk_tipo_troquelado_entrada foreign key(id_tipo_troquelado_entrada) references tipo_troquelado_entrada(id)
);

-- etiquetas
create table if not exists tipo_papel_etiqueta (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_papel_etiqueta(id, tipo) values
(1, 'OPALINA 180 GRS'),
(2, 'OPALINA 210 GRS'),
(3, 'ILUSTRACIÓN 250 GRS'),
(4, 'KRAFT'),
(5, 'ILUSTRACIÓN 2250 GRS');

create table if not exists tipo_laminado_etiqueta (
    id bigint auto_increment not null primary key,
    laminado varchar(255) not null
);

insert into tipo_laminado_etiqueta(id, laminado) values
(1, 'BRILLANTE'),
(2, 'MATE'),
(3, 'NINGUNO');

create table if not exists tamanio_perforacion (
    id bigint auto_increment not null primary key,
    tamanio varchar(255) not null
);

insert into tamanio_perforacion(id, tamanio) values
(1, 'NINGUNA'),
(2, 'CHICA'),
(3, 'MEDIANA'),
(4, 'GRANDE');

create table if not exists tipo_faz_etiqueta (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_faz_etiqueta(id, tipo) values
(1, 'SIMPLE FAZ'),
(2, 'DOBLE FAZ');

create table if not exists cantidad_etiqueta (
    id bigint auto_increment not null primary key,
    cantidad varchar(255) not null
);

insert into cantidad_etiqueta(id, cantidad) values
(1, '100'),
(2, '200'),
(3, '500'),
(4, '1000'),
(5, 'OTRA');

create table if not exists medida_etiqueta (
    id bigint auto_increment not null primary key,
    medida varchar(255) not null
);

insert into medida_etiqueta(id, medida) values
(1, '7X5 CM'),
(2, 'OTRA')

create table if not exists etiqueta (
    id bigint auto_increment not null primary key,
    medida_personalizada varchar(255) not null,
    con_perforacion_adicional tinyint(1) not null default 0,
    con_marca_adicional tinyint(1) not null default 0,
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 3500,
    precio int null,
    informacion_adicional varchar(255) null,
    id_tipo_papel_etiqueta bigint not null,
    id_tipo_laminado_etiqueta bigint not null,
    id_tamanio_perforacion bigint null,
    id_tipo_faz_etiqueta bigint not null,
    id_cantidad_etiqueta bigint not null,
    id_medida_etiqueta bigint not null,
    constraint fk_tipo_papel_etiqueta foreign key(id_tipo_papel_etiqueta) references tipo_papel_etiqueta(id),
    constraint fk_tipo_laminado_etiqueta foreign key(id_tipo_laminado_etiqueta) references tipo_laminado_etiqueta(id),
    constraint fk_tamanio_perforacion foreign key(id_tamanio_perforacion) references tamanio_perforacion(id),
    constraint fk_tipo_faz_etiqueta foreign key(id_tipo_faz_etiqueta) references tipo_faz_etiqueta(id),
    constraint fk_cantidad_etiqueta foreign key(id_cantidad_etiqueta) references cantidad_etiqueta(id),
    constraint fk_medida_etiqueta foreign key(id_medida_etiqueta) references medida_etiqueta(id)
);

create table if not exists plantilla_etiqueta (
    id bigint auto_increment not null primary key,
    precio int not null,
    id_tipo_papel_etiqueta bigint not null,
    id_tipo_laminado_etiqueta bigint not null,
    id_tipo_faz_etiqueta bigint not null,
    id_cantidad_etiqueta bigint not null,
    id_medida_etiqueta bigint not null,
    constraint fk_plantilla_tipo_papel_etiqueta foreign key(id_tipo_papel_etiqueta) references tipo_papel_etiqueta(id),
    constraint fk_plantilla_tipo_laminado_etiqueta foreign key(id_tipo_laminado_etiqueta) references tipo_laminado_etiqueta(id),
    constraint fk_plantilla_tipo_faz_etiqueta foreign key(id_tipo_faz_etiqueta) references tipo_faz_etiqueta(id),
    constraint fk_plantilla_cantidad_etiqueta foreign key(id_cantidad_etiqueta) references cantidad_etiqueta(id),
    constraint fk_plantilla_medida_etiqueta foreign key(id_medida_etiqueta) references medida_etiqueta(id)
);

insert into plantilla_etiqueta(id_tipo_papel_etiqueta, id_tipo_laminado_etiqueta, id_tipo_faz_etiqueta, id_cantidad_etiqueta, id_medida_etiqueta, precio) values
(4, 3, 1, 1, 1, 9700),  -- kraft, sin laminar, simple, 100, 7x5
(4, 3, 1, 2, 1, 17050), -- kraft, sin laminar, simple, 200, 7x5
(4, 3, 1, 3, 1, 40150), -- kraft, sin laminar, simple, 500, 7x5
(4, 3, 1, 4, 1, 70100), -- kraft, sin laminar, simple, 1000, 7x5
(4, 3, 2, 1, 1, 11150), -- kraft, sin laminar, doble, 100, 7x5
(4, 3, 2, 2, 1, 19750), -- kraft, sin laminar, doble, 200, 7x5
(4, 3, 2, 3, 1, 46200), -- kraft, sin laminar, doble, 500, 7x5
(4, 3, 2, 4, 1, 88250), -- kraft, sin laminar, doble, 1000, 7x5
(5, 3, 1, 1, 1, 9700),  -- 220 grs, sin laminar, simple, 100, 7x5
(5, 3, 1, 2, 1, 17050), -- 220 grs, sin laminar, simple, 200, 7x5
(5, 3, 1, 3, 1, 40150), -- 220 grs, sin laminar, simple, 500, 7x5
(5, 3, 1, 4, 1, 70100), -- 220 grs, sin laminar, simple, 1000, 7x5
(5, 3, 2, 1, 1, 11150), -- 220 grs, sin laminar, doble, 100, 7x5
(5, 3, 2, 2, 1, 19750), -- 220 grs, sin laminar, doble, 200, 7x5
(5, 3, 2, 3, 1, 46200), -- 220 grs, sin laminar, doble, 500, 7x5
(5, 3, 2, 4, 1, 88250), -- 220 grs, sin laminar, doble, 1000, 7x5
(1, 3, 1, 1, 1, 7950),  -- opalina 180, sin laminar, simple, 100, 7x5
(1, 3, 1, 2, 1, 14100), -- opalina 180, sin laminar, simple, 200, 7x5
(1, 3, 1, 3, 1, 33200), -- opalina 180, sin laminar, simple, 500, 7x5
(1, 3, 1, 4, 1, 59250), -- opalina 180, sin laminar, simple, 1000, 7x5
(1, 3, 2, 1, 1, 9700),  -- opalina 180, sin laminar, doble, 100, 7x5
(1, 3, 2, 2, 1, 17150), -- opalina 180, sin laminar, doble, 200, 7x5
(1, 3, 2, 3, 1, 40150), -- opalina 180, sin laminar, doble, 500, 7x5
(1, 3, 2, 4, 1, 70100); -- opalina 180, sin laminar, doble, 1000, 7x5

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

create table if not exists cantidad_folleto (
    id bigint auto_increment not null primary key,
    cantidad varchar(255) not null
);

insert into cantidad_folleto(id, cantidad) values
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

create table if not exists tipo_faz_folleto (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_faz_folleto(id, tipo) values
(1, 'SIMPLE FAZ'),
(2, 'DOBLE FAZ');

create table if not exists folleto (
    id bigint auto_increment not null primary key,
    cantidad_personalizada varchar(255) null,
    con_plegado tinyint(1) not null default 0,
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int null,
    informacion_adicional varchar(255) null,
    id_tipo_papel_folleto bigint not null,
    id_tipo_color_folleto bigint not null,
    id_tipo_faz_folleto bigint not null,
    id_tamanio_hoja_folleto bigint not null,
    id_tipo_folleto bigint not null,
    id_cantidad_folleto bigint not null,
    constraint fk_tipo_papel_folleto foreign key(id_tipo_papel_folleto) references tipo_papel_folleto(id),
    constraint fk_tipo_color_folleto foreign key(id_tipo_color_folleto) references tipo_color_folleto(id),
    constraint fk_tipo_faz_folleto foreign key(id_tipo_faz_folleto) references tipo_faz_folleto(id),
    constraint fk_tamanio_hoja_folleto foreign key(id_tamanio_hoja_folleto) references tamanio_hoja_folleto(id),
    constraint fk_tipo_folleto foreign key(id_tipo_folleto) references tipo_folleto(id),
    constraint fk_cantidad_folleto foreign key(id_cantidad_folleto) references cantidad_folleto(id)
);

create table if not exists plantilla_folleto (
    id bigint auto_increment not null primary key,
    precio int not null,
    id_tipo_papel_folleto bigint not null,
    id_tipo_color_folleto bigint not null,
    id_tipo_faz_folleto bigint not null,
    id_tamanio_hoja_folleto bigint not null,
    id_tipo_folleto bigint not null,
    id_cantidad_folleto bigint not null,
    constraint fk_plantilla_tipo_papel_folleto foreign key(id_tipo_papel_folleto) references tipo_papel_folleto(id),
    constraint fk_plantilla_tipo_color_folleto foreign key(id_tipo_color_folleto) references tipo_color_folleto(id),
    constraint fk_plantilla_tipo_faz_folleto foreign key(id_tipo_faz_folleto) references tipo_faz_folleto(id),
    constraint fk_plantilla_tamanio_hoja_folleto foreign key(id_tamanio_hoja_folleto) references tamanio_hoja_folleto(id),
    constraint fk_plantilla_tipo_folleto foreign key(id_tipo_folleto) references tipo_folleto(id),
    constraint fk_plantilla_cantidad_folleto foreign key(id_cantidad_folleto) references cantidad_folleto(id)
);

insert into plantilla_folleto(id_tipo_papel_folleto, id_tipo_color_folleto, id_tipo_faz_folleto, id_tamanio_hoja_folleto, id_tipo_folleto, id_cantidad_folleto, precio) values
(1, 1, 1, 3, 1, 2, 6400), -- obra, byn, simple faz, 1/4 de a4, común, 100
(1, 1, 2, 3, 1, 2, 7600), -- obra, byn, doble faz, 1/4 de a4, común, 100
(1, 1, 1, 3, 1, 4, 9750), -- obra, byn, simple faz, 1/4 de a4, común, 200
(1, 1, 2, 3, 1, 4, 13500), -- obra, byn, doble faz, 1/4 de a4, común, 200
(1, 1, 1, 3, 1, 7, 21900), -- obra, byn, simple faz, 1/4 de a4, común, 500
(1, 1, 2, 3, 1, 7, 27300), -- obra, byn, doble faz, 1/4 de a4, común, 500
(1, 1, 1, 3, 1, 8, 41100), -- obra, byn, simple faz, 1/4 de a4, común, 1000
(1, 1, 2, 3, 1, 8, 47500), -- obra, byn, doble faz, 1/4 de a4, común, 1000
(1, 2, 1, 3, 1, 2, 14850), -- obra, color, simple faz, 1/4 de a4, común, 100
(1, 2, 2, 3, 1, 2, 16750), -- obra, color, doble faz, 1/4 de a4, común, 100
(1, 2, 1, 3, 1, 4, 26700), -- obra, color, simple faz, 1/4 de a4, común, 200
(1, 2, 2, 3, 1, 4, 29800), -- obra, color, doble faz, 1/4 de a4, común, 200
(1, 2, 1, 3, 1, 7, 64000), -- obra, color, simple faz, 1/4 de a4, común, 500
(1, 2, 2, 3, 1, 7, 77500), -- obra, color, doble faz, 1/4 de a4, común, 500
(1, 2, 1, 3, 1, 8, 111400), -- obra, color, simple faz, 1/4 de a4, común, 1000
(1, 2, 2, 3, 1, 8, 139600), -- obra, color, doble faz, 1/4 de a4, común, 1000
(2, 2, 1, 3, 1, 2, 18150), -- 115, color, simple faz, 1/4 de a4, común, 100
(2, 2, 2, 3, 1, 2, 21600), -- 115, color, doble faz, 1/4 de a4, común, 100
(2, 2, 1, 3, 1, 4, 31950), -- 115, color, simple faz, 1/4 de a4, común, 200
(2, 2, 2, 3, 1, 4, 36900), -- 115, color, doble faz, 1/4 de a4, común, 200
(2, 2, 1, 3, 1, 7, 77550), -- 115, color, simple faz, 1/4 de a4, común, 500
(2, 2, 2, 3, 1, 7, 87100), -- 115, color, doble faz, 1/4 de a4, común, 500
(2, 2, 1, 3, 1, 8, 140450), -- 115, color, simple faz, 1/4 de a4, común, 1000
(2, 2, 2, 3, 1, 8, 159100); -- 115, color, doble faz, 1/4 de a4, común, 1000

-- hojas membreteadas
create table if not exists hojas_membreteadas (
    id bigint auto_increment not null primary key,
    medida varchar(255) not null,
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int null,
    informacion_adicional varchar(255) null
);

-- impresiones
create table if not exists tipo_color_impresion (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_color_impresion(id, tipo) values
(1, 'BLANCO Y NEGRO'),
(2, 'A COLOR');

create table if not exists tamanio_hoja_impresion (
    id bigint auto_increment not null primary key,
    tamanio varchar(255) not null
);

insert into tamanio_hoja_impresion(id, tamanio) values
(1, 'A4'),
(2, 'A5'),
(3, 'OFICIO'),
(4, 'A3'),
(5, 'OTRO');

create table if not exists tipo_faz_impresion (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_faz_impresion(id, tipo) values
(1, 'SIMPLE FAZ'),
(2, 'DOBLE FAZ');

create table if not exists tipo_papel_impresion (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

create table if not exists impresion (
    id bigint auto_increment not null primary key,
    es_anillado tinyint(1) not null default 0,
    enlace_archivo varchar(255) not null default '-',
    precio int null,
    informacion_adicional varchar(255) null,
    id_tipo_color_impresion bigint not null,
    id_tamanio_hoja_impresion bigint not null,
    id_tipo_faz_impresion bigint not null,
    id_tipo_papel_impresion bigint not null,
    constraint fk_tipo_color_impresion foreign key(id_tipo_color_impresion) references tipo_color_impresion(id),
    constraint fk_tamanio_hoja_impresion foreign key(id_tamanio_hoja_impresion) references tamanio_hoja_impresion(id),
    constraint fk_tipo_faz_impresion foreign key(id_tipo_faz_impresion) references tipo_faz_impresion(id),
    constraint fk_tipo_papel_impresion foreign key(id_tipo_papel_impresion) references tipo_papel_impresion(id)
);

-- lonas
create table if not exists medida_lona_comun (
    id bigint auto_increment not null primary key,
    medida varchar(255) not null
);

insert into medida_lona_comun(id, medida) values
(1, '40X60 CM'),
(2, '60X90 CM'),
(3, '70X100 CM'),
(4, '90X120 CM'),
(5, 'OTRA');

create table if not exists tipo_lona_comun (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_lona_comun(id, tipo) values
(1, 'LONA FRONT'),
(2, 'LONA BACK LIGHT'),
(3, 'LONA BLOCKOUT');

create table if not exists lona_comun (
    id bigint auto_increment not null primary key,
    medida_personalizada varchar(255) null,
    con_ojales tinyint(1) not null default 0,
    con_ojales_con_refuerzo tinyint(1) not null default 0,
    con_bolsillos tinyint(1) not null default 0,
    con_demasia_para_tensado tinyint(1) not null default 0,
    con_solapado tinyint(1) not null default 0,
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int null,
    informacion_adicional varchar(255) null,
    id_medida_lona_comun bigint not null,
    id_tipo_lona_comun bigint not null,
    constraint fk_medida_lona_comun foreign key(id_medida_lona_comun) references medida_lona_comun(id),
    constraint fk_tipo_lona_comun foreign key(id_tipo_lona_comun) references tipo_lona_comun(id)
);

create table if not exists plantilla_lona_comun (
    id bigint auto_increment not null primary key,
    precio int not null,
    con_bolsillos tinyint(1) not null,
    id_medida_lona_comun bigint not null,
    id_tipo_lona_comun bigint not null,
    constraint fk_plantilla_medida_lona_comun foreign key(id_medida_lona_comun) references medida_lona_comun(id),
    constraint fk_plantilla_tipo_lona_comun foreign key(id_tipo_lona_comun) references tipo_lona_comun(id)
);

insert into plantilla_lona_comun(con_bolsillos, id_medida_lona_comun, id_tipo_lona_comun, precio) values
(1, 1, 1, 20440), -- con bolsillos, 40x60, front
(1, 2, 1, 23600), -- con bolsillos, 60x90, front
(1, 3, 1, 27650), -- con bolsillos, 70x100, front
(1, 4, 1, 29930); -- con bolsillos, 90x120, front

-- lonas publicitarias
create table if not exists tipo_lona_publicitaria (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_lona_publicitaria(id, tipo) values
(1, 'LONA FRONT'),
(2, 'LONA BACK LIGHT'),
(3, 'LONA BLOCKOUT');

create table if not exists medida_lona_publicitaria (
    id bigint auto_increment not null primary key,
    medida varchar(255) not null
);

insert into medida_lona_publicitaria(id, medida) values
(1, '60X160 CM'),
(2, '90X190 CM'),
(3, '85X200 CM'),
(4, 'A3 MINI - 25X42 CM');

create table if not exists lona_publicitaria (
    id bigint auto_increment not null primary key,
    con_adicional_portabanner tinyint(1) not null default 0,
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int null,
    informacion_adicional varchar(255) null,
    id_medida_lona_publicitaria bigint not null,
    id_tipo_lona_publicitaria bigint not null,
    constraint fk_medida_lona_publicitaria foreign key(id_medida_lona_publicitaria) references medida_lona_publicitaria(id),
    constraint fk_tipo_lona_publicitaria foreign key(id_tipo_lona_publicitaria) references tipo_lona_publicitaria(id)
);

create table if not exists plantilla_lona_publicitaria (
    id bigint auto_increment not null primary key,
    precio int not null,
    con_adicional_portabanner tinyint(1) not null,
    id_medida_lona_publicitaria bigint not null,
    id_tipo_lona_publicitaria bigint not null,
    constraint fk_plantilla_medida_lona_publicitaria foreign key(id_medida_lona_publicitaria) references medida_lona_publicitaria(id),
    constraint fk_plantilla_tipo_lona_publicitaria foreign key(id_tipo_lona_publicitaria) references tipo_lona_publicitaria(id)
);

insert into plantilla_lona_publicitaria(con_adicional_portabanner, id_medida_lona_publicitaria, id_tipo_lona_publicitaria, precio) values
(1, 4, 1, 19500), -- con portabanner, 25x42, front
(1, 1, 1, 56016), -- con portabanner 60x160, front
(1, 2, 1, 81480), -- con portabanner, 90x190, front
(1, 3, 1, 85510); -- con portabanner, 85x200, front

-- rifas / bonos contribución
create table if not exists tipo_papel_rifa (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_papel_rifa(id, tipo) values
(1, 'OBRA 75 GRS'),
(2, 'ILUSTRACIÓN 150 GRS');

create table if not exists tipo_troquelado_rifa (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_troquelado_rifa(id, tipo) values
(1, 'SIN TROQUELAR'),
(2, 'TROQUELADO SIMPLE'),
(3, 'TROQUELADO DOBLE');

create table if not exists tipo_color_rifa (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_color_rifa(id, tipo) values
(1, 'BLANCO Y NEGRO'),
(2, 'A COLOR');

create table if not exists rifas_bonos_contribucion (
    id bigint auto_increment not null primary key,
    con_numerado tinyint(1) not null default 0,
    detalle_numerado varchar(255) null,
    con_encolado tinyint(1) not null default 0,
    medida varchar(255) not null,
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int null,
    informacion_adicional varchar(255) null,
    id_tipo_papel_rifa bigint not null,
    id_tipo_troquelado_rifa bigint not null,
    id_tipo_color_rifa bigint not null,
    constraint fk_tipo_papel_rifa foreign key(id_tipo_papel_rifa) references tipo_papel_rifa(id),
    constraint fk_tipo_troquelado_rifa foreign key(id_tipo_troquelado_rifa) references tipo_troquelado_rifa(id),
    constraint fk_tipo_color_rifa foreign key(id_tipo_color_rifa) references tipo_color_rifa(id)
);
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

create table if not exists tipo_corte_rotulacion (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_corte_rotulacion(id, tipo) values
(1, 'MANUAL'),
(2, 'TROQUELADO'),
(3, 'TROQUELADO CON CORTE INDIVIDUAL'),
(4, 'PLANCHA IMPRESA');

create table if not exists rotulacion (
    id bigint auto_increment not null primary key,
    es_laminado tinyint(1) not null default 0,
    horario_rotulacion varchar(255) not null default '-',
    direccion_rotulacion varchar(255) not null default '-',
    medida varchar(255) not null,
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int null,
    informacion_adicional varchar(255) null,
    id_tipo_rotulacion bigint not null,
    id_tipo_corte_rotulacion bigint not null,
    constraint fk_tipo_rotulacion foreign key(id_tipo_rotulacion) references tipo_rotulacion(id),
    constraint fk_tipo_corte_rotulacion foreign key(id_tipo_corte_rotulacion) references tipo_corte_rotulacion(id)
);

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

create table if not exists sello_automatico (
    id bigint auto_increment not null primary key,
    es_profesional tinyint(1) not null default 0,
    es_particular tinyint(1) not null default 0,
    texto_linea_uno varchar(255) not null,
    texto_linea_dos varchar(255) not null default '-',
    texto_linea_tres varchar(255) not null default '-',
    texto_linea_cuatro varchar(255) not null default '-',
    tipografia_linea_uno varchar(255) not null,
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int null,
    informacion_adicional varchar(255) null,
    id_modelo_sello_automatico bigint not null,
    constraint fk_modelo_sello_automatico foreign key(id_modelo_sello_automatico) references modelo_sello_automatico(id)
);

-- sellos automáticos escolares
create table if not exists modelo_sello_automatico_escolar (
    id bigint auto_increment not null primary key,
    modelo varchar(255) not null
);

insert into modelo_sello_automatico_escolar(id, modelo) values
(1, '10X27 MM - 10 COLOP'),
(2, '14X38 MM - 20 COLOP');

create table if not exists sello_automatico_escolar (
    id bigint auto_increment not null primary key,
    texto_linea_uno varchar(255) not null,
    texto_linea_dos varchar(255) not null default '-',
    texto_linea_tres varchar(255) not null default '-',
    tipografia varchar(255) not null,
    dibujo varchar(255) not null,
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int null,
    informacion_adicional varchar(255) null,
    id_modelo_sello_automatico_escolar bigint not null,
    constraint fk_modelo_sello_automatico_escolar foreign key(id_modelo_sello_automatico_escolar) references modelo_sello_automatico_escolar(id)
);

create table if not exists plantilla_sello_automatico_escolar (
    id bigint auto_increment not null primary key,
    precio int not null,
    id_modelo_sello_automatico_escolar bigint not null,
    constraint fk_plantilla_modelo_sello_automatico_escolar foreign key(id_modelo_sello_automatico_escolar) references modelo_sello_automatico_escolar(id)
);

insert into plantilla_sello_automatico_escolar(id_modelo_sello_automatico_escolar, precio) values
(1, 15800),
(2, 18850);

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

create table if not exists sello_madera (
    id bigint auto_increment not null primary key,
    tamanio_personalizado varchar(255) null,
    con_adicional_perilla tinyint(1) not null default 0,
    detalle_sello varchar(255) not null default '-',
    tipografia_linea_uno varchar(255) not null,
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int null,
    informacion_adicional varchar(255) null,
    id_tamanio_sello_madera bigint not null,
    constraint fk_tamanio_sello_madera foreign key(id_tamanio_sello_madera) references tamanio_sello_madera(id)
);

-- sobres
create table if not exists tipo_color_sobre (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_color_sobre(id, tipo) values
(1, 'BLANCO Y NEGRO'),
(2, 'A COLOR');

create table if not exists medida_sobre (
    id bigint auto_increment not null primary key,
    medida varchar(255) not null
);

insert into medida_sobre(id, medida) values
(1, 'BOLSA A4 - 27X37 CM'),
(2, 'BOLSA A5 - 19X24 CM'),
(3, 'OFICIO INGLÉS'),
(4, 'OTRA');

create table if not exists sobre (
    id bigint auto_increment not null primary key,
    medida_personalizada varchar(255) null,
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int null,
    informacion_adicional varchar(255) null,
    id_medida_sobre bigint not null,
    id_tipo_color_sobre bigint not null,
    constraint fk_medida_sobre foreign key(id_medida_sobre) references medida_sobre(id),
    constraint fk_tipo_color_sobre foreign key(id_tipo_color_sobre) references tipo_color_sobre(id)
);

-- stickers
create table if not exists tipo_troquelado_sticker (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_troquelado_sticker(id, tipo) values
(1, 'CUADRADO'),
(2, 'CIRCULAR'),
(3, 'POR EL CONTORNO'),
(4, 'CORTE INDIVIDUAL'),
(5, 'SIN TROQUELAR');

create table if not exists medida_sticker (
    id bigint auto_increment not null primary key,
    medida varchar(255) not null
);

insert into medida_sticker(id, medida) values
(1, '5 CM'),
(2, '7 CM'),
(3, '10 CM');

create table if not exists cantidad_sticker (
    id bigint auto_increment not null primary key,
    cantidad int not null
);

insert into cantidad_sticker(id, cantidad) values
(1, 100),
(2, 200),
(3, 500);

create table if not exists sticker (
    id bigint auto_increment not null primary key,
    medida_personalizada varchar(255) null,
    cantidad_personalizada int null,
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 2000,
    precio int null,
    informacion_adicional varchar(255) null,
    id_tipo_troquelado_sticker bigint not null,
    id_cantidad_sticker bigint not null,
    id_medida_sticker bigint not null,
    constraint fk_tipo_troquelado_sticker foreign key(id_tipo_troquelado_sticker) references tipo_troquelado_sticker(id),
    constraint fk_cantidad_sticker foreign key(id_cantidad_sticker) references cantidad_sticker(id),
    constraint fk_medida_sticker foreign key(id_medida_sticker) references medida_sticker(id)
);

create table if not exists plantilla_sticker (
    id bigint auto_increment not null primary key,
    precio int not null,
    id_tipo_troquelado_sticker bigint not null,
    id_cantidad_sticker bigint not null,
    id_medida_sticker bigint not null,
    constraint fk_plantilla_tipo_troquelado_sticker foreign key(id_tipo_troquelado_sticker) references tipo_troquelado_sticker(id),
    constraint fk_plantilla_cantidad_sticker foreign key(id_cantidad_sticker) references cantidad_sticker(id),
    constraint fk_plantilla_medida_sticker foreign key(id_medida_sticker) references medida_sticker(id)
);

insert into plantilla_sticker(id_tipo_troquelado_sticker, id_cantidad_sticker, id_medida_sticker, precio) values
(3, 1, 1, 9850), -- por el contorno, 100, 5 cm
(3, 2, 1, 18600), -- por el contorno, 200, 5 cm
(3, 3, 1, 43600), -- por el contorno, 500, 5 cm
(3, 1, 2, 18000), -- por el contorno, 100, 7 cm
(3, 2, 2, 30850), -- por el contorno, 200, 7 cm
(3, 3, 2, 75150), -- por el contorno, 500, 7 cm
(3, 1, 3, 23700), -- por el contorno, 100, 10 cm
(3, 2, 3, 42300), -- por el contorno, 200, 10 cm
(3, 3, 3, 94650); -- por el contorno, 500, 10 cm

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

create table if not exists sublimacion (
    id bigint auto_increment not null primary key,
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int null,
    informacion_adicional varchar(255) null,
    id_material_sublimacion bigint not null,
    constraint fk_material_sublimacion foreign key(id_material_sublimacion) references material_sublimacion(id)
);

create table if not exists plantilla_sublimacion (
    id bigint auto_increment not null primary key,
    precio int not null,
    id_material_sublimacion bigint not null,
    constraint fk_plantilla_material_sublimacion foreign key(id_material_sublimacion) references material_sublimacion(id)
);

insert into plantilla_sublimacion(id_material_sublimacion, precio) values
(3, 15450), -- taza de cerámica
(2, 11600), -- taza de polímero
(5, 19500), -- taza mágica
(4, 26400), -- taza con glitter
(8, 15450), -- mate de cerámica
(7, 12400), -- mate de polímero
(9, 13900), -- jarro de café
(10, 6750), -- lapicero de polímero
(14, 11050); -- mousepad

-- talonarios (presupuestos, x, recibos)
create table if not exists modo_talonario (
    id bigint auto_increment not null primary key,
    modo varchar(255) not null
);

insert into modo_talonario(id, modo) values
(1, 'ORIGINAL'),
(2, 'ORIGINAL DUPLICADO'),
(3, 'N/A');

create table if not exists tipo_talonario (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_talonario(id, tipo) values
(1, 'RECIBO'),
(2, 'PRESUPUESTO'),
(3, 'X'),
(4, 'OTRO');

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

create table if not exists talonario (
    id bigint auto_increment not null primary key,
    con_numerado tinyint(1) not null default 0,
    detalle_numerado varchar(255) null,
    es_encolado tinyint(1) not null default 0,
    medida_personalizada varchar(255) null,
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int null,
    informacion_adicional varchar(255) null,
    id_tipo_talonario bigint not null,
    id_tipo_troquelado_talonario bigint not null,
    id_modo_talonario bigint not null,
    id_tipo_color_talonario bigint not null,
    id_medida_talonario bigint not null,
    id_tipo_papel_talonario bigint not null,
    constraint fk_tipo_talonario foreign key(id_tipo_talonario) references tipo_talonario(id),
    constraint fk_tipo_troquelado_talonario foreign key(id_tipo_troquelado_talonario) references tipo_troquelado_talonario(id),
    constraint fk_modo_talonario foreign key(id_modo_talonario) references modo_talonario(id),
    constraint fk_tipo_color_talonario foreign key(id_tipo_color_talonario) references tipo_color_talonario(id),
    constraint fk_medida_talonario foreign key(id_medida_talonario) references medida_talonario(id),
    constraint fk_tipo_papel_talonario foreign key(id_tipo_papel_talonario) references tipo_papel_talonario(id)
);

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

create table if not exists cantidad_tarjeta (
    id bigint auto_increment not null primary key,
    cantidad varchar(255) not null
);

insert into cantidad_tarjeta(id, cantidad) values
(1, '50'),
(2, '100'),
(3, '150'),
(4, '200'),
(5, '300'),
(6, '400'),
(7, '500'),
(8, '1000'),
(9, 'OTRA');

create table if not exists tipo_laminado_tarjeta (
    id bigint auto_increment not null primary key,
    laminado varchar(255) not null
);

insert into tipo_laminado_tarjeta(id, laminado) values
(1, 'BRILLANTE'),
(2, 'MATE'),
(3, 'NINGUNO');

create table if not exists tipo_faz_tarjeta (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_faz_tarjeta(id, tipo) values
(1, 'SIMPLE FAZ'),
(2, 'DOBLE FAZ');

create table if not exists tarjeta (
    id bigint auto_increment not null primary key,
    medida_personalizada varchar(255) null,
    cantidad_personalizada int null,
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int null,
    informacion_adicional varchar(255) null
    id_tipo_papel_tarjeta bigint not null,
    id_tipo_color_tarjeta bigint not null,
    id_tipo_faz_tarjeta bigint not null,
    id_tipo_laminado_tarjeta bigint not null,
    id_medida_tarjeta bigint not null,
    id_cantidad_tarjeta bigint not null,
    constraint fk_tipo_papel_tarjeta foreign key(id_tipo_papel_tarjeta) references tipo_papel_tarjeta(id),
    constraint fk_tipo_color_tarjeta foreign key(id_tipo_color_tarjeta) references tipo_color_tarjeta(id),
    constraint fk_tipo_faz_tarjeta foreign key(id_tipo_faz_tarjeta) references tipo_faz_tarjeta(id),
    constraint fk_tipo_laminado_tarjeta foreign key(id_tipo_laminado_tarjeta) references tipo_laminado_tarjeta(id),
    constraint fk_medida_tarjeta foreign key(id_medida_tarjeta) references medida_tarjeta(id),
    constraint fk_cantidad_tarjeta foreign key(id_cantidad_tarjeta) references cantidad_tarjeta(id)
);

create table if not exists plantilla_tarjeta (
    id bigint auto_increment not null primary key,
    precio int not null,
    id_tipo_papel_tarjeta bigint not null,
    id_tipo_color_tarjeta bigint not null,
    id_tipo_faz_tarjeta bigint not null,
    id_tipo_laminado_tarjeta bigint not null,
    id_medida_tarjeta bigint not null,
    id_cantidad_tarjeta bigint not null,
    constraint fk_plantilla_tipo_papel_tarjeta foreign key(id_tipo_papel_tarjeta) references tipo_papel_tarjeta(id),
    constraint fk_plantilla_tipo_color_tarjeta foreign key(id_tipo_color_tarjeta) references tipo_color_tarjeta(id),
    constraint fk_plantilla_tipo_faz_tarjeta foreign key(id_tipo_faz_tarjeta) references tipo_faz_tarjeta(id),
    constraint fk_plantilla_tipo_laminado_tarjeta foreign key(id_tipo_laminado_tarjeta) references tipo_laminado_tarjeta(id),
    constraint fk_plantilla_medida_tarjeta foreign key(id_medida_tarjeta) references medida_tarjeta(id),
    constraint fk_plantilla_cantidad_tarjeta foreign key(id_cantidad_tarjeta) references cantidad_tarjeta(id)
);

insert into plantilla_tarjeta(id_tipo_papel_tarjeta, id_tipo_color_tarjeta, id_tipo_faz_tarjeta, id_tipo_laminado_tarjeta, id_medida_tarjeta, id_cantidad_tarjeta, precio) values
(3, 2, 1, 3, 1, 1, 10290), -- 250 grs, color, simple, sin laminar, 9x5, 50
(3, 2, 2, 3, 1, 1, 11350), -- 250 grs, color, doble, sin laminar, 9x5, 50
(3, 2, 1, 2, 1, 1, 12820), -- 250 grs, color, simple, mate, 9x5, 50
(3, 2, 2, 2, 1, 1, 13880), -- 250 grs, color, doble, mate, 9x5, 50
(3, 2, 1, 1, 1, 1, 12270), -- 250 grs, color, simple, brillante, 9x5, 50
(3, 2, 2, 1, 1, 1, 13330), -- 250 grs, color, doble, brillante, 9x5, 50
(3, 2, 1, 3, 1, 2, 16500), -- 250 grs, color, simple, sin laminar, 9x5, 100
(3, 2, 2, 3, 1, 2, 19950), -- 250 grs, color, doble, sin laminar, 9x5, 100
(3, 2, 1, 2, 1, 2, 19800), -- 250 grs, color, simple, mate, 9x5, 100
(3, 2, 2, 2, 1, 2, 23250), -- 250 grs, color, doble, mate, 9x5, 100
(3, 2, 1, 1, 1, 2, 19140), -- 250 grs, color, simple, brillante, 9x5, 100
(3, 2, 2, 1, 1, 2, 22590), -- 250 grs, color, doble, brillante, 9x5, 100
(3, 2, 1, 3, 1, 4, 27560), -- 250 grs, color, simple, sin laminar, 9x5, 200
(3, 2, 2, 3, 1, 4, 31850), -- 250 grs, color, doble, sin laminar, 9x5, 200
(3, 2, 1, 2, 1, 4, 34160), -- 250 grs, color, simple, mate, 9x5, 200
(3, 2, 2, 2, 1, 4, 38450), -- 250 grs, color, doble, mate, 9x5, 200
(3, 2, 1, 1, 1, 4, 32840), -- 250 grs, color, simple, brillante, 9x5, 200
(3, 2, 2, 1, 1, 4, 37130), -- 250 grs, color, doble, brillante, 9x5, 200
(3, 2, 1, 3, 1, 7, 59100), -- 250 grs, color, simple, sin laminar, 9x5, 500
(3, 2, 2, 3, 1, 7, 69000), -- 250 grs, color, doble, sin laminar, 9x5, 500
(3, 2, 1, 2, 1, 7, 75600), -- 250 grs, color, simple, mate, 9x5, 500
(3, 2, 2, 2, 1, 7, 85500), -- 250 grs, color, doble, mate, 9x5, 500
(3, 2, 1, 1, 1, 7, 72300), -- 250 grs, color, simple, brillante, 9x5, 500
(3, 2, 2, 1, 1, 7, 82200); -- 250 grs, color, doble, brillante, 9x5, 500

-- turneros / r.p
create table if not exists tipo_color_turnero (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_color_turnero(id, tipo) values
(1, 'BLANCO Y NEGRO'),
(2, 'A COLOR');

create table if not exists cantidad_turnero (
    id bigint auto_increment not null primary key,
    cantidad varchar(255) not null
);

insert into cantidad_turnero(id, cantidad) values
(1, '4'),
(2, '8'),
(3, '12'),
(4, 'OTRA');

create table if not exists medida_turnero (
    id bigint auto_increment not null primary key,
    medida varchar(255) not null
);

insert into medida_turnero(id, medida) values
(1, '1/4 DE A4'),
(2, 'OTRA');

create table if not exists turnero (
    id bigint auto_increment not null primary key,
    cantidad_personalizada int null,
    medida_personalizada varchar(255) null,
    cantidad_hojas int not null,
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int null,
    informacion_adicional varchar(255) null,
    id_tipo_color_turnero bigint not null,
    id_cantidad_turnero bigint not null,
    id_medida_turnero bigint not null,
    constraint fk_tipo_color_turnero foreign key(id_tipo_color_turnero) references tipo_color_turnero(id),
    constraint fk_cantidad_turnero foreign key(id_cantidad_turnero) references cantidad_turnero(id),
    constraint fk_medida_turnero foreign key(id_medida_turnero) references medida_turnero(id)
);

create table if not exists plantilla_turnero (
    id bigint auto_increment not null primary key,
    precio int not null,
    cantidad_hojas int not null,
    id_tipo_color_turnero bigint not null,
    id_cantidad_turnero bigint not null,
    id_medida_turnero bigint not null,
    constraint fk_plantilla_tipo_color_turnero foreign key(id_tipo_color_turnero) references tipo_color_turnero(id),
    constraint fk_plantilla_cantidad_turnero foreign key(id_cantidad_turnero) references cantidad_turnero(id),
    constraint fk_plantilla_medida_turnero foreign key(id_medida_turnero) references medida_turnero(id)
);

insert into plantilla_turnero(cantidad_hojas, id_tipo_color_turnero, id_cantidad_turnero, id_medida_turnero, precio) values
(100, 1, 1, 1, 29300), -- 100 hojas, byn, 4, 1/4 de a4
(100, 1, 2, 1, 48000), -- 100 hojas, byn, 8, 1/4 de a4
(100, 1, 3, 1, 63950), -- 100 hojas, byn, 12, 1/4 de a4
(100, 2, 1, 1, 38600), -- 100 hojas, color, 4, 1/4 de a4
(100, 2, 2, 1, 69150), -- 100 hojas, color, 8, 1/4 de a4
(100, 2, 3, 1, 96100), -- 100 hojas, color, 12, 1/4 de a4

-- vinilos
create table if not exists tipo_corte_vinilo (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_corte_vinilo(id, tipo) values
(1, 'MANUAL'),
(2, 'TROQUELADO'),
(3, 'TROQUELADO CON CORTE INDIVIDUAL'),
(4, 'PLANCHA IMPRESA');

create table if not exists tipo_adicional_vinilo (
    id bigint auto_increment not null primary key,
    adicional varchar(255) not null
);

insert into tipo_adicional_vinilo(id, adicional) values
(1, 'LAMINADO'),
(2, 'IMÁN'),
(3, 'NINGUNO');

create table if not exists tipo_vinilo (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_vinilo(id, tipo) values
(1, 'ARLON BRILLANTE'),
(2, 'ARLON MATE'),
(3, 'CLEAR'),
(4, 'MICROPERFORADO');

create table if not exists vinilo (
    id bigint auto_increment not null primary key,
    medida varchar(255) not null,
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int null,
    informacion_adicional varchar(255) null,
    id_tipo_vinilo bigint not null,
    id_tipo_adicional_vinilo bigint not null,
    id_tipo_corte_vinilo bigint not null,
    constraint fk_tipo_vinilo foreign key(id_tipo_vinilo) references tipo_vinilo(id),
    constraint fk_tipo_adicioal_vinilo foreign key(id_tipo_adicional_vinilo) references tipo_adicional_vinilo(id),
    constraint fk_tipo_corte_vinilo foreign key(id_tipo_corte_vinilo) references tipo_corte_vinilo(id)
);

-- vinilos + plástico corrugado
create table if not exists vinilo_plastico_corrugado (
    id bigint auto_increment not null primary key,
    medida varchar(255) not null,
    con_ojales tinyint(1) not null default 0,
    cantidad_ojales int not null default 0,
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int null,
    informacion_adicional varchar(255) null
);

-- vinilos de corte
create table if not exists trae_material_vinilo (
    id bigint auto_increment not null primary key,
    material varchar(255) not null
);

insert into trae_material_vinilo(id, material) values
(1, 'PARA SOLO CORTE'),
(2, 'CORTE PELADO TRANSFER'),
(3, 'NO APLICA');

create table if not exists vinilo_de_corte (
    id bigint auto_increment not null primary key,
    es_promocional tinyint(1) not null default 0,
    es_oracal tinyint(1) not null default 0,
    codigo_color varchar(255) not null default '-',
    con_colocacion tinyint(1) not null default 0,
    medida varchar(255) not null,
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int null,
    informacion_adicional varchar(255) null,
    id_trae_material_vinilo bigint not null,
    constraint fk_trae_material_vinilo foreign key(id_trae_material_vinilo) references trae_material_vinilo(id)
);

-- otros
create table if not exists tipo_color_otro (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_color_otro(id, tipo) values
(1, 'BLANCO Y NEGRO'),
(2, 'A COLOR');

create table if not exists otro (
    id bigint auto_increment not null primary key,
    medida varchar(255) null,
    enlace_archivo varchar(255) not null default '-',
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    precio int null,
    informacion_adicional varchar(255) null,
    id_tipo_color_otro bigint not null,
    constraint fk_tipo_color_otro foreign key(id_tipo_color_otro) references tipo_color_otro(id)
);

-- ordenes de trabajo
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
    id_medio_pago bigint null,
    id_estado_pago bigint not null default 1,
    id_estado_orden bigint not null default 1,
    id_empleado bigint not null,
    constraint fk_medio_pago_orden foreign key (id_medio_pago) references medio_pago(id),
    constraint fk_estado_pago_orden foreign key (id_estado_pago) references estado_pago(id),
    constraint fk_estado_orden foreign key (id_estado_orden) references estado_orden(id),
    constraint fk_empleado_orden foreign key (id_empleado) references empleado(id)
);

-- tablas puente
create table if not exists orden_agenda (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_agenda bigint not null,
    constraint fk_orden_agenda foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_agenda foreign key(id_agenda) references agenda(id)
);

create table if not exists orden_anotador (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_anotador bigint not null,
    constraint fk_orden_anotador foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_anotador foreign key(id_anotador) references anotador(id)
);

create table if not exists orden_carpeta_solapa (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_carpeta_solapa bigint not null,
    constraint fk_orden_carpeta_solapa foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_carpeta_solapa foreign key(id_carpeta_solapa) references carpeta_solapa(id)
);

create table if not exists orden_catalogo (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_catalogo bigint not null,
    constraint fk_orden_catalogo foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_catalogo foreign key(id_catalogo) references catalogo(id)
);

create table if not exists orden_cierra_bolsas (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_cierra_bolsas bigint not null,
    constraint fk_orden_cierra_bolsas foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_cierra_bolsas foreign key(id_cierra_bolsas) references cierra_bolsas(id)
);

create table if not exists orden_cuaderno_anillado (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_cuaderno_anillado bigint not null,
    constraint fk_orden_cuaderno_anillado foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_cuaderno_anillado foreign key(id_cuaderno_anillado) references cuaderno_anillado(id)
);

create table if not exists orden_combo (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_combo bigint not null,
    constraint fk_orden_combo foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_combo foreign key(id_combo) references combo(id)
);

create table if not exists orden_entrada (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_entrada bigint not null,
    constraint fk_orden_entrada foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_entrada foreign key(id_entrada) references entrada(id)
);

create table if not exists orden_etiqueta (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_etiqueta bigint not null,
    constraint fk_orden_etiqueta foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_etiqueta foreign key(id_etiqueta) references etiqueta(id)
);

create table if not exists orden_folleto (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_folleto bigint not null,
    constraint fk_orden_folleto foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_folleto foreign key(id_folleto) references folleto(id)
);

create table if not exists orden_hojas_membreteadas (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_hojas_membreteadas bigint not null,
    constraint fk_orden_hojas_membreteadas foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_hojas_membreteadas foreign key(id_hojas_membreteadas) references hojas_membreteadas(id)
);

create table if not exists orden_impresion (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_impresion bigint not null,
    constraint fk_orden_impresion foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_impresion foreign key(id_impresion) references impresion(id)
);

create table if not exists orden_lona_comun (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_lona_comun bigint not null,
    constraint fk_orden_lona_comun foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_lona_comun foreign key(id_lona_comun) references lona_comun(id)
);

create table if not exists orden_lona_publicitaria(
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_lona_publicitaria bigint not null,
    constraint fk_orden_lona_publicitaria foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_lona_publicitaria foreign key(id_lona_publicitaria) references lona_publicitaria(id)
);

create table if not exists orden_rifas_bonos_contribucion (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_rifas_bonos_contribucion bigint not null,
    constraint fk_orden_rifas_bonos_contribucion foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_rifas_bonos_contribucion foreign key(id_rifas_bonos_contribucion) references rifas_bonos_contribucion(id)
);

create table if not exists orden_rotulacion (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_rotulacion bigint not null,
    constraint fk_orden_rotulacion foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_rotulacion foreign key(id_rotulacion) references rotulacion(id)
);

create table if not exists orden_sello_automatico (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_sello_automatico bigint not null,
    constraint fk_orden_sello_automatico foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_sello_automatico foreign key(id_sello_automatico) references sello_automatico(id)
);

create table if not exists orden_sello_automatico_escolar (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_sello_automatico_escolar bigint not null,
    constraint fk_orden_sello_automatico_escolar foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_sello_automatico_escolar foreign key(id_sello_automatico_escolar) references sello_automatico_escolar(id)
);

create table if not exists orden_sello_madera (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_sello_madera bigint not null,
    constraint fk_orden_sello_madera foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_sello_madera foreign key(id_sello_madera) references sello_madera(id)
);

create table if not exists orden_sobre (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_sobre bigint not null,
    constraint fk_orden_sobre foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_sobre foreign key(id_sobre) references sobre(id)
);

create table if not exists orden_sticker (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_sticker bigint not null,
    constraint fk_orden_sticker foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_sticker foreign key(id_sticker) references sticker(id)
);

create table if not exists orden_sublimacion (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_sublimacion bigint not null,
    constraint fk_orden_sublimacion foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_sublimacion foreign key(id_sublimacion) references sublimacion(id)
);

create table if not exists orden_talonario (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_talonario bigint not null,
    constraint fk_orden_talonario foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_talonario foreign key(id_talonario) references talonario(id)
);

create table if not exists orden_tarjeta (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_tarjeta bigint not null,
    constraint fk_orden_tarjeta foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_tarjeta foreign key(id_tarjeta) references tarjeta(id)
);

create table if not exists orden_turnero (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_turnero bigint not null,
    constraint fk_orden_turnero foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_turnero foreign key(id_turnero) references turnero(id)
);

create table if not exists orden_vinilo (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_vinilo bigint not null,
    constraint fk_orden_vinilo foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_vinilo foreign key(id_vinilo) references vinilo(id)
);

create table if not exists orden_vinilo_plastico_corrugado (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_vinilo_plastico_corrugado bigint not null,
    constraint fk_orden_vinilo_plastico_corrugado foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_vinilo_plastico_corrugado foreign key(id_vinilo_plastico_corrugado) references vinilo_plastico_corrugado(id)
);

create table if not exists orden_vinilo_de_corte (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_vinilo_de_corte bigint not null,
    constraint fk_orden_vinilo_de_corte foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_vinilo_de_corte foreign key(id_vinilo_de_corte) references vinilo_de_corte(id)
);

create table if not exists orden_otro (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    id_orden_trabajo bigint not null,
    id_otro bigint not null,
    constraint fk_orden_otro foreign key(id_orden_trabajo) references orden_trabajo(id),
    constraint fk_otro foreign key(id_otro) references otro(id)
);