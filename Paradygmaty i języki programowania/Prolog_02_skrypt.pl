


% zad 1
max2(X,Y,Z) :- X>=Y, Z=X. 
max2(X,Y,Z) :- X=<Y, Z=Y.

maxx(X,Y,Z) :- X>=Y -> Z=X; Z=Y.

% zad 2
isList([]).
isList([_|Y]) :- isList(Y).  


% zad 3
isMember(X, [X|_]).
isMember(X, [_|T]) :- isMember(X,T).

% zad 4
myLast(X, [X]).
myLast(X, [_|T]) :- myLast(X, T).

% zad 5
multiply([X], [Y], [Z]) :- Z is X*Y.
multiply([X|Xs], [Y|Ys], L) :- 
	Z is X*Y, multiply(Xs, Ys, Ls), L=[Z|Ls].

% zad 6
razy(A, [X], [Z]) :- Z is A*X.
razy(A, [X|T], L) :- Z is A*X, L=[Z|Ls], razy(A, T, Ls).


% zad 7
suma([],0).
suma([X|Xs],S) :- suma(Xs,S1), S is X+S1. 

list_length([], 0).
list_length([_|Xs], L) :- list_length(Xs,N), L is N+1 .



% inne 
is_on_list(X, [X|Y]).

is_on_list2(X, [X|Y]).
is_on_list2(X, [Z|Y]) :- is_on_list2(X, Y).

join_lists(X, Y, Z) :- append(X, Y, Z) .
join_lists2([X|Y],List2,[X|Result]):- append(Y,List2,Result).

is_member2(X, [Y|T]) :- X = Y; member(X, T).