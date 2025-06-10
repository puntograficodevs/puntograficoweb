-- BASE DE PRODUCTOS
create table if not exists PRODUCTO-NUEVO-PA (
    id bigint auto_increment not null primary key,
    con_adicional_disenio tinyint(1) not null default 5000,
    precio int not null default 0,
    cantidad int not null default 1,
    enlace_archivo varchar(255) not null default '-',
    informacion_adicional varchar(1000) not null default '-',
    id_tipo_producto bigint not null default ID DE PRODUCTO-NUEVO-PA,
    constraint fk_tipo_producto_PRODUCTO-NUEVO-PA foreign key (id_tipo_producto) references tipo_producto(id)
);