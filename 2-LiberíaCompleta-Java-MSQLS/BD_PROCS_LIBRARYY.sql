use LIBRARYY
/*exec sp_droptype member_no
exec sp_droptype title
exec sp_droptype title_no
exec sp_droptype isbn
exec sp_droptype zipcode*/

go
execute sp_addtype member_no ,'INT' ,'NOT NULL'
execute sp_addtype title  ,'varchar(20)' ,'NOT NULL'
execute sp_addtype title_no  ,'INT' ,'NOT NULL'
EXEC  sp_addtype isbn, 'smallint', 'NOT NULL'
EXEC  sp_addtype zipcode, 'char(10)'
go
/*****************************/
--create database LIBRARYY
use LIBRARYY
create table claves(cves member_no primary key)
create table claves_tit(cves title_no primary key)
--delete from member
create table member
(
	member_no member_no primary key,
	nombre char(20) not null,
	lastname char(20) not null,
	--middleinitial char(1)
)
create table adult
(
	adult_member_no member_no primary key references member(member_no),
	street char(20) not null,
	zipcode zipcode check (zipcode like '[0-9][0-9][0-9][0-9][0-9]'),
	city char(20),
	state char(20),
	phone_no char(20),
	expr_date datetime
)

create table juvenile
(
	juvenile_member_no member_no primary key references member(member_no),
	 juvenile_adult_member_no member_no references adult(adult_member_no),
	 birthdate datetime not null,
	check (juvenile_adult_member_no <> juvenile_member_no)
)

create table title
(
	title_no title_no primary key,
	title title not null,
	author char(20) not null,
	synopsis text 
)

CREATE TABLE item 
(
	 isbn isbn PRIMARY KEY,
	 title_no title_no references title(title_no),
	 idioma VARCHAR(20) NOT NULL,
	 cover VARCHAR(20) NULL,
	 loanable char(2)
)

CREATE TABLE COPY 
(
	isbn isbn,
	copy_no int not null,
	title_no title_no references title(title_no),
	on_loan char (2),
	primary key (isbn,copy_no)
)

---------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE loan 
(
	--otracve int identity(1,1),
	isbn isbn, 
	copy_no int not null,
	title_no int not null references title(title_no),
	member_no int not null references member(member_no),
	out_date datetime not null,
	due_date datetime not null,
	primary key (isbn, copy_no, out_date),
	foreign key (isbn, copy_no) references copy (isbn, copy_no)
)

create table reservation
(
	isbn isbn references item(isbn),
	member_no member_no references member(member_no),
	log_date datetime,
	remarks varchar(100)
	primary key (isbn, member_no)
)

--drop table loanhist
create table loanhist(
	--otracve int not null identity(1,1),
	isbn isbn, 
	copy_no int not null,
	title_no int not null,-- references title(title_no),
	member_no int not null, --references member(member_no),
	out_date datetime not null,
	due_date datetime not null,
	in_date datetime not null,
	fine_assessed float,
	fine_paid float,
	remarks text,
	primary key (isbn, copy_no, in_date)
	--foreign key (isbn, copy_no) references copy (isbn, copy_no)
)
go

go
use LIBRARYY

/* POR SI YA ESTÁN
drop proc alta_adulto
drop proc elimina_member_adulto
drop proc ingresa_miembro
drop proc añade_copias
drop proc rellena_campos
drop proc rellena_campo_isbn
drop proc alta_libros
drop proc alta_prestamo
drop proc alta_reservacion
drop proc consulta_miembros_cve
drop proc consulta_miembros
drop proc consulta_miembros_adultos
drop proc consulta_miembros_adultos_todos
drop proc consulta_miembros_jovenes
drop  PROCEDURE consulta_miembros_joven_todos
drop  PROCEDURE elimina_member_juvenile
drop  PROCEDURE baja_libro
drop  PROCEDURE regresar_libro
drop proc regresa_multa
drop proc existencia_isbn_copy
drop proc adeudo
drop proc con_memb_deu
*/

go
--drop proc elimina_member_adulto
create procedure elimina_member_adulto
@member_no int
as
begin

	if not exists (select * from member where member_no = @member_no)
	begin
		raiserror('Miembro no existe', 16, 1)
		return 1
	end
	if not exists (select * from adult where adult_member_no = @member_no)
	begin
		exec elimina_member_juvenile @member_no
		--raiserror('Miembro no es adulto', 16, 1)
		return 0
	end
	if exists (select * from juvenile where juvenile_adult_member_no = @member_no)
	begin
		raiserror('No se puede borrar, hay jovenes a cargo de este miembro', 16, 1)
		return 1
	end

	if exists (select * from loan where member_no = @member_no)
	begin
		raiserror('Miembro tiene préstamos', 16, 1)
		return 1
	end
	if exists (select * from loanhist where member_no = @member_no and fine_assessed>fine_paid)
	begin
		raiserror('Miembro con adeudo', 16, 1)
		return 1
	end
	
	BEGIN TRAN

		delete reservation where member_no = @member_no
		if @@error <>0
		begin
			raiserror('error al borrar', 16, 1)
			return 1
		end
		delete adult where adult_member_no = @member_no
		if @@error <>0
		begin
			raiserror('error al borrar', 16, 1)
			return 1
		end
		delete member where member_no = @member_no
		if @@error <>0
		begin
			raiserror('error al borrar', 16, 1)
			return 1
		end
	COMMIT TRAN
	return 0
