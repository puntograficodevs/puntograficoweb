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

create table if not exists categoria_producto (
	id bigint auto_increment not null primary key,
	nombre varchar(255) not null unique
);

create table if not exists orden_trabajo (
	id bigint auto_increment not null primary key,
	cliente_nombre varchar(255) not null,
	cliente_telefono varchar(255) not null,
	es_cuenta_corriente tinyint(1) not null default 0,
	fecha_pedido date not null,
	fecha_muestra date null,
	fecha_entrega date not null,
	hora_entrega varchar(255) null,
	medio_pago varchar(255) null,
	estado_pago varchar(255) not null,
	estado_orden varchar(255) not null,
	cantidad int not null default 1,
	total double not null default 1,
	abonado double not null default 0,
	resta double not null default 1,
	id_empleado bigint not null,
	id_categoria bigint not null,
	constraint fk_orden_empleado foreign key (id_empleado) references empleado(id),
	constraint fk_orden_categoria foreign key (id_categoria) references categoria_producto(id)
);

create table if not exists sello_madera (
	id bigint auto_increment not null primary key,
	tamanio_sello_madera varchar(255) not null,
	tamanio_personalizado varchar(255) null,
	adicional_perilla tinyint(1) null,
	texto_linea_uno varchar(255) not null,
	texto_linea_dos varchar(255) null,
	texto_linea_tres varchar(255) null,
	texto_linea_cuatro varchar(255) null,
	tipografia_linea_uno varchar(255) null,
	archivo varchar(1000) null,
	adicional_disenio tinyint(1) null,
	id_orden_trabajo bigint not null unique,
	constraint fk_sello_madera_orden foreign key (id_orden_trabajo) references orden_trabajo(id)
);

create table if not exists sello_automatico_escolar (
	id bigint auto_increment not null primary key,
	modelo_sello_automatico_escolar varchar(255) not null,
	es_vertical tinyint(1) null,
	es_horizontal tinyint(1) null,
	texto_linea_uno varchar(255) not null,
	texto_linea_dos varchar(255) null,
	texto_linea_tres varchar(255) null,
	texto_linea_cuatro varchar(255) null,
	tipografia varchar(255) null,
	dibujo varchar(255) null,
	archivo varchar(1000) null,
	id_orden_trabajo bigint not null unique,
	constraint fk_sello_automatico_escolar_orden foreign key (id_orden_trabajo) references orden_trabajo(id)
);

create table if not exists sello_automatico (
	id bigint auto_increment not null primary key,
	es_profesional tinyint(1) null,
	es_particular tinyint(1) null,
	modelo_sello_automatico varchar(255) not null,
	texto_linea_uno varchar(255) not null,
	texto_linea_dos varchar(255) null,
	texto_linea_tres varchar(255) null,
	texto_linea_cuatro varchar(255) null,
	tipografia_linea_uno varchar(255) null,
	archivo varchar(255) null,
	id_orden_trabajo bigint not null unique,
	constraint fk_sello_automatico_orden foreign key (id_orden_trabajo) references orden_trabajo(id)
);

create table if not exists talonario (
	id bigint auto_increment not null primary key,
	tipo_talonario varchar(255) not null,
	tipo_troquelado_talonario varchar(255) not null,
	con_numerado tinyint(1) null,
	detalle_numerado varchar(255) null,
	modo_talonario varchar(255) null,
	es_encolado tinyint(1) null,
	tipo_color varchar(255) not null,
	medida_talonario varchar(255) not null,
	medida_personalizada varchar(255) null,
	tipo_papel_talonario varchar(255) null,
	adicional_disenio tinyint(1) null,
	archivo varchar(255) null,
	detalle varchar(255) null,
	id_orden_trabajo bigint not null unique,
	constraint fk_talonario_orden foreign key (id_orden_trabajo) references orden_trabajo(id)
);

create table if not exists tarjeta (
	id bigint auto_increment not null primary key,
	tipo_papel_tarjeta varchar(255) not null,
	tipo_color varchar(255) not null,
	tipo_faz varchar(255) not null,
	tipo_laminado varchar(255) not null,
	medida_estandar_tarjeta varchar(255) not null,
	medida_personalizada varchar(255) null,
	cantidad_estandar_t_y_f varchar(255) not null,
	adicional_disenio tinyint(1) null,
	archivo varchar(255) null,
	detalle varchar(255) null,
	id_orden_trabajo bigint not null unique,
	constraint fk_tarjeta_orden foreign key (id_orden_trabajo) references orden_trabajo(id)
);

create table if not exists folleto (
	id bigint auto_increment not null primary key,
	tipo_papel_folleto varchar(255) not null,
	tipo_color varchar(255) not null,
	tipo_faz varchar(255) not null,
	tamanio_hoja_folleto varchar(255) not null,
	tipo_folleto varchar(255) not null,
	cantidad_estandar_t_y_f varchar(255) not null,
	con_plegado tinyint(1) null,
	adicional_disenio tinyint(1) null,
	archivo varchar(255) null,
	detalle varchar(255) null,
	id_orden_trabajo bigint not null unique,
	constraint fk_folleto_orden foreign key (id_orden_trabajo) references orden_trabajo(id)
);

