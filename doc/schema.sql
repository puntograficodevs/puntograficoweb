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
('Sol', 'solpm', 'tinta2025!', 1),
('Andrea', 'andreapm', 'tinta2025!', 1),
('Ben', 'benpm', 'BenYMar2605!', 2),
('Anto', 'antopm', 'tinta2025!', 3),
('Jose', 'josepm', 'tinta2025!', 3),
('Myrna', 'myrnapm', 'tinta2025!', 3),
('Meli', 'melipm', 'tinta2025!', 3),
('Mari', 'maripm', 'tinta2025!', 3);

-- crear los configurables
create table if not exists producto (
    id bigint auto_increment primary key,
    nombre varchar(255) not null
);

create table if not exists item_configurable (
    id bigint auto_increment primary key,
    nombre varchar(255) not null,
    tipo varchar(255) not null, -- Ej: "select", "boolean"
    es_obligatorio tinyint(1) not null default 0,
    id_producto bigint not null,
    constraint fk_item_configurable_producto foreign key (id_producto) references producto(id)
);

create table if not exists item_configurable_opcion (
    id bigint auto_increment primary key,
    valor varchar(255) not null,
    precio double not null,
    id_item_configurable bigint not null,
    constraint fk_opcion_item_configurable foreign key (id_item_configurable) references item_configurable(id)
);

create table if not exists item_personalizable (
    id bigint auto_increment primary key,
    nombre varchar(255) not null,
    tipo varchar(255) not null, -- Ej: "text", "number",
    precio double not null,
    id_producto bigint not null,
    constraint fk_item_personalizable_producto foreign key (id_producto) references producto(id)
);

-- Instancia concreta de un producto con total, dentro de una orden
create table if not exists producto_orden (
    id bigint auto_increment primary key,
    id_producto bigint not null,
    total double not null,
    constraint fk_producto_orden_producto foreign key (id_producto) references producto(id)
);

-- Orden de trabajo con varios productos
create table if not exists orden_trabajo (
    id bigint auto_increment primary key,
    nombre_cliente varchar(255) not null,
    telefono_cliente varchar(255) not null,
    es_cuenta_corriente tinyint(1) not null default 0,
    fecha_orden date not null,
    fecha_muestra date null,
    fecha_entrega date not null,
    hora_entrega varchar(255) null,
    medio_pago varchar(255) null,
    estado_pago varchar(255) not null,
    estado_orden varchar(255) not null,
    total double not null,
    abonado double not null,
    resta double not null,
    id_empleado bigint not null,
    constraint fk_orden_empleado foreign key (id_empleado) references empleado(id)
);

-- Relación entre producto_orden y orden_trabajo
create table if not exists orden_producto (
    id bigint auto_increment primary key,
    id_orden_trabajo bigint not null,
    id_producto_orden bigint not null,
    constraint fk_orden_producto_orden foreign key (id_orden_trabajo) references orden_trabajo(id),
    constraint fk_orden_producto_producto_orden foreign key (id_producto_orden) references producto_orden(id)
);
