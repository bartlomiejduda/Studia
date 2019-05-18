
% zad 1
rw :- write('Wpisz jakis tekst: '), read(T), write('Podany tekst: '), write(T) .

rw2 :- write('Wpisz jakis tekst: '), 
       read(T), 
	   write('Podany tekst: '), 
	   write(T), nl,
	   writeln('Czy kontynuowac? t/n'),
	   read(A), A=='t' -> rw2; 
	   write("Koniec") .
	   
rw3 :- write('Wpisz jakis tekst: '), 
       read(T), 
	   write('Podany tekst: '), 
	   write(T), nl,
	   writeln('Czy kontynuowac? t/n'),
	   read(A), member(A, ['t', 'T']) -> rw3,
	   write("Koniec") .

% zad 2
oblicz :- write('Podaj A: '),
          read(A),
		  write('Podaj B: '),
		  read(B),
		  W is (A+B)/2,
		  write('Wynik: '), write(W), nl .
		  
% zad 3
oblicz_kolo :- write('Podaj r: '),
               read(R),
			   W1 is 2 * pi * R,  
			   W2 is pi * R * R,
			   write('W1: '), write(W1), nl,
			   write('W2: '), write(W2), nl .
			   
% zad 4
wczytaj_liste(['end'|L],L) :- !.     
wczytaj_liste(L1,L) :- write('Podaj element: '),
                       read(X),
					   wczytaj_liste([X|L1],L) .

lista :- lista(L), write(L) .		  
lista(L) :- write('Wpisz elementy. Koniec - wpisz: end.'), nl, 
            wczytaj_liste([],L2), reverse(L2, L) .   
		  
		  
% zad 5
zapisz :- write('Podaj tekst: '),
          read(T),
		  open('plik1.txt', write, X),
		  current_output(Cout), 
		  set_output(X),
		  write(T),
		  close(X),
		  set_output(Cout) .
		  
% zad 6 
pliki :- open('plik1.txt', read, X),
         open('plik2.txt', write, Y),
		 current_input(Cin), set_input(X),
		 current_output(Cout), set_output(Y),
		 % read(T),
		 % write(T),
		 go,
		 close(X), close(Y),
		 set_output(Cout),
		 set_input(Cin) .
		
		
go :- read(T), dzialaj(T) .

dzialaj(end_of_file) :- !.
% dzialaj(T) :- write(T), nl, go .
dzialaj(T) :- string_upper(T, T1), write(T1), nl, go .
		
		
		
		  
		  
		  