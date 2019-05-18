

% zad 1
% element_at(X,[X|Xs],1).
element_at(X,[X|_],1).
element_at(X,[_|Xs],K) :- K>1, K1 is K-1, element_at(X,Xs,K1).


% zad 2
dupli([],[]).
dupli([X|Xs], [X,X|Ys]) :- dupli(Xs,Ys).

% zad 3 
my_reverse(L1,L2) :- my_rev(L1,L2,[]).

my_rev([],L2,L2).  
my_rev([X|Xs], L2, Ac) :- my_rev(Xs,L2, [X|Ac]). 

% zad 4
end(X,L1,L2) :- append(L1,[X],L2).

start(X,L1,L2) :- L2=[X|L1].
start2(X,L1,[X|L1]).
end2(X,L1,L2) :- my_reverse(L1,Y), start(X,Y, W), my_reverse(W,L2).

% zad 5
remove_at(X,[X|Xs], 1, Xs).   
remove_at(X,[Y|Xs],K,[Y|Ys]) :- K>1, K1 is K-1, remove_at(X,Xs,K1,Ys).     

% zad 6 
take(_,0,[]).
take([X|Xs],K,L) :- K1 is K-1, take(Xs,K1,Ls), L=[X|Ls].
% take([X|Xs],K,[X|Ls]) :- K1 is K-1, take(Xs,K1,Ls).



