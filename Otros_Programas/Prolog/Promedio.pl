%por lo que entendemos, tenemos que generar un numero random, 
%y ese numero se va a multiplicar y asi el numero de veces que le mandes
g(0, [], _, _):-!.

g(N, [M | Y], M, R) :-  
	Num is N -1,
	g(Num, Y, (M * R), R).
	
mainGeometrica(N, L) :-
	random(1, 5, R),
	g(N, L2, R, R),
	operacion(L2, L).

operacion([X|Y],[Z|R]):-
	Z is X,
	operacion(Y,R).

operacion([], []).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

geometrica(0, [], C, _) :- 
	!,
	true.

geometrica(N, [X | R], C, X) :- 
	N2 is N - 1,
	Y is X * C, 
	geometrica(N2, R, C, Y).
geometrica(N, L) :- 
	random(2, 10, C),
	geometrica(N, L, C, C).
	
	
	
	
	
	
	
%calcular promedio

calcular(X, R):-
suma(X,Res),
conta(X,Res2),
R is Res / Res2.

suma([X|[]], X).
suma([X|R], Res):-
suma(R,Res2),
Res is X + Res2.

conta([X|[]],1).
conta([X|R], Res):-
conta(R, Res2),
Res is Res2 + 1.
%%%%%%%%%%%%%%%%

union([], Cs, Cs).
union([A|As], Bs, Cs):-
          member(A, Bs),
          !,
          union(As, Bs, Cs).

union([A|As], Bs, [A|Cs]):-
          union(As, Bs, Cs).

%%%%%%%%%%%%%%%%%%%%Diferencia sucesiva%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

comparaResto([X|[]], RM, RM). % comparaResto([X|[]], UR, RM):- UR is RM.

comparaResto([X,Y|Z], R, RM):-
	W is Y - X,
	(W =\= RM) -> 
		R is 0
	; 
		comparaResto([Y|Z], R, RM).

%comparaResto([Y|Z], R, RM),R = RM. %Así también funciona, aunque es irrelevante hacer más largo el código

mainDiff([X,Y], R):-
	(X =:= Y ) -> R is 0;
	R is Y - X.

diff([X,Y|Z],R):-
	mainDiff([X,Y], RM),
	comparaResto([Y|Z],RCR,RM),
	R is RCR.
	
diff([X|[]],0).