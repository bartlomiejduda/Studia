

firma(abc).
firma(klm).
firma(mno).

kobieta(anna).
kobieta(maria).
kobieta(julia).
kobieta(ewa).
kobieta(joanna).
kobieta(lena).
kobieta(teresa).


mezczyzna(jan).
mezczyzna(karol).
mezczyzna(piotr).
mezczyzna(tomasz).
mezczyzna(lukasz).
mezczyzna(marek).
mezczyzna(jozef).

% pracownik(imie, nazwisko, firma, staz_pracy).

pracownik(anna, klimczak, firma(abc), 10).
pracownik(anna, maj, firma(abc), 1).
pracownik(maria, jankowska, firma(abc), 12).
pracownik(julia, klimczak,  firma(abc), 4).
pracownik(jan, kowal, firma(abc), 21).
pracownik(karol, lis, firma(abc), 5).
pracownik(anna, lis, firma(klm), 12).
pracownik(piotr, bednarek, firma(klm), 8).
pracownik(tomasz, bednarek, firma(klm), 2).
pracownik(ewa, wilk, firma(klm), 3).
pracownik(ewa, lipiec,firma(klm), 7).
pracownik(lukasz, polak,  firma(klm), 11).
pracownik(marek, doba, firma(klm), 8).
pracownik(anna, just, firma(mno), 22).
pracownik(joanna, wilk, firma(mno), 16).
pracownik(piotr, czekaj, firma(mno), 4).
pracownik(maria, wilczak, firma(mno), 16).
pracownik(piotr, kawa, firma(mno), 14).
pracownik(marek, czubak, firma(mno), 5).
pracownik(marek, lis, firma(mno), 4).



% zad 1
% kobieta(lidia).
% pracownik(jan, _, _, _).
% pracownik(jozef, _, _, _).
% pracownik(_, karolak, _, _).
% pracownik(_, maj, firma(abc), _).


% zad 2
% a) 
% pracownik(anna, X, _, _).
% b) 
% pracownik(X, lis, Y, _).
% c) 
% pracownik(_, lis, X, _)
% d) 
% pracownik(X, Y, firma(klm), _).
% e) 
% pracownik(X, Y, _, _), mezczyzna(X). -- to jest dobrze
% pracownik(X, Y, _, _); mezczyzna(X).



% zad 3a
pracownik_abc(X, Y) :- pracownik(X, Y, firma(abc), _). 

% 3b
% pracInne(X, Y) :- pracownik(X, Y, \+ firma(abc), _). 
pracInne(X, Y) :- pracownik(X, Y, FIRMA, _), FIRMA \= firma(abc).

% 3c
pracKobieta(X,Y) :- pracownik(X, Y, _, _), kobieta(X).


% 3e 
plus(X, Y, Z) :- Z is X+Y.

% wyliczam bonus B
% premia(X, Y, B) :- pracownik(X, Y, _, STAZ), B is STAZ*150.

premia(X, Y, B) :- pracownik(X, Y, _, STAZ), STAZ>5 -> B is STAZ*150. 





