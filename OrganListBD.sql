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
	descripcion varchar(255) null,
	usuario int unsigned not null
);

alter table Lista
	add constraint FK_usuario_id foreign key (usuario) references Usuario(id);

create table Producto(
	id int unsigned primary key auto_increment,
	nombre varchar(70) not null,
	descripcion varchar(255) null,
	esencial boolean not null,
	categoria varchar(100) not null
);

create table ListaProducto(
	id int unsigned primary key auto_increment,
	lista int unsigned not null,
	producto int unsigned not null
);

alter table ListaProducto
	add constraint FK_lista_id foreign key (lista) references Lista(id);
alter table ListaProducto
    add constraint FK_producto_id foreign key (producto) references Producto(id);

create table ProductoTienda(
	id int unsigned primary key auto_increment,
	producto int unsigned not null,
	tienda int unsigned not null,
	precio decimal(6, 2) null
);

create table Tienda(
	id int unsigned primary key auto_increment,
	nombre varchar(70) not null,
	tipo varchar(70) not null
);

alter table ProductoTienda
	add constraint FK_tienda_id foreign key (tienda) references Tienda(id);
alter table ProductoTienda
	add constraint FK_producto_id foreign key (producto) references Producto(id);

insert into Usuario values(1, 'Mario', 'mariofernandezs1@gmail.com', '657152931', '123');
insert into Lista values (1, 'lista1', 0, 'descripcion', 1);
insert into Producto 
values (1, 'coca cola', 'bebida formula secreta', 1, 'Refrescos'),
(2, 'Lay\'s', 'Patatas fritas de bolsa', 1, 'Patatas de bolsa');
insert into ListaProducto values (1, 1, 1), (2, 1, 2);
insert into Tienda values (1, 'Carrefour', 'Supermercado');
insert into ProductoTienda values (1, 1, 1, 2.0), (2, 2, 1, 1.5);
