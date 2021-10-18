drop database if exists OrganList;
create database OrganList;
use OrganList;

create table Usuario(
	id int unsigned primary key auto_increment,
	nombre varchar(70) not null,
	email varchar(70) not null,
	telefono varchar(14) null,
	contrasena varchar(10) not null,
	constraint UC_email unique (email)
);
create table Lista(
	id int unsigned primary key auto_increment,
	nombre varchar(70) not null,
	favorita boolean not null,
	descripcion varchar(255) null
);

alter table Lista
	add constraint FK_usuario_lista foreign key (id);

create table ListaProducto(
	id int unsigned primary key auto_increment,
	lista int unsigned not null,
	producto int unsigned not null
);

alter table ListaProducto
	add constraint FK_relacion_lista foreign key (id);

create table Producto(
	id int unsigned primary key auto_increment,
	nombre varchar(70) not null,
	descripcion varchar(255) null,
	esencial boolean not null
	categoria varchar(100) not null
);

alter table Producto
	add constraint FK_relacion_producto foreign key (id);

create table ProductoTienda(
	id int unsigned primary key auto_increment,
	producto int unsigned not null,
	tienda int unsigned not null,
	precio decimal(6, 2) null
);

alter table ProductoTienda
	add constraint FK_relacion_producto_tienda foreign key (id);

create table Tienda(
	id int unsigned primary key auto_increment,
	nombre varchar(70) not null,
	tipo varchar(70) not null
);

alter table Tienda
	add constraint FK_relacion_tienda foreign key (id);