create database Papeleria

use Papeleria

create table proveedor(
	nombre char(20)
)

create table articulos(

	cve int not null identity(1,1) primary key,
	
	tipo varchar(20) not null,
	descripcion char(30),
	cantidad int, 
	precioCompra int,
	precioVenta int,
	proveedor varchar(30)

)

create table historial(

	cve int not null identity(1,1) primary key,
	accion char(20),
	tipo varchar(20) not null,
	descripcion char(30),
	cantidad int, 
	precioCompra int,
	precioVenta int,
	fecha datetime,
	proveedor varchar(30)
)
update articulos set precioCompra = 10 where cve = 2
insert into articulos values('Papel', 'Papel normal', 5)
delete from articulos
select cve, max(cantidad) from articulos
group by cve
select * from articulos
select * from historial
update articulos set cantidad = 5
select * from articulos
select * from historial
drop table historial
drop table articulos
drop table articulos