end
go

--use LIBRARYY
--exec alta_adulto 1, 'nombre', 'apellido', 'calle', 12345, 'ciudad', 'estado', 'telefono', '11-11-11'
CREATE PROCEDURE alta_adulto
(@adult_member_no int, @nombre char(20), @apellido char(20), @street char(20), @zipcode zipcode,
 @city char(20), @statee char(20), @phone_no char(20), @expr_date datetime)
as
begin
	if (@adult_member_no like '' or @nombre  like '' or @apellido  like '' 
	 or @street  like ''  or @zipcode  like ''  or @city  like ''  or @statee  like ''
	 or @expr_date  like '') --or @phone_no  like ''  )
	begin
		raiserror('llena campos correctamente',16,1)
		return 1
	end 
	if ( @zipcode not like '[0-9][0-9][0-9][0-9][0-9]')
	begin
		raiserror('Campo ZipCode incorrecto, debe contener 5 números, ejemplo: 12345',16,1)
		return 1
	end
	if exists(select * from member where member_no = @adult_member_no)	
	begin
		raiserror('usuario ya existe',16,1)
		return 1
	end
	BEGIN TRANSACTION
		insert into member values (@adult_member_no, @nombre, @apellido)
		if @@error<>0
		begin
			raiserror('error al insertar miembro', 16,1)
			rollback
			return 1
		end
		insert into adult values( @adult_member_no, @street, @zipcode, @city, @statee, @phone_no, @expr_date)
		if @@error<>0
		begin
			raiserror('error al insertar adulto', 16, 11)
			rollback
			return 1
		end
	COMMIT TRAN
	return 0
end

go
 --borra el procedimiento
	--exec 
--exec ingresa_miembro 16, 'aaaa','aaaaa', 2, '26/08/2000' 
 --select * from member
 --inicia procedimiento
 --use libraryy
CREATE PROCEDURE ingresa_miembro --joven
(@member_no int, @nombre char(20), @apellido char(20), @adult_member_no int, @birth_date datetime)
as
begin
	if (@nombre like '' or @apellido  like '' or @adult_member_no  like '' 
	 or @member_no  like ''  or @birth_date  like '' )
	 begin
			raiserror('llena campos correctamente',16,1)
			return 1
		end 
	if exists(select * from member where member_no = @member_no)	
		begin
			raiserror('usuario ya existe',16,1)
			return 1
		end
	if exists (select * from member where member_no = @adult_member_no)
		if not exists(select * from adult where adult_member_no = @adult_member_no)
		begin
			raiserror('Clave del campo tutor no existe como adulto, está siendo ocupada por un joven',16,1)
			return 1
		end

	if not exists(select * from adult where adult_member_no = @adult_member_no)	
	begin
		raiserror('Clave del tutor no existe',16,1)
		return 1
	end
	--04/05/2019 - 04/05/2009 = 10 --- 10 > 18? : no
	
if ((datediff(year, @birth_date, getdate())) > 18)
	begin 
		raiserror('usuario mayor a 18',16,1)
		return 1
	end
	if(select expr_date from adult where @adult_member_no = adult_member_no) < getdate() 
	begin
		raiserror('credencial adulto expirada',16,1)
		return 1
	end 

	BEGIN TRANSACTION
		insert into member values (@member_no, @nombre, @apellido)
		if @@error<>0
		begin
			raiserror('error al insertar miembro', 16, 1)
			rollback
			return 1
		end
		insert into juvenile values(  @member_no, @adult_member_no, @birth_date)
		if @@error<>0
		begin
			raiserror('error al insertar joven', 16, 1)
			rollback
			return 1
		end
	COMMIT TRAN
	return 0
end
go

--si es que ya está registrado el libro
--use LIBRARYY
CREATE PROC añade_copias
	@title_no title_no, @isbn smallint, @title title, @author char(20), @synopsis text,
	@idioma varchar(20), @cover VARCHAR(20), @num_copias int
as
begin

	declare @copias_actual int
	set @copias_actual = (select max(copy_no) from copy where isbn = @isbn)
	--set @synopsis = (select synopsis from title where title_no = @title_no)
	BEGIN TRAN

		/*insert into title values(@title_no, @title, @author, @synopsis)
		if @@error <>0
		begin
			raiserror('error al borrar', 16, 1)
			return 1
		end
		insert into item values (@isbn, @title_no, @idioma, @cover, 'Sí')
		if @@error <>0
		begin
			raiserror('error al borrar', 16, 1)
			return 1
		end*/
		declare @suma_copias int
		set  @suma_copias = (@num_copias + @copias_actual)
		while (@copias_actual < @suma_copias)
		begin
			set @copias_actual = @copias_actual+1
			insert into copy values (@isbn, @copias_actual, @title_no, 'No')
			if @@error <>0
			begin
				raiserror('error al borrar', 16, 1)
				return 1
			end
		end							--5 + 5 = 10     5<10  -6<10  7<10	8<10	9<10	10<10
												
	COMMIT TRAN
	return 0;

end
go
--use LIBRARYY
CREATE PROCEDURE rellena_campos
@isbn isbn
as
begin
	if not exists (select isbn from item  where isbn = @isbn)
	begin
		raiserror('Articulo con este ISBN no existe',16,1)
		return 1
	end

	select  title, author, idioma, cover, synopsis from title t, item i where t.title_no = i.title_no and isbn = @isbn
	--select title_no from title where title_no in (select title_no from item where isbn = @isbn)
	return 0
end
go
CREATE PROCEDURE rellena_campo_isbn
@isbn isbn
as
begin
	select title_no from title where title_no in (select title_no from item where isbn = @isbn)
	return 0
end
go

--por si no está registrado tal libro
--use LIBRARYY

CREATE PROC alta_libros
@title_no title_no, @isbn smallint, @title title, @author char(20), @synopsis text,
@idioma varchar(20), @cover VARCHAR(20), @num_copias int-- @loanable char(2)
as
begin

if( @title_no like '' or @isbn like '' or @title like '' or @author like '' or @synopsis like '' or 
	@idioma like '' or @cover like '' or @num_copias like '' )
		begin 
		raiserror('Llena los campos correctamente, operación sin completar', 16, 1)
			return 1;
	end
	declare @copias_actual int
	set @copias_actual = 0
	if exists (select * from item where isbn = @isbn)
	begin 
		if exists(select title_no from title where title_no = @title_no)
		begin
			exec añade_copias @title_no, @isbn, @title, @author, @synopsis, @idioma, @cover, @num_copias 
			return 0;
		end
		else	--set @copias_actual = (select max(copy_no) from copy where isb = @isbn)
		begin
			raiserror('ISBN existente pero la clave del título no, no se pueden añadir copias', 16, 1)
			return 1;
		end
	end
	if exists (select * from title where title_no = @title_no)
	begin 
		raiserror('Clave de título existente, operación sin completar', 16, 1)
			return 1;
	end

	BEGIN TRAN
	begin
		insert into claves_tit values(@title_no)
		if @@error <>0
		begin
			raiserror('error al borrar', 16, 1)
			return 1
		end
		insert into title values(@title_no, @title, @author, @synopsis)
		if @@error <>0
		begin
			raiserror('error al borrar', 16, 1)
			return 1
		end
		insert into item values (@isbn, @title_no, @idioma, @cover, 'Sí')
		if @@error <>0
		begin
			raiserror('error al borrar', 16, 1)
			return 1
		end
		declare @suma_copias int
		set  @suma_copias = (@num_copias + @copias_actual)
		while (@copias_actual < @suma_copias) --0 5 = 10   5<10 6<10 7<10 8<10 9<10 10<10
		begin
			set @copias_actual = @copias_actual+1
			insert into copy values (@isbn, @copias_actual, @title_no, 'No')
			if @@error <>0
			begin
				raiserror('error al borrar', 16, 1)
				return 1
			end
		end
	end
	COMMIT TRAN
	return 0;
end
go