create table if not exists sublimacion (
	id bigint auto_increment not null primary key,
	material_a_sublimar varchar(255) not null,
	adicional_disenio tinyint(1) null,
	archivo varchar(255) null,
	detalle varchar(255) null,
	id_orden_trabajo bigint not null unique,
	constraint fk_sublimacion_orden foreign key (id_orden_trabajo) references orden_trabajo(id)
);

create table if not exists rotulacion (
	id bigint auto_increment not null primary key,
	tipo_rotulacion varchar(255) not null,
	es_laminado tinyint(1) null,
	tipo_corte varchar(255) not null,
	horario_rotulacion varchar(255) null,
	direccion_rotulacion varchar(255) null,
	medida varchar(255) not null,
	adicional_disenio tinyint(1) null,
	archivo varchar(255) null,
	detalle varchar(255) null,
	id_orden_trabajo bigint not null unique,
	constraint fk_rotulacion_orden foreign key (id_orden_trabajo) references orden_trabajo(id)
);

create table if not exists vinilo_de_corte (
	id bigint auto_increment not null primary key,
	es_promocional tinyint(1) null,
	es_oracal tinyint(1) null,
	codigo_color varchar(255) null,
	con_colocacion tinyint(1) null,
	trae_material_vinilo varchar(255) not null,
	medida varchar(255) not null,
	adicional_disenio tinyint(1) null,
	archivo varchar(255) null,
	detalle varchar(255) null,
	id_orden_trabajo bigint not null unique,
	constraint fk_vinilo_de_corte_orden foreign key (id_orden_trabajo) references orden_trabajo(id)
);

create table if not exists vinilo (
	id bigint auto_increment not null primary key,
	medida varchar(255) not null,
	tipo_vinilo varchar(255) not null,
	adicional_vinilo varchar(255) not null,
	tipo_corte varchar(255) not null,
	adicional_disenio tinyint(1) null,
	archivo varchar(255) null,
	detalle varchar(255) null,
	id_orden_trabajo bigint not null unique,
	constraint fk_vinilo_orden foreign key (id_orden_trabajo) references orden_trabajo(id)
);

create table if not exists vinilo_plastico_corrugado (
	id bigint auto_increment not null primary key,
	medida varchar(255) not null,
	con_ojales tinyint(1) null,
	cantidad_ojales int null,
	adicional_disenio tinyint(1) null,
	archivo varchar(255) null,
	detalle varchar(255) null,
	id_orden_trabajo bigint not null unique,
	constraint fk_vinilo_plastico_corrugado_orden foreign key (id_orden_trabajo) references orden_trabajo(id)
);

create table if not exists lona (
	id bigint auto_increment not null primary key,
	medida_estandar_lona_comun varchar(255) not null,
	medida_personalizada varchar(255) null,
	con_ojales tinyint(1) null,
	con_ojales_con_refuerzo tinyint(1) null,
	con_bolsillos tinyint(1) null,
	con_demasia_para_tensado tinyint(1) null,
	con_solapado tinyint(1) null,
	tipo_lona varchar(255) not null,
	adicional_disenio tinyint(1) null,
	archivo varchar(255) null,
	detalle varchar(255) null,
	id_orden_trabajo bigint not null unique,
	constraint fk_lona_orden foreign key (id_orden_trabajo) references orden_trabajo(id)
);

create table if not exists lona_impresa (
	id bigint auto_increment not null primary key,
	medida_estandar_lona_impresa varchar(255) not null,
	con_adicional_portabanner tinyint(1) null,
	tipo_lona varchar(255) not null,
	adicional_disenio tinyint(1) null,
	archivo varchar(255) null,
	detalle varchar(255) null,
	id_orden_trabajo bigint not null unique,
	constraint fk_lona_impresa_orden foreign key (id_orden_trabajo) references orden_trabajo(id)
);

create table if not exists etiqueta (
	id bigint auto_increment not null primary key,
	medida varchar(255) not null,
	tipo_papel_etiqueta varchar(255) not null,
	tipo_laminado varchar(255) not null,
	con_perforacion tinyint(1) null,
	tamanio_perforacion varchar(255) null,
	tipo_faz varchar(255) not null,
	con_perforacion_adicional tinyint(1) null,
	con_marca_adicional tinyint(1) null,
	adicional_disenio tinyint(1) null,
	archivo varchar(255) null,
	detalle varchar(255) null,
	id_orden_trabajo bigint not null unique,
	constraint fk_etiqueta_orden foreign key (id_orden_trabajo) references orden_trabajo(id)
);

create table if not exists sticker (
	id bigint auto_increment not null primary key,
	medida varchar(255) not null,
	es_troquelado tinyint(1) null,
	tipo_troquelado varchar(255) null,
	adicional_disenio tinyint(1) null,
	archivo varchar(255) null,
	detalle varchar(255) null,
	id_orden_trabajo bigint not null unique,
	constraint fk_sticker_orden foreign key (id_orden_trabajo) references orden_trabajo(id)
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

insert into categoria_producto(nombre) values
('SELLO_MADERA'),
('SELLO_AUTOMATICO_ESCOLAR'),
('SELLO_AUTOMATICO'),
('TALONARIO'),
('TARJETA'),
('FOLLETO'),
('SUBLIMACION'),
('ROTULACION'),
('VINILO_DE_CORTE'),
('VINILO'),
('VINILO_PLASTICO_CORRUGADO'),
('LONA'),
('LONA_IMPRESA'),
('ETIQUETA'),
('STICKER');


