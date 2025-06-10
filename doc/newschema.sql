-- creacion de tablas iniciales
create table if not exists rol (
	id bigint auto_increment not null primary key,
	nombre varchar(255) not null unique
);

create table if not exists empleado (
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
('Sol', 'solpm', 'tinta2025!', 1),
('Andrea', 'andreapm', 'tinta2025!', 1),
('Ben', 'benpm', 'BenYMar2605!', 2),
('Anto', 'antopm', 'tinta2025!', 3),
('Jose', 'josepm', 'tinta2025!', 3),
('Myrna', 'myrnapm', 'tinta2025!', 3),
('Meli', 'melipm', 'tinta2025!', 3),
('Mari', 'maripm', 'tinta2025!', 3);

-- SELLO MADERA
create table if not exists tamanio_sello_madera (
    id bigint auto_increment not null primary key,
    tamanio varchar(255) not null,
    precio int not null
);

insert into tamanio_sello_madera(tamanio, precio) values
('6X3 CM', 16200),
('6X4 CM', 17850),
('6X5 CM', 19750),
('6x7 CM', 20650),
('6X8 CM', 21750),
('7X3 CM', 17850),
('7X5 CM', 21750),
('1X1 CM', 8100),
('2X2 CM', 8800),
('3X3 CM', 10550),
('4X4 CM', 11650),
('5X5 CM', 12450),
('6X6 CM', 16200),
('7X7 CM', 19350),
('8X8 CM', 22800),
('9X9 CM', 28000),
('10X10 CM', 30350),
('11X11 CM', 32650),
('12X12 CM', 38850),
('13X13 CM', 44400),
('14X14 CM', 52500),
('15X15 CM', 56600),
('10X15 CM', 47800);

create table if not exists sello_madera (
    id bigint auto_increment not null primary key,
    tamanio_personalizado varchar(255) null,
    precio_tamanio_personalizado int null,
    tiene_adicional_perilla tinyint(1) not null default 0,
    precio_adicional_perilla int not null default 2800,
    detalle_sello varchar(255) not null,
    tipografia varchar(255) null,
    enlace_archivo varchar(255) null,
    tiene_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 6000,
    id_tamanio_sello_madera bigint null,
    constraint fk_tamanio_sello_madera foreign key (id_tamanio_sello_madera) references tamanio_sello_madera(id)
);

create table if not exists orden_sello_madera (
    id bigint auto_increment not null primary key,
    nombre_cliente varchar(255) not null,
    telefono_cliente varchar(255) not null,
    es_cuenta_corriente tinyint(1) not null default 0,
    fecha_pedido date not null,
    fecha_muestra date null,
    fecha_entrega date not null,
    hora_entrega varchar(255) null,
    cantidad_productos int not null default 1,
    comentarios varchar(255) null,
    necesita_factura tinyint(1) default 0,
    medio_de_pago varchar(255) not null,
    total int not null,
    abonado int not null default 0,
    resta int not null,
    estado_pago varchar(255) not null,
    estado_orden varchar(255) not null,
    id_empleado bigint not null,
    id_sello_madera bigint not null,
    constraint fk_empleado_orden_sello_madera foreign key (id_empleado) references empleado(id),
    constraint fk_sello_madera_orden foreign key (id_sello_madera) references sello_madera(id)
);

-- SELLO AUTOMÁTICO ESCOLAR
create table if not exists modelo_sello_escolar (
    id bigint auto_increment not null primary key,
    modelo varchar(255) not null,
    precio int not null
);

insert into modelo_sello_escolar(modelo, precio) values
('20 COLOP - HORIZONTAL', 18850),
('20 COLOP - VERTICAL', 18850),
('10 COLOP - HORIZONTAL', 15800);

create table if not exists sello_automatico_escolar (
    id bigint auto_increment not null primary key,
    texto_linea_uno varchar(255) not null,
    texto_linea_dos varchar(255) null,
    texto_linea_tres varchar(255) null,
    texto_linea_cuatro varchar(255) null,
    tipografia varchar(255) not null,
    dibujo varchar(255) null,
    id_modelo_sello_escolar bigint not null,
    constraint fk_modelo_sello_escolar foreign key (id_modelo_sello_escolar) references modelo_sello_escolar(id)
);

create table if not exists orden_sello_automatico_escolar (
    id bigint auto_increment not null primary key,
    nombre_cliente varchar(255) not null,
    telefono_cliente varchar(255) not null,
    es_cuenta_corriente tinyint(1) not null default 0,
    fecha_pedido date not null,
    fecha_muestra date null,
    fecha_entrega date not null,
    hora_entrega varchar(255) null,
    cantidad_productos int not null default 1,
    comentarios varchar(255) null,
    necesita_factura tinyint(1) default 0,
    medio_de_pago varchar(255) not null,
    total int not null,
    abonado int not null default 0,
    resta int not null,
    estado_pago varchar(255) not null,
    estado_orden varchar(255) not null,
    id_empleado bigint not null,
    id_sello_automatico_escolar bigint not null,
    constraint fk_empleado_orden_sello_automatico_escolar foreign key (id_empleado) references empleado(id),
    constraint fk_sello_automatico_escolar_orden foreign key (id_sello_automatico_escolar) references sello_automatico_escolar(id)
);

-- SELLO AUTOMÁTICO
create table if not exists modelo_sello_automatico (
    id bigint auto_increment not null primary key,
    modelo varchar(255) not null,
    precio int not null
);

insert into modelo_sello_automatico(modelo, precio) values
('MOUSE STAMP 20 COLOP 14X38MM', 22850),
('POCKET 20 COLOP 14X38MM', 22250),
('AUTOMÁTICO 20 COLOP 14X38MM', 18850),
('AUTOMÁTICO 10 COLOP 10X27MM', 15800),
('PRINTER C30 18X47MM', 30000),
('PRINTER 45 82X25MM', 44400),
('PRINTER 45 82X25MM', 44400),
('PRINTER C50 30X69MM', 42900),
('PRINTER 55 40X60MM', 44800),
('PRINTER 55 DATER 40X60', 51900),
('PRINTER C60 76X37MM', 46500),
('S 260 45X24MM', 48200),
('MINI DATER S120 40X45MM', 43050),
('MINI DATER S160 40X20MM', 27350),
('PRINTER R30', 40500),
('PRINTER R40', 43450);

create table if not exists sello_automatico (
    id bigint auto_increment not null primary key,
    es_profesional tinyint(1) not null default 0,
    es_particular tinyint(1) not null default 0,
    texto_linea_uno varchar(255) not null,
    texto_linea_dos varchar(255) null,
    texto_linea_tres varchar(255) null,
    texto_linea_cuatro varchar(255) null,
    tipografia_linea_uno varchar(255) not null,
    id_modelo_sello_automatico bigint not null,
    constraint fk_modelo_sello_automatico foreign key (id_modelo_sello_automatico) references modelo_sello_automatico(id)
);

create table if not exists orden_sello_automatico (
    id bigint auto_increment not null primary key,
    nombre_cliente varchar(255) not null,
    telefono_cliente varchar(255) not null,
    es_cuenta_corriente tinyint(1) not null default 0,
    fecha_pedido date not null,
    fecha_muestra date null,
    fecha_entrega date not null,
    hora_entrega varchar(255) null,
    cantidad_productos int not null default 1,
    comentarios varchar(255) null,
    necesita_factura tinyint(1) default 0,
    medio_de_pago varchar(255) not null,
    total int not null,
    abonado int not null default 0,
    resta int not null,
    estado_pago varchar(255) not null,
    estado_orden varchar(255) not null,
    id_empleado bigint not null,
    id_sello_automatico bigint not null,
    constraint fk_empleado_orden_sello_automatico foreign key (id_empleado) references empleado(id),
    constraint fk_sello_automatico_orden foreign key (id_sello_automatico) references sello_automatico(id)
);

-- SUBLIMACION
create table if not exists material_sublimacion (
    id bigint auto_increment not null primary key,
    material varchar(255) not null,
);

insert into material_sublimacion(id, material) values
(1, 'TAZA DE CERÁMICA'),
(2, 'TAZA DE POLÍMERO'),
(3, 'TAZA MÁGICA'),
(4, 'TAZA CON GLITTER'),
(5, 'MATE DE CERÁMICA'),
(6, 'MATE DE POLÍMERO'),
(7, 'JARRO DE CAFÉ'),
(8, 'LAPICERO DE POLÍMERO'),
(9, 'MOUSEPAD'),
(10, 'BIROME'),
(11, 'TELA'),
(12, 'CINTA FALLETINA'),
(13, 'LLAVERO CORTO LANYARD'),
(14, 'LLAVERO LARGO LANYARD');

create table if not exists sublimacion_personalizada (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    precio int not null default 0,
    id_material_sublimacion bigint not null,
    constraint fk_material_sublimacion_personalizada foreign key (id_material_sublimacion) references material_sublimacion(id)
);

create table if not exists oferta_sublimacion (
    id bigint auto_increment not null primary key,
    cantidad int not null default 1,
    precio int not null default 0,
    id_material_sublimacion bigint not null,
    constraint fk_material_oferta_sublimacion foreign key (id_material_sublimacion) references material_sublimacion(id)
);

insert into oferta_sublimacion(cantidad, precio, id_material_sublimacion) values
(1, 15450, 1),
(1, 11600, 2),
(1, 19500, 3),
(1, 26400, 4),
(1, 15450, 5),
(1, 12400, 6),
(1, 13900, 7),
(1, 6750, 8),
(1, 11050, 9),
(50, 53000, 10),
(100, 90150, 10),
(150, 106100, 10);

create table if not exists sublimacion (
    id bigint auto_increment not null primary key,
    tiene_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio int not null default 5000,
    enlace_archivo varchar(255) null,
    precio_total int not null,
    id_sublimacion_personalizada null,
    id_oferta_sublimacion bigint null,
    constraint fk_sublimacion_personalizada foreign key (id_sublimacion_personalizada) references sublimacion_personalizada(id)
    constraint fk_material_sublimacion foreign key (id_material_sublimacion) references material_sublimacion(id),
    constraint fk_oferta_sublimacion foreign key (id_oferta_sublimacion) references oferta_sublimacion(id)
);

create table if not exists orden_sublimacion (
    id bigint auto_increment not null primary key,
    nombre_cliente varchar(255) not null,
    telefono_cliente varchar(255) not null,
    es_cuenta_corriente tinyint(1) not null default 0,
    fecha_pedido date not null,
    fecha_muestra date null,
    fecha_entrega date not null,
    hora_entrega varchar(255) null,
    comentarios varchar(255) null,
    necesita_factura tinyint(1) default 0,
    medio_de_pago varchar(255) not null,
    total int not null,
    abonado int not null default 0,
    resta int not null,
    estado_pago varchar(255) not null,
    estado_orden varchar(255) not null,
    id_empleado bigint not null,
    id_sublimacion bigint not null,
    constraint fk_empleado_orden_sublimacion foreign key (id_empleado) references empleado(id),
    constraint fk_sublimacion_orden foreign key (id_sublimacion) references sublimacion(id)
);

-- FOLLETOS
create table if not exists tipo_papel_folleto (
    id bigint auto_increment not null primary key,
    papel varchar(255) not null
);

insert into tipo_papel_folleto(id, papel) values
(1, 'OBRA'),
(2, 'ILUSTRACION 115 GRS'),
(3, 'ILUSTRACION 150 GRS');

create table if not exists tipo_color (
    id bigint auto_increment not null primary key,
    color varchar(255) not null
);

insert into tipo_color(id, color) values
(1, 'BLANCO Y NEGRO'),
(2, 'COLOR');

create table if not exists tipo_faz (
    id bigint auto_increment not null primary key,
    faz varchar(255) not null
);

insert into tipo_faz(id, faz) values
(1, 'SIMPLE FAZ'),
(2, 'DOBLE FAZ');

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
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_folleto(id, tipo) values
(1, 'COMUN'),
(2, 'DIPTICO'),
(3, 'TRIPTICO');

create table if not exists cantidad_estandar_folletos (
    id bigint auto_increment not null primary key,
    cantidad int not null
);

insert into cantidad_estandar_folletos(id, cantidad) values
(1, 100),
(2, 200),
(3, 500),
(4, 1000);

create table if not exists oferta_folleto (
    id bigint auto_increment not null primary key,
    id_tipo_papel_folleto bigint not null,
    id_tipo_color bigint not null,
    id_tipo_faz bigint not null,
    id_tamanio_hoja_folleto bigint not null,
    id_tipo_folleto bigint null,
    id_cantidad_estandar_folletos bigint not null,
    precio int null default 0,
    constraint fk_tipo_papel_folleto foreign key (id_tipo_papel) references tipo_papel_folleto(id),
    constraint fk_tipo_color_folleto foreign key (id_tipo_color) references tipo_color(id),
    constraint fk_tipo_faz_folleto foreign key (id_tipo_faz) references tipo_faz(id),
    constraint fk_tamanio_hoja_folleto foreign key (id_tamanio_hoja_folleto) references tamanio_hoja_folleto(id),
    constraint fk_tipo_folleto foreign key (id_tipo_folleto) references tipo_folleto(id),
    constraint fk_cantidad_estandar_folletos foreign key (id_cantidad_estandar_folletos) references cantidad_estandar_folletos(id)
);

insert into oferta_folleto(id_tipo_papel_folleto, id_tipo_color, id_tipo_faz, id_tamanio_hoja_folleto, id_tipo_folleto, id_cantidad_estandar_folletos, precio) values
(1, 1, 1, 3, 1, 1, 6400),
(1, 1, 2, 3, 1, 1, 7600),
(1, 1, 1, 3, 1, 2, 9750),
(1, 1, 2, 3, 1, 2, 13500),
(1, 1, 1, 3, 1, 3, 21900),
(1, 1, 2, 3, 1, 3, 27300),
(1, 1, 1, 3, 1, 4, 41100),
(1, 1, 2, 3, 1, 4, 47500),
(1, 2, 1, 3, 1, 1, 14850),
(1, 2, 2, 3, 1, 1, 16750),
(1, 2, 1, 3, 1, 2, 26700),
(1, 2, 2, 3, 1, 2, 29800),
(1, 2, 1, 3, 1, 3, 64000),
(1, 2, 2, 3, 1, 3, 77500),
(1, 2, 1, 3, 1, 4, 111400),
(1, 2, 2, 3, 1, 4, 139600),
(2, 2, 1, 3, 1, 1, 18150),
(2, 2, 2, 3, 1, 1, 21600),
(2, 2, 1, 3, 1, 2, 31950),
(2, 2, 2, 3, 1, 2, 36900),
(2, 2, 1, 3, 1, 3, 77550),
(2, 2, 2, 3, 1, 3, 87100),
(2, 2, 1, 3, 1, 4, 140450),
(2, 2, 2, 3, 1, 4, 159100);

create table if not exists folleto_personalizado (
    id bigint auto_increment not null primary key,
    cantidad_personalizada int not null default 1,
    id_tipo_papel_folleto bigint not null,
    id_tipo_color bigint not null,
    id_tipo_faz bigint not null,
    id_tamanio_hoja_folleto bigint not null,
    id_tipo_folleto bigint null,
    precio int not null,
    constraint fk_tipo_papel_folleto foreign key (id_tipo_papel) references tipo_papel_folleto(id),
    constraint fk_tipo_color_folleto foreign key (id_tipo_color) references tipo_color(id),
    constraint fk_tipo_faz_folleto foreign key (id_tipo_faz) references tipo_faz(id),
    constraint fk_tamanio_hoja_folleto foreign key (id_tamanio_hoja_folleto) references tamanio_hoja_folleto(id),
    constraint fk_tipo_folleto foreign key (id_tipo_folleto) references tipo_folleto(id)
);

create table if not exists folleto (
    id bigint auto_increment not null primary key,
    con_adicional_disenio tinyint(1) not null default 0,
    precio_adicional_disenio not null default 5000,
    enlace_archivo varchar(255) null,
    total int not null,
    id_oferta_folleto bigint null,
    id_folleto_personalizado bigint null,
    constraint fk_oferta_folleto foreign key (id_oferta_folleto) references oferta_folleto(id),
    constraint fk_folleto_personalizado foreign key (id_folleto_personalizado) references folleto_personalizado(id)
);

create table if not exists orden_folleto (
    id bigint auto_increment not null primary key,
    nombre_cliente varchar(255) not null,
    telefono_cliente varchar(255) not null,
    es_cuenta_corriente tinyint(1) not null default 0,
    fecha_pedido date not null,
    fecha_muestra date null,
    fecha_entrega date not null,
    hora_entrega varchar(255) null,
    comentarios varchar(255) null,
    necesita_factura tinyint(1) default 0,
    medio_de_pago varchar(255) not null,
    total int not null,
    abonado int not null default 0,
    resta int not null,
    estado_pago varchar(255) not null,
    estado_orden varchar(255) not null,
    id_empleado bigint not null,
    id_folleto bigint not null,
    constraint fk_empleado_orden_folleto foreign key (id_empleado) references empleado(id),
    constraint fk_folleto_orden foreign key (id_folleto) references folleto(id)
);

-- LONA COMÚN
create table if not exists tipo_lona (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_lona(id, tipo) values
(1, 'LONA FRONT'),
(2, 'LONA BLACK LIGHT'),
(3, 'LONA BLOCKOUT');

create table if not exists medida_estandar_lona (
    id bigint auto_increment not null primary key,
    medida varchar(255) not null
)

insert into medida_estandar_lona(id, medida) values
(1, '40X60 CM'),
(2, '60X90 CM'),
(3, '70X100 CM'),
(4, '90X120 CM');

create table if not exists oferta_lona (
    id bigint auto_increment not null primary key,
    id_tipo_lona bigint not null,
    id_medida_estandar_lona bigint not null,
    con_bolsillos tinyint(1) not null,
    precio int not null,
    constraint fk_tipo_lona_comun foreign key (id_tipo_lona) references tipo_lona(id),
    constraint fk_medida_estandar_lona foreign key (id_medida_estandar_lona) references medida_estandar_lona(id)
);

insert into oferta_lona(id_tipo_lona, id_medida_estandar_lona, con_bolsillos, precio) values
(1, 1, 1, 20440),
(1, 2, 1, 23600),
(1, 3, 1, 27650),
(1, 4, 1, 29930);

create table if not exists lona_personalizada (
    id bigint auto_increment not null primary key,
    medida_personalizada varchar(255) null,
    con_ojales tinyint(1) not null default 0,
    precio_con_ojales int not null default 0,
    con_bolsillos tinyint(1) not null default 0,
    precio_con_bolsillos int null default 0,
    con_ojales_metalicos tinyint(1) not null default 0,
    precio_con_ojales_metalicos int not null default 0,
    con_demasia_para_tensado tinyint(1) not null default 0,
    precio_con_demasia_para_tensado int not null default 0,
    con_solapado tinyint(1) not null default 0,
    precio_con_solapado int not null default 0,
    precio_total int not null,
    id_tipo_lona bigint not null,
    id_medida_estandar_lona bigint null,
    constraint fk_tipo_lona_personalizada foreign key (id_tipo_lona) references tipo_lona(id),
    constraint fk_medida_lona_personalizada foreign key (id_medida_estandar_lona) references medida_estandar_lona(id)
);

create table if not exists lona (
    id bigint auto_increment not null primary key,
    con_adicional_disenio tinyint(1) not null default 0,
    precio_con_adicional_disenio int not null default 5000,
    enlace_archivo varchar(255) null,
    total int not null default 0,
    id_oferta_lona bigint null,
    id_lona_personalizada bigint null,
    constraint fk_oferta_lona foreign key (id_oferta_lona) references oferta_lona(id),
    constraint fk_lona_personalizada foreign key (id_lona_personalizada) references lona_personalizada(id)
);

create table if not exists orden_lona (
    id bigint auto_increment not null primary key,
    nombre_cliente varchar(255) not null,
    telefono_cliente varchar(255) not null,
    es_cuenta_corriente tinyint(1) not null default 0,
    fecha_pedido date not null,
    fecha_muestra date null,
    fecha_entrega date not null,
    hora_entrega varchar(255) null,
    cantidad_productos int not null default 1,
    comentarios varchar(255) null,
    necesita_factura tinyint(1) not null default 0,
    medio_de_pago varchar(255) not null,
    total int not null,
    abonado int not null default 0,
    resta int not null,
    estado_pago varchar(255) not null,
    estado_orden varchar(255) not null,
    id_empleado bigint not null,
    id_lona bigint not null,
    constraint fk_empleado_orden_lona foreign key (id_empleado) references empleado(id),
    constraint fk_lona_orden foreign key (id_lona) references lona(id)
);

-- TARJETA
create table if not exists tipo_papel_tarjeta (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_papel_tarjeta(id, tipo) values
(1, 'OPALINA 180GRS'),
(2, 'OPALINA 210GRS'),
(3, 'ILUSTRACION 250GRS'),
(4, 'ILUSTRACION 300GRS');

create table if not exists tipo_laminado (
    id bigint auto_increment not null primary key,
    tipo varchar(255) not null
);

insert into tipo_laminado(id, tipo) values
(1, 'NINGUNO'),
(2, 'BRILLANTE'),
(3, 'MATE');

create table if not exists cantidad_estandar_tarjeta (
    id bigint auto_increment not null primary key,
    cantidad int not null
);

insert into cantidad_estandar_tarjeta(id, cantidad) values
(1, 50),
(2, 100),
(3, 200),
(4, 500);

create table if not exists oferta_tarjeta (
    id bigint auto_increment not null primary key,
    medida varchar(255) not null default '9X5 CM',
    precio int not null,
    id_tipo_papel_tarjeta bigint not null,
    id_tipo_color bigint not null,
    id_tipo_faz bigint not null,
    id_tipo_laminado bigint not null,
    id_cantidad_estandar_tarjeta bigint not null,
    constraint fk_papel_tarjeta_oferta foreign key (id_tipo_papel_tarjeta) references tipo_papel_tarjeta(id),
    constraint fk_tipo_color_tarjeta foreign key (id_tipo_color) references tipo_color(id),
    constraint fk_tipo_faz_tarjeta foreign key (id_tipo_faz) references tipo_faz(id),
    constraint fk_tipo_laminado_oferta_tarjeta foreign key (id_tipo_laminado) references tipo_laminado(id),
    constraint fk_cantidad_estandar_tarjeta foreign key (id_cantidad_estandar_tarjeta) references cantidad_estandar_tarjeta(id)
);

insert into oferta_tarjeta(id_tipo_papel_tarjeta, id_tipo_color, id_tipo_faz, id_tipo_laminado, id_cantidad_estandar_tarjeta, precio) values
(3, 1, 1, 1, 1, 10290), -- ilus250, byn, simple, sin, 50
(3, 2, 1, 1, 1, 10290), -- ilus250, color, simple, sin, 50
(3, 1, 1, 2, 1, 12270), -- ilus250, byn, simple, bril, 50
(3, 2, 1, 2, 1, 12270), -- ilus250, color, simple, bril, 50
(3, 1, 1, 3, 1, 12820), -- ilus250, byn, simple, mate, 50
(3, 2, 1, 3, 1, 12820), -- ilus250, color, simple, mate, 50
(3, 1, 2, 1, 1, 11350), -- ilus250, byn, doble, sin, 50
(3, 2, 2, 1, 1, 11350), -- ilus250, color, doble, sin, 50
(3, 1, 2, 2, 1, 13330), -- ilus250, byn, doble, bril, 50
(3, 2, 2, 2, 1, 13330), -- ilus250, color, doble, bril, 50
(3, 1, 2, 3, 1, 13880), -- ilus250, byn, doble, mate, 50
(3, 2, 2, 3, 1, 13880), -- ilus250, color, doble, mate, 50
(3, 1, 1, 1, 2, 16500), -- ilus250, byn, simple, sin, 100
(3, 2, 1, 1, 2, 16500), -- ilus250, color, simple, sin, 100
(3, 1, 1, 2, 2, 19140), -- ilus250, byn, simple, bril, 100
(3, 2, 1, 2, 2, 19140), -- ilus250, color, simple, bril, 100
(3, 1, 1, 3, 2, 19800), -- ilus250, byn, simple, mate, 100
(3, 2, 1, 3, 2, 19800), -- ilus250, color, simple, mate, 100
(3, 1, 2, 1, 2, 19950), -- ilus250, byn, doble, sin, 100
(3, 2, 2, 1, 2, 19950), -- ilus250, color, doble, sin, 100
(3, 1, 2, 2, 2, 22590), -- ilus250, byn, doble, bril, 100
(3, 2, 2, 2, 2, 22590), -- ilus250, color, doble, bril, 100
(3, 1, 2, 3, 2, 23250), -- ilus250, byn, doble, mate, 100
(3, 2, 2, 3, 2, 23250), -- ilus250, color, doble, mate, 100
(3, 1, 1, 1, 2, 27560), -- ilus250, byn, simple, sin, 200
(3, 2, 1, 1, 2, 27560), -- ilus250, color, simple, sin, 200
(3, 1, 1, 2, 2, 32840), -- ilus250, byn, simple, bril, 200
(3, 2, 1, 2, 2, 32840), -- ilus250, color, simple, bril, 200
(3, 1, 1, 3, 2, 34160), -- ilus250, byn, simple, mate, 200
(3, 2, 1, 3, 2, 34160), -- ilus250, color, simple, mate, 200
(3, 1, 2, 1, 2, 31850), -- ilus250, byn, doble, sin, 200
(3, 2, 2, 1, 2, 31850), -- ilus250, color, doble, sin, 200
(3, 1, 2, 2, 2, 37130), -- ilus250, byn, doble, bril, 200
(3, 2, 2, 2, 2, 37130), -- ilus250, color, doble, bril, 200
(3, 1, 2, 3, 2, 38450), -- ilus250, byn, doble, mate, 200
(3, 2, 2, 3, 2, 38450), -- ilus250, color, doble, mate, 200
(3, 1, 1, 1, 2, 59100), -- ilus250, byn, simple, sin, 500
(3, 2, 1, 1, 2, 59100), -- ilus250, color, simple, sin, 500
(3, 1, 1, 2, 2, 72300), -- ilus250, byn, simple, bril, 500
(3, 2, 1, 2, 2, 72300), -- ilus250, color, simple, bril, 500
(3, 1, 1, 3, 2, 75600), -- ilus250, byn, simple, mate, 500
(3, 2, 1, 3, 2, 75600), -- ilus250, color, simple, mate, 500
(3, 1, 2, 1, 2, 69000), -- ilus250, byn, doble, sin, 500
(3, 2, 2, 1, 2, 69000), -- ilus250, color, doble, sin, 500
(3, 1, 2, 2, 2, 82200), -- ilus250, byn, doble, bril, 500
(3, 2, 2, 2, 2, 82200), -- ilus250, color, doble, bril, 500
(3, 1, 2, 3, 2, 85500), -- ilus250, byn, doble, mate, 500
(3, 2, 2, 3, 2, 85500); -- ilus250, color, doble, mate, 500


create table if not exists tarjeta_personalizada (
    id bigint auto_increment not null primary key,
    medida varchar(255) not null default '8X5 CM',
    cantidad int not null default 1,
    precio int not null default 0,
    id_tipo_papel_tarjeta bigint not null,
    id_tipo_color bigint not null,
    id_tipo_faz bigint not null,
    id_tipo_laminado bigint not null,
    constraint fk_papel_tarjeta_personalizada foreign key (id_tipo_papel_tarjeta) references tipo_papel_tarjeta(id),
    constraint fk_tipo_color_tarjeta_personalizada foreign key (id_tipo_color) references tipo_color(id),
    constraint fk_tipo_faz_tarjeta_personalizada foreign key (id_tipo_faz) references tipo_faz(id),
    constraint fk_tipo_laminado_tarjeta_personalizada foreign key (id_tipo_laminado) references tipo_laminado(id),
);

create table if not exists tarjeta(
    id bigint auto_increment not null primary key,
    con_adicional_disenio tinyint(1) not null default 5000,
    precio_adicional_disenio int not null default x,
    id_oferta_tarjeta bigint null,
    id_tarjeta_personalizada bigint null,
    constraint fk_oferta_tarjeta foreign key (id_oferta_tarjeta) references oferta_tarjeta(id),
    constraint fk_tarjeta_personalizada foreign key (id_tarjeta_personalizada) references tarjeta_personalizada(id)
);

create table if not exists orden_tarjeta (
    id bigint auto_increment not null primary key,
    nombre_cliente varchar(255) not null,
    telefono_cliente varchar(255) not null,
    es_cuenta_corriente tinyint(1) not null default 0,
    fecha_pedido date not null,
    fecha_muestra date null,
    fecha_entrega date not null,
    hora_entrega varchar(255) null,
    cantidad_productos int not null default 1,
    comentarios varchar(255) null,
    necesita_factura tinyint(1) not null default 0,
    medio_de_pago varchar(255) not null,
    total int not null,
    abonado int not null default 0,
    resta int not null,
    estado_pago varchar(255) not null,
    estado_orden varchar(255) not null,
    id_empleado bigint not null,
    id_tarjeta bigint not null,
    constraint fk_empleado_orden_lona foreign key (id_empleado) references empleado(id),
    constraint fk_tarjeta_orden foreign key (id_tarjeta) references tarjeta(id)
);







