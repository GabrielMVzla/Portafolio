sumax(P,[L|[]],[RC|[]]):- RC is L + P,!.
sumax(P,[LX|LR], [RC|RR]):-
	RC is LX + P,
	sumax(P,LR,RR).


%2
mayoresY(N, [L|[]], Z):-
	(L > N ->
	Z is 1,!;
	Z is 0,!).
	
mayoresY(N, [L|LR], Z):-
	(L > N ->
	mayoresY(N, LR, Z1),
	Z is Z1 + 1;
	mayoresY(N, LR, Z)).

elimina(X,[X|[]], []).
elimina(X,[Y|[]], [Y|[]]):- (X == Y) ->false.
elimina(X,[X|T],Z):- elimina(X,T,Z).
elimina(X,[Y|S],[Y|Z]):- elimina(X,S,Z).


pos_y_negPos([L|LR],Npos,[L|PP],Nneg,Neg):- 
		pos_y_neg(LR,Npos2,PP,Nneg,Neg),
		Npos is Npos2 + 1.
		
pos_y_negNeg([L|LR],Npos,Pos,Nneg,[L|NP]):- 
		pos_y_neg(LR,Npos,Pos,Nneg2,NP),
		Nneg is Nneg2 + 1.
		
pos_y_neg([L|LR],Npos,Pos,Nneg,Neg):-
	(L >= 0 ->
		pos_y_negPos([L|LR],Npos,Pos,Nneg,Neg)
		;
		pos_y_negNeg([L|LR],Npos,Pos,Nneg,Neg)
	).

pos_y_neg([L|[]],Npos,Pos,Nneg,Neg):-
	(L >= 0 ->
		pos_y_negPos1(L,Npos,Pos,Nneg,Neg)
		;
		pos_y_negNeg1(L,Npos,Pos,Nneg,Neg)
	).

pos_y_negPos1(L,1,[L|[]],0,[]).
pos_y_negNeg1(L,0,[],1,[L|[]]).