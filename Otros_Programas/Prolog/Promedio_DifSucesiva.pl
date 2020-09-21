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
diff([],0).