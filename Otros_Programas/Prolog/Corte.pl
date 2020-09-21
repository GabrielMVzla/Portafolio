

%Prueba de else if

prueba(R, A, B, C):-
(
	R =:= 1 ->
		A is 1,
		B is 2
	;
		A is 2,
		B is 1
),
C is 3.



%Devuelve la primera posición del número buscado

p(_,[],0).

p(D,[D|_],P):- P is 1,!.

p(D,[_|R],P):-
p(D,R,P1),
P is P1+1.

%Devuelve todas las posiciones del número buscado
principalMain(D,X,P):-
	C is 0,
	main(D, X, P, C).
	
main(D,[X|[]],[P],C):-
Contador is C + 1,
(
D =:= X ->
	P is Contador;
	P is 0
).
main(D,[X|Y],[R2|P2],C):-
	Contador is C + 1,

	(D =:= X ->
	R2 is Contador;
	R2 is 0),
	main(D, Y, P2, Contador).
	
%insertar(E,[X|Y],[X|R], C):-insertar(E,Y,R, C).
	

%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%insertar(E,[],[E|[]], C).
%insertar(E,[X|Y],[X|R]):-insertar(E,Y,R).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%

principal(Resp):-
C is 0,
contador(C, R),
Resp is R.

contador(C, R):-
RT is C,
RT >= 1 -> 
	R is C
;
	conta(C, R1),
	RC is R1,
	contador(RC,R2),
	R is R2
.

conta(C,R):- 
R is C + 1.




% Hacer un predicado que reciba dos listas (veamolas como conjuntos)
% y retorne la interseccion de ambas
u(_,[],[]).
u(X,[Y|Z], R):-
X =:= Y -> R is X;
u(X,Z,R).

uMain([],L,[]).
uMain([X|Y],L,[R1|R]):-
u(X,L,R1),
uMain(Y,L,R).

principalUnion(Y,L,R):-
uMain(Y,L,R1),
quitaVacio(R1, R2),
ultimo(R2,R).

ultimo([X,[]],[X]).
ultimo([X|R], [X|R]).

quitaVacio([[],Y|R],L):-
	quitaVacio([Y|R],L).

quitaVacio([X|Y], [X|L]):-
quitaVacio(Y,L).
	
quitaVacio([],[]).
%agg(D,[X|R],P, C):-
%	cont(C, R1),
%	Contador is R1,
%	encuentra(D,X,Resp,Contador),
%	RA is Resp,
%%%	agg(D,R,[_,RA|[]], Contador).  hacer instrucción de si respuesta es = 0 que no se añada
%	agg(D,R,[P|RA], Contador).

%agg(D,[],[D|[]], C).


longitud([],0).
longitud([_|[]],1).
longitud([_|R],L):- longitud(R,L2), L is L2 + 1.

mainsepara(Valor, R):-  
	separa(Valor, R1), 
	longitud(R1,Long),
	(Long =:= 5 ->
	R2 = ['0'|R1] ,
	concatenar(R2,R);
	concatenar(R1,R))
	.
concatenar([A], A) :- !.
concatenar([A | R], F) :- concatenar(R, B), string_concat(A, B, F).

separa(Valor, R):- string_chars(Valor, R).




%longitud([],0).
%longitud([_|[]],1).
%longitud([_|R],L):- longitud(R,L2), L is L2 + 1.

%tres0(R,['0','0','0'|R]).
%dos0(R,['0','0'|R]).

%mainsepara(Valor, R):-  separa(Valor, R1), longitud(R1,Long),
%	(
%		Long =:= 3 -> tres0(R1, R2)
%	;
%		Long =:= 4 -> dos0(R1, R2)
%	;
%		R2 = ['0'|R1] ),
%		concatenar(R2,R)
%	.
%concatenar([A], A) :- !.
%concatenar([A | R], Curp) :- concatenar(R, B), string_concat(A, B, Curp).


	