CREATE PROCEDURE alta_prestamo
@isbn int, @Copy_no int, @title_no int, @member_no int,  
@due_date datetime 
as
	declare @out_date datetime = getdate();
	
	if(@isbn like '' or @Copy_no like '' or @title_no like '' or @member_no like  '' or @out_date like  '' or @due_date like '')
	Begin 
		Raiserror('Llena campos correctamente',16,11)
		return 1
	end
	if not exists (select title_no from title where title_no = @title_no)
	Begin 
		Raiserror('El título especificado no existe',16,11)
		return 1
	end
	if not exists (select * from member where member_no = @member_no)
	Begin 
		Raiserror('El usuario no existe',16,11)
		return 1
	end
	if(select count(*) from loan
		   where member_no = @member_no) = 3
	Begin
		Raiserror('El usuario llegó al límite de prestamos',16,11)
		return 1
	end
	if not exists (select * from copy where isbn = @isbn and copy_no= @copy_no)
		begin
			Raiserror('El isbn y/o copia del libro especificado no existe en la librería.', 16, 11)
			return 1
		end
	if(@due_date < getdate())
		begin
			Raiserror('La fecha de vencimiento no puede ser menor que la fecha actual.', 16, 11)
			return 1
		end
	if(select on_loan from copy
			where isbn = @isbn and copy_no = @copy_no) = 'Sí'
		begin
			Raiserror('El libro ya se encuentra prestado.', 16, 11)
			return 1
		end
	------------------------------------------------------------------------------
	if(select loanable from item where isbn = @isbn) = 'No'
	Begin
		Raiserror('el libro no se presta',16,11)
		return 1
	end
	------------------------------------------------------------------------------------------
	if exists(select * from adult where adult_member_no = @member_no)
	Begin
		if  (select expr_date from adult where adult_member_no = @member_no)<getdate()
		begin
			Raiserror('Credencial vencida',16,11)
			return 1
		end
	end
	else 
	Begin	
		if (select expr_date from adult a, juvenile j
			where j.juvenile_member_no = @member_no and j.juvenile_adult_member_no = a.adult_member_no)<getdate()
			Begin
				Raiserror('Credencial vencida',16,11)
				return 1
			end
	end

	Begin Transaction

		insert into loan values(@isbn, @Copy_no, @title_no, @member_no, @out_date, @due_date)
		if @@ERROR <>0
		Begin
			Raiserror('Error al insertar',16,11)
			rollback tran
			return 1
		end
		Update copy	set on_loan = 'Sí' where isbn = @isbn and copy_no = @Copy_no
		if @@ERROR <>0
		Begin
			Raiserror('Error al actualizar',16,11)
			rollback tran 
			return 1
		end
		delete from reservation where member_no = @member_no and @isbn = isbn
			if @@ERROR <>0
		Begin
			Raiserror('Error al eliminar reservación',16,11)
			rollback tran 
			return 1
		end
	commit Transaction
return 0
go
CREATE PROCEDURE alta_reservacion
@isbn int, @member_no member_no, @remarks VARCHAR(100)
AS
	declare @log_date datetime = getdate()

	if not exists (select * from member
			where member_no = @member_no)
		begin
			raiserror('El miembro no existe', 16, 11)
			return 1
		end
	if exists (select * from loan where @isbn = isbn and @member_no = member_no)
	begin
			raiserror('El miembro tiene a su cargo este libro', 16, 11)
			return 1
	end
		if exists(select * from reservation where isbn = @isbn and member_no= @member_no)
		begin
			raiserror('La reservación que trata de dar de alta ya existe', 16, 11)
			return 1
		end
		--select * from juvenile
		--select * from adult
	declare @expira datetime = (select A.expr_date from adult A, juvenile where juvenile_member_no = @member_no 
	and juvenile_adult_member_no = adult_member_no)
	if @expira < getdate()
	begin
		raiserror('La credencial del usuario tutor ya expiró', 16, 11)	
		return 1
	end
	if(select expr_date from adult
	where adult_member_no = @member_no )<getdate()
	begin
		raiserror('La credencial del usuario tutor ya expiró', 16, 11)
		return 1
	end
	if(select sum(fine_assessed) from loanhist	where member_no = @member_no) < 0
		begin
			raiserror('El usuario presenta adeudos', 16, 1)
			return 1
		end
	if(select loanable from item where isbn = @isbn) = 'No'
		begin
			raiserror('El libro especificado no se puede prestar', 16, 11)
			return 1
		end
	
	if(@log_date < getdate())
		begin
			raiserror('La fecha de la reservación no puede ser menor que la fecha actual', 16, 11)
			return 1
		end
	begin tran
		insert into reservation values(@isbn, @member_no, @log_date, @remarks)
		if(@@ERROR <> 0)
		begin
			raiserror('Error al dar de alta reservacion en tabla reservation.', 16, 11)
			return 1
		end
	commit tran
return 0;
go

create proc consulta_miembros_cve
@member_no int
as
begin
	if(@member_no like '')
	begin 
		raiserror('Llena el campo correctamente',16,1)
		return 1
	end
	if not exists(select * from member where member_no = @member_no)
	begin 
		raiserror('miembro no existe',16,1)
		return 1
	end
	select * from member where member_no = @member_no
end
go
----------------------------------------------------------------------------------------------------------
CREATE PROCEDURE consulta_miembros
as
begin
	select * from member
