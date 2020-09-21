potencia(0,_,0).
potencia(X,0,1).


potencia(X,Y,Z) :- R1 is Y-1, potencia(X,R1,R2), Z is R2*X.

compara([X|[]],X).
compara([X|R], A):-
Anterior is Actual,
Actual is X,
compara(R, Y),
DifAnt = Dif,
(Anterior =/= 0 ->
Dif is Anterior - Actual;
Dif is Actual - Y),
(Dif =:= DifAnt -> 
A is Dif;
A = 0).

