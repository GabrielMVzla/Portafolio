%compara([X|[]],X).
%compara([X,Y|R], A):-
%Anterior is X,
%Actual is Y,
%compara(R, Z),
%DifAnt = Dif,
%Anterior =\= 0 -> Dif is Anterior - Actual; Dif is Actual - Y,
%Dif =:= DifAnt -> A is Dif; A = 0.


%Diferencia con el siguiente

diferencia([X|[]]):- true.
diferencia([X,Y|R]):- ( X =:= Y ) -> false; diferencia([X|R]).

manda([]):- true.
manda([X|[]]):- true.

manda([X|R]):-
(false =:= diferencia([X|R])) -> false;
manda(R).

%diferencia([X,Y|_]):- ( X =\= Y ) -> true ; false.

%Diferencia con el siguiente
%diferencia([]):- true.
%diferencia([X|[]]):- true.

%diferencia([X,Y|R]):- ( X =:= Y ) -> false; diferencia([Y|R]).

diferencia1([X|[]], 1).
diferencia1([X,Y|R], Z):- ( X =:= Y ) -> Z is 0; diferencia1([X|R], Z1), Z is Z1.

manda1([],1).
manda1([X|[]],1).

manda1([X|R], Res):-
diferencia1([X|R], Z), 
Z =:= 0 -> Res is 0;
manda1(R,Z1),
Res is Z1.

repetir(X):-
manda1(X, R),
(R =:= 1) -> true;%write("No se repiten"); write("Se repiten").
false .