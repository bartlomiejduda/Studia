

/** PRZYKLAD 1 */
db1(1,a).
db1(2,b).
db1(3,c).
db1(4,d).


fun1(X) :-read(Y),db1(Y,X).


/** Wywolanie:
11 ?- fun1(X).
|: 3
|: .

X = c.
*/



% PRZYKLAD 2 

oblicz_kolo :- write('Podaj r: '),
               read(R),
			   W1 is 2 * pi * R,  
			   W2 is pi * R * R,
			   write('W1: '), write(W1), nl,
			   write('W2: '), write(W2), nl .
			   

/** Wywolanie:
oblicz_kolo .
*/


% PRZYKLAD 3 

przedstaw_sie :- write('Podaj imie: '),
                 read(Imie),
				 write('Podaj nazwisko: '),
				 read(Nazwisko),
				 write('Imie: '), write(Imie), nl,
				 write('Nazwisko: '), write(Nazwisko), nl .
				 
/** Wywolanie:
22 ?- przedstaw_sie .
Podaj imie: "Bartek" .
Podaj nazwisko: |: "Duda" .
Imie: Bartek
Nazwisko: Duda
true.
*/



% PRZYKLAD 4


litera :- write('Podaj litere: '),
                 get_char(Litera),
				 write('Podana litera: '), write(Litera), nl .


/** Wywolanie:
35 ?- litera .
Podaj litere: t
Podana litera: t
true.
*/



% PRZYKLAD 5

wstaw_litery(X, Y) :- write('Put: '), put(X), nl,
                   write('Put_char: '), put_char(Y), nl.



/** Wywolanie:
39 ?- wstaw_litery('F', 'M') .
Put: F
Put_char: M
true.
*/




% PRZYKLAD 6

zapisz :- write('Podaj tekst: '),
          read(T),
		  open('plik1.txt', write, X),
		  current_output(Cout), 
		  set_output(X),
		  write(T),
		  close(X),
		  set_output(Cout) .


/** Wywolanie:
41 ?- zapisz .
Podaj tekst: aaabb1
|: .
*/



% PRZYKLAD 7

czytaj :- open('plik2.txt', read, X),
		 set_input(X),
		 read(T),
		 write('Tekst z pliku: '), write(T),
		 close(X) .

/** Wywolanie:
52 ?- czytaj .
Tekst z pliku: cccdd2
true.
*/

