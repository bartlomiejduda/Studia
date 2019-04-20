drugi (_,b,_) = b
aa x = "kjvhnern" ++ x

f1 :: Int -> String
f1 5 = "Na piatke"
f1 7 = "Szczesliwa siodemka"
f1 8 = "Teraz osiem"
f1 x = "Nie wiem, co to jest"

f2 :: Int -> String
f2 x
 | x<=0 = "Bledne dane"
 | x<8 = "Przedszkolak"
 | x<18 = "Pora nauki"
 | otherwise = "Pracuj!!!"
 
kolo r = pi*r*r 

kolo' r = pi*kw 
 where kw=r*r
 
kolo'' r = 
 let kw=r*r 
 in pi*kw 
 
 
--zad 1
znak :: Int -> String -> Char
znak n str = str !! n 

--zad2
listaA = [("Ania", "Kowalska"), ("Bartek",  "Nowak"), ("Cezary", "Pazura"), ("Damian", "Noeck")]
listaB = [33, 34, 53, 54, 73, 74]

--a)
listaA1 = [fst(listaA !! 0), fst(listaA !! 1), fst(listaA !! 2), fst(listaA !! 3) ]
--b)
listaA2 = [snd(listaA !! 0), snd(listaA !! 1), snd(listaA !! 2), snd(listaA !! 3) ]

 
 