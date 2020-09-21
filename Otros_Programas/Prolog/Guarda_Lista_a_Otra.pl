%Hacer lista con elementos de una lista
main(D,X,P):-
	C is 0,
	insertar(D, X, P, C).

insertar(D,[X|Y],[X|R], C):-
cont(C,R1),
Contador is R1,
encuentra(D,X,P,Contador),
( P =:= 0 -> 
insertar([],[],R,Contador)
;
insertar(D, Y, R, Contador))
.

cont(C,R):- R is C + 1.

encuentra(D,X,P,C):-
	D =:= X ->
	P is C
	;
	P is 0.

insertar([],[],[], C).
insertar(D,[],[D|[]], C).

%Encuentra el máximo
maximo([M], M) :- !.
maximo([C | R], M) :- maximo(R, M), M >= C.
maximo([C | R], C) :- maximo(R, M), C > M.

%insertar(D,[X|Y],[_|R], C):-
%Contador is R1,
%encuentra(D,X,P1,Contador),
%( P1 =:= 0 -> 
%insertar(D,Y,0,Contador)
%;
%insertar(D, Y, R, Contador)).

%cont(C,R):- R is C + 1.

%encuentra(D,X,P,C):-
%	D =:= X ->
%	P is C
%	;
%	P is 0.

%insertar([],[],[], C).
%insertar(P,[],[P|[]], C).