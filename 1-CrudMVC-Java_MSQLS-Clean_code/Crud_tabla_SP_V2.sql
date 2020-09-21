create database mvcrud
use mvcrud

create table datosPersona
(
	rfc varchar(10) not null primary key,
	nombre varchar(50) not null,
	edad int not null,
	idCiudad int not null
)

--Funcion existencia
go
create function consultaExistenciaRFC (@rfc varchar(10)) returns int
as
begin
	if not exists(select * from datosPersona where rfc like @rfc)	
	begin
		return 1
	end
	return 0
end

go
--Guarda datos de un usuario
CREATE PROCEDURE altaPersona
(@rfc varchar(10), @nombre varchar(50), @edad int, @idCiudad int)
as
begin

	--por motivos de integridad aunque intentes registrar 2 personas con el mismo PK (RFC) el sistema no te lo
	--permitirá mostrando un mensaje, sin embargo al hacerlo de esta manera el mensaje que le digo que muestre
	--al usuario sea más entendible a diferencia del por default

	declare @error int
	set @error = (select dbo.consultaExistenciaRFC(@rfc))

	if(@error = 0)
	begin
		raiserror('RFC registrado anteriormente', 16, 1)
		return 1
	end

	BEGIN TRANSACTION

		insert into datosPersona values (@rfc, @nombre, @edad, @idCiudad)
		if @@error<>0
		begin
			raiserror('Error al insertar usuario', 16, 1)
			rollback
			return 1
		end

	COMMIT TRAN
	return 0
end
go
--Recupera datos del usuario
CREATE PROCEDURE recuperaPersona
(@rfc varchar(10))
as
begin

	if((select dbo.consultaExistenciaRFC(@rfc)) = 1)
	begin
		raiserror('RFC no registrado anteriormente', 16, 1)
		return 1
	end

	select  *
	from datosPersona
	where rfc = @rfc

	return 0
end
go

--Modificar datos de usuario

CREATE PROCEDURE modificaPersona
(@rfc varchar(10), @nombre varchar(50), @edad int, @idCiudad int)
as
begin
	declare @error int
	set @error = (select dbo.consultaExistenciaRFC(@rfc))

	if(@error = 1)
	begin
		raiserror('RFC no registrado anteriormente', 16, 1)
		return 1
	end

	BEGIN TRANSACTION

		update datosPersona set nombre = @nombre, edad = @edad, idCiudad = @idCiudad
		where rfc = @rfc
		if @@error<>0
		begin
			raiserror('Error al modificar usuario', 16, 1)
			rollback
			return 1
		end
	COMMIT TRAN
	return 0
end
go
--Eliminar usuaruio

CREATE PROCEDURE eliminarPersona
(@rfc varchar(10))
as
begin

	declare @error int
	set @error = (select dbo.consultaExistenciaRFC(@rfc))

	if(@error = 1)
	begin
		raiserror('RFC no registrado anteriormente', 16, 1)
		return 1
	end

	BEGIN TRANSACTION

		delete from datosPersona where rfc like @rfc
		if @@error<>0
		begin
			raiserror('Error al eliminar usuario', 16, 1)
			rollback
			return 1
		end
	COMMIT TRAN
	return 0
end
go