end
go
----------------------------------------------------------------------------------------------------------
CREATE PROCEDURE consulta_miembros_adultos
@member_no int
as
begin
	if(@member_no like '')
	begin 
		raiserror('Llena el campo correctamente',16,1)
		return 1
	end
	if not exists(select * from member where member_no = @member_no)
	begin 
		raiserror('miembro no existe',16,1)
		return 1
	end
	if not exists(select * from adult where adult_member_no = @member_no)
	begin 
		raiserror('Este miembro existe pero no es adulto',16,1)
		return 1
	end
	select member_no, nombre, lastname, street,zipcode,city,state,phone_no,expr_date
	from adult, member
	where adult_member_no = @member_no and member_no = @member_no
end
go
----------------------------------------------------------------------------------------------------------
CREATE PROCEDURE consulta_miembros_adultos_todos
as
	select member_no, nombre, lastname, street,zipcode,city,state,phone_no,expr_date
	from adult, member where adult_member_no = member_no
----------------------------------------------------------------------------------------------------------
go
----------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------
CREATE PROCEDURE consulta_miembros_jovenes
@member_no int
as
begin
	if(@member_no like '')
	begin 
		raiserror('Llena el campo correctamente',16,1)
		return 1
	end
	if not exists(select * from member where member_no = @member_no)
	begin 
		raiserror('Miembro no existe',16,1)
		return 1
	end
	if not exists(select * from juvenile where juvenile_member_no = @member_no)
	begin 
		raiserror('Este miembro existe pero no es joven',16,1)
		return 1
	end
	select member_no, nombre, lastname, juvenile_adult_member_no, birthdate
	from juvenile, member
	where juvenile_member_no = @member_no and member_no = @member_no

end
go
----------------------------------------------------------------------------------------------------------------
CREATE PROCEDURE consulta_miembros_joven_todos
as
	select member_no, nombre, lastname, juvenile_adult_member_no, birthdate
	from juvenile, member
	where juvenile_member_no = member_no
go

create procedure elimina_member_juvenile
@member_no int
as
begin
	/*if not exists (select * from member where member_no = @member_no)
	begin
		raiserror('Miembro no existe', 16, 1)
		return 1
	end
	if not exists (select * from juvenile where juvenile_member_no = @member_no)
	begin
		raiserror('Miembro existe pero no es un joven', 16, 1)
		return 1
	end
	if not exists (select * from adult where juvenile_member_no = @member_no
					and adult_member_no = juvenile_adult_member_no )
	begin
		raiserror('Miembro tutor no existe', 16, 1)
		return 1
	end*/

	if exists (select * from loan where member_no = @member_no)
	begin
		raiserror('Miembro tiene préstamos', 16, 1)
		return 1
	end
	if exists (select * from loanhist where member_no = @member_no and fine_assessed>fine_paid)
	begin
		raiserror('Miembro con adeudo', 16, 1)
		return 1
	end
	
	BEGIN TRAN

		delete reservation where member_no = @member_no
		if @@error <>0
		begin
			raiserror('error al borrar', 16, 1)
			return 1
		end
		delete juvenile where juvenile_member_no = @member_no
		if @@error <>0
		begin
			raiserror('error al borrar', 16, 1)
			return 1
		end
		delete member where member_no = @member_no
		if @@error <>0
		begin
			raiserror('error al borrar', 16, 1)
			return 1
		end
	COMMIT TRAN
	return 0
end
go

CREATE PROCEDURE baja_libro
@title_no title_no
AS

if(@title_no like '')
	Begin 
		Raiserror('Llena campos correctamente',16,1)
		return 1
	end
	if not exists (select * from title where title_no = @title_no)
		begin
			raiserror('Éste libro que trata de dar de baja no existe', 16, 1)
			return 1
		end
	if exists(select * from loan where title_no = @title_no)
		begin
			raiserror('Éste libro especificado tiene copias prestadas', 16, 1)
			return 1
		end

	BEGIN TRAN
		delete from copy where title_no = @title_no
		if(@@ERROR <> 0)
			begin
				raiserror('Error al dar de baja el libro de la tabla copy', 16, 1)
				rollback tran
				return 1
			end

		delete from item where title_no = @title_no
		if(@@ERROR <> 0)
			begin
				raiserror('Error al dar de baja el libro de la tabla item', 16, 1)
				rollback tran
				return 1
			end

		delete from title where title_no = @title_no
		if(@@ERROR <> 0)
			begin
				raiserror('Error al dar de baja el libro de la tabla title', 16, 1)
				rollback tran
				return 1
			end
	COMMIT TRAN
return 0
go

CREATE PROCEDURE regresar_libro
@isbn isbn, @copy_no int, @fine_paid float, @remarks VARCHAR(100)
AS
declare @out_date datetime, @title_no title_no, @member_no member_no, @due_date datetime, @in_date datetime = getdate()

	select @out_date = out_date, @title_no=title_no, @member_no=member_no, @due_date = due_date from loan
	where isbn = @isbn and copy_no = @copy_no

if(@isbn like '' or @Copy_no like '' or @fine_paid like '') --poner 0 por defecto
	Begin 
		Raiserror('Llena campos correctamente',16,11)
		return 1
	end
	if not exists(select * from loan where isbn = @isbn and copy_no = @copy_no)
		begin
			raiserror('ISBN y/o Cve copia no coinciden con los préstamos registrados-', 16, 11)
			return 1
		end
	 
	if((select due_date from loan where isbn = @isbn and copy_no = @copy_no) < getdate())
		begin
			declare @fine_assessed float
			set @fine_assessed = abs(5 * datediff(dd, getdate(), (select due_date from loan
											where isbn = @isbn and copy_no = @copy_no)))
		end

--	if not exists(select * from copy where isbn = @isbn and copy_no = @copy_no)
	--	begin
		--	raiserror('El libro especificado no existe', 16, 1)
		--	return 1
		--yend

	
	BEGIN TRAN
		insert into loanhist values(@isbn, @copy_no,@title_no, @member_no,
									@out_date, @due_date, @in_date, 
									@fine_assessed, @fine_paid, @remarks		)
		if(@@error <> 0)
			begin
				raiserror('Error al registrar el préstamo en loanhist', 16, 1)
				rollback tran
				return 1
			end

		delete from loan where isbn = @isbn and copy_no = @copy_no
		if(@@ERROR <> 0)
		begin
			raiserror('Error al borrar el préstamo de la tabla loan', 16, 1)
			rollback tran
			return 1
		end

		update copy set on_loan = 'No'
		where isbn = @isbn and copy_no = @copy_no
		if(@@ERROR <> 0)
		begin
			raiserror('Error al actualizar la tabla copy', 16, 1)
			rollback tran
			return 1
		end
	COMMIT TRAN
return 0
go
CREATE PROC regresa_multa
@due_date datetime
as
--declare @due_date datetime = '11/05/2019'
	if @due_date < getdate()
	begin
		select abs(5 * datediff(dd, getdate(),(select @due_date)))
	end
	else 
	begin
		select 0
	end
go
CREATE PROC existencia_isbn_copy
@isbn isbn, @copy_no int
as
	if not exists (select isbn, copy_no from loan where @isbn = isbn and @copy_no = copy_no)
	begin
		raiserror('Prestamos no existe',16,1)
		return 1
	end
go

CREATE PROCEDURE adeudo @member_no member_no, @pago int
as
	if not exists (select member_no from member where member_no = @member_no)
	begin
		raiserror('Este miembro no existe',16,11)
		return 1
	end
	--declare @member_no int = 1, @pago int = 2
	declare @deudaasses int = (select sum(fine_assessed) from loanhist where member_no = @member_no)
	declare @deudapain  int = (select sum(fine_paid) from loanhist where member_no = @member_no)

	if (@deudaasses < @deudapain )--or (Select abs((sum(fine_assessed)- sum(fine_paid))) from loanhist) = 0)
	begin
		raiserror('Miembro sin deudas',16,11)
		return 1	
	end
	if(@deudaasses < (@deudapain+@pago))
	begin
		raiserror('Cobro supera el adeudo',16,11)
		return 1	
	end

	--declare @pago int = 1, @member_no int = 1---------------------------------------------------------
	declare @recupera int = (select max(fine_paid) from loanhist where member_no = @member_no)
	set @pago = @pago + @recupera
	--delete from loanhist where member_no = 1
	BEGIN TRAN
		update loanhist set fine_paid = @pago where member_no = @member_no 
		and fine_paid in (select top 1 fine_paid from loanhist where member_no = @member_no order by out_date desc)

		if(@@error <> 0)
			begin
				raiserror('Error al registrar la deuda o en la BD', 16, 1)
				rollback tran
				return 1
			end
	COMMIT TRAN
return 0
go

CREATE PROC con_memb_deu @member_no member_no
as
	
	if not exists (select member_no from member where member_no = @member_no)
	begin
		raiserror('Este miembro no existe',16,1)
		return 1
	end
	if not exists (select member_no from loanhist where member_no = @member_no)--or (Select abs((sum(fine_assessed)- sum(fine_paid))) from loanhist) = 0)
	begin
		raiserror('Miembro sin deudas',16,11)
		return 1	
	end
	declare @deudaass int = (select sum(fine_assessed) from loanhist where member_no = @member_no)
	declare @deudapaid  int = (select sum(fine_paid) from loanhist where member_no = @member_no)

	if ((@deudaass < @deudapaid) or (Select abs(sum(fine_assessed)- sum(fine_paid)) from loanhist where member_no = @member_no) = 0)
	begin
		raiserror('Miembro sin deudas',16,11)
		return 1	
	end

	Select member_no,abs((sum(fine_assessed)- sum(fine_paid))) from loanhist where member_no = @member_no Group by member_no
	return 0
go
/*
--in(select max(member_no) from loanhist)
select * from member
select sum(fine_assessed) as debido, sum(fine_paid) as pagado from loanhist where member_no = 1

select fine_assessed as debido, fine_paid as pagado from loanhist where member_no = 1
	exec adeudo 1, 80
	select * from copy
	select * from loanhist where member_no = 1
	delete from loanhist where member_no = 5
--------------------------PRUEBAS-----------------------------
select * from member
delete from member where member_no = 8
select * from loanhist
	update loanhist set member_no = 5 where fine_assessed = 500
	update loanhist set fine_paid = 4 where fine_paid = 37 	
update loanhist set fine_assessed = 12 where copy_no = 4 and fine_assessed is null
update loanhist set fine_assessed = 50 where member_no = 6
insert into loan values(1,2,2,1,'29/04/2019', '11/05/2019')
delete from loanhist
select * from copy
select * from loanhist order by in_date
exec alta_prestamo	2,2,2,6,'12/05/2019 12:05:59'--LOAN		-- @isbn , @Copy_no , @title_no , @member_no , 
exec alta_prestamo	1,3,3,1,'12/05/2019 12:05:59'
exec alta_prestamo	1,4,3,1,'12/05/2019 12:05:59'
exec alta_prestamo	1,5,2,1,'10/05/2019 12:05:59'
exec regresar_libro 2 , 2, 6 , 'sin nada que decir'--LOANHIST --@isbn , @copy_no , @fine_paid , @remarks 
exec regresar_libro 1 , 3, 3 , 'sin nada que decir'
exec regresar_libro 1 , 4, null , 'sin nada que decir'
exec regresar_libro 1 , 2, null , 'sin nada que decir'
declare @isbntitle int = 3														--COPIAS
insert into title values(@isbntitle, 'titulo', 'autor', 'synopsis')
insert into item values(@isbntitle, @isbntitle, 'español', 'Gruesa', 'Sí')
insert into COPY values(@isbntitle, 2, @isbntitle, 'No')
insert into COPY values(@isbntitle, 3, @isbntitle, 'No')
insert into COPY values(@isbntitle, 4, @isbntitle, 'No')
insert into COPY values(@isbntitle, 5, @isbntitle, 'No')
insert into COPY values(@isbntitle, 6, @isbntitle, 'No')
insert into COPY values(@isbntitle, 7, @isbntitle, 'No')

update loanhist set remarks = 'algo que decir' where member_no = 1 
								and in_date in (select max(in_date) from loanhist )
								*/
/*
	--isbn int not null references item (isbn),
	--copy_no int not null references copy(copy_no),
	--out_date datetime,
	--title_no int not null references title(title_no),
	--member_no int not null references member(member_no),
	--due_date datetime,
	--in_date datetime,
	--fine_assessed float,
	--fine_paid float,
	--remarks text

use LIBRARYY

use LIBRARYY
drop table loanhist
select * from title
select * from COPY
select * from item
select  isbn, title, author, idioma, cover from title t, item i where t.title_no = i.title_no and isbn = 4
	select title_no, author,title from title where title_no in (select title_no from item where isbn = 4)
	select title_no from item where isbn = 1 

select * from member
select top 1 nombre from clientes where cve_cte in (select cve_cte from comen) order by nombre asc
select * from adult
select * from juvenile
select * from loanhist order by in_date desc
select * from loan
delete from juvenile
delete from adult
delete from member
delete from claves

delete from loanhist
delete from loan
delete from copy
delete from item
delete from title

select max(nombre), count(*) from member m, juvenile j where j.juvenile_adult_member_no = m.member_no
group by  juvenile_adult_member_no
use LIBRARYY
insert into member values(1, 'alexia', 'dayane')
insert into member values(8, 'chikilin', 'dayane')
insert into adult values(1, 'calle', '12345', 'city', 'sinaloa', 1234, '20-12-2020') 
insert into juvenile values (8, 1, '11-11-2011')	


declare @adeudo int = 26, @adeutototal int = (select sum(fine_assessed) from loanhist where member_no = 1)

	update loanhist set fine_assessed = @adeutototal where member_no = 1 
	update loanhist set fine_paid = @adeudo where member_no = 1 

	select * from member
select * from title
select * from item
select * from copy

insert into reservation values(2,5,getdate(), 'sin comentarios')
select * from reservation
delete from reservation
insert into reservation values (1, 1, GETDATE(), '')
insert into reservation values (2, 5, GETDATE(), '')
insert into reservation values (3, 1, GETDATE(), '')
insert into item values(3,2,'esp','hard','Sí')
select * from item

delete from loan
drop table loan
select * from loan
select * from loanhist
delete from loanhist where fine_assessed is null
SELECT FORMAT(getdate(),'dd/mm/yyy') as date
Select FORMAT(due_date,'dd/MM/yyyy') from loan where 2 = isbn and copy_no = 2
drop table loan

select * from loan
update loanhist set fine_assessed = 32 where copy_no = 4
update loanhist set fine_assessed = 32 where copy_no = 3
insert into loan values(1,2,2,1,'29/04/2019', '11/05/2019')
delete from loanhist
select * from copy
exec alta_prestamo	1,3,18,1,'11/05/2019 12:05:59'		-- @isbn , @Copy_no , @title_no , @member_no , 
exec regresar_libro 1 , 2, 5 , 'sin nada que decir' --@isbn , @copy_no , @fine_paid , @remarks 
exec regresar_libro 1 , 3, 3 , 'sin nada que decir'
exec regresar_libro 1 , 4, null , 'sin nada que decir'
exec regresar_libro 1 , 2, null , 'sin nada que decir'
select fine_assessed from loanhist where member_no = 1
----------------------------------------------------------------------------------------------------------------------------------------------------------------
drop table pagos
drop table deudas

create table deudas(
	cve_deuda int not null identity(1,1) primary key,
	member_no member_no not null references member(member_no),
	fecha datetime,
	deuda int,
	deudamain int
)
create table pagos(
	cve_pago int not null identity(1,1) references deudas(cve_deuda) primary key,
	member_no member_no not null references member(member_no),
	fecha datetime,
	pago int
)
use LIBRARYY
insert into title values(3, 'titulo', 'autor', 'synopsis')
insert into item values(2, 3, 'español', 'Gruesa', 'Sí')
insert into COPY values(2, 2, 3, 'No')
insert into COPY values(2, 3, 3, 'No')
insert into COPY values(2, 4, 3, 'No')
insert into COPY values(2, 5, 3, 'No')
insert into COPY values(1, 6, 3, 'No')
insert into COPY values(1, 7, 33, 'No')
select isbn, i.title_no, title, idioma, cover, loanable, author from item i, title t where i.title_no = t.title_no
select * from item 
select * from title
select * from copy
select i.isbn, i.title_no, title, idioma, cover, count(loanable) as disponibles, count(c.title_no) , author
 from item i, title t, copy c
 where i.title_no = t.title_no and c.title_no = t.title_no and i.loanable = 'Sí' and c.on_loan = 'Sí'
 group by t.title_no, i.isbn,  i.title_no, title, idioma, cover, author*/


 
/*drop proc title_con

drop proc title_con
CREATE PROC title_con
@title_no title_no
as
begin
	Select * from title where title_no = @title_no
	return 0
end
go

drop proc isbn_con
CREATE PROC isbn_con
@isbn isbn
as
begin
	Select * from item where isbn = @isbn
	return 0
end

drop proc isbn_con
go*/

delete from copy
delete from item
delete from title
delete from reservation
delete from loan
delete from loanhist
delete from juvenile
delete from adult
delete from member
delete from claves
delete from claves_tit
select * from member
select * from adult

insert into claves values(1)
insert into claves_tit values(1)
insert into claves values(2)
insert into claves_tit values(2)

insert into member values (1,'Rey', 'Sosa')
insert into adult values( 1, 'Obregón', '12345', 'Culiacán','Sinaloa', '6677889900', '11/11/2011 00:00:00.000')
--member_no nombre apellido calle zip ciudad estado telefono fecha
insert into member values (2,'Luis', 'López')
insert into adult values(2, 'Malecón', '99998', 'Mazatlán','Sinaloa', '6677889911', '11-11-20')

insert into title values(1, 'Estrellita', 'Yo', 'Una estrellita de mar y del espacio')
insert into item values (1, 1, 'Español', 'Gruesa', 'Sí')

insert into COPY values(1, 1, 1, 'No')
insert into loan values (2,1, 2, 1, '11/04/19', '04/05/19') --fecha de salida 5ta
insert into loanhist values (1,1, 1, 1, '11/04/19', '30/05/19', '05/05/19', 35, 12, 'Pagó $5')

insert into loan values (2,1, 2, 1, '11/04/19 12:05', '04/05/19') --fecha de salida 5ta
insert into loanhist values (1,1, 1, 1, '11/04/19', '30/05/19', '05/05/19 12:00:01', 65, 13, 'Pagó $5')

select * from loanhist