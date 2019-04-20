import System.IO
--zad 1
sumPositiveOnList :: [Int] -> Int
sumPositiveOnList [] = 0
sumPositiveOnList (x:xs) = (if x>0 then x else 0) + sumPositiveOnList xs

--zad 2
--howManyTimes 'a' "bananas" -> 3
--count (=='a') "bananas" 
count :: Num p => (t -> Bool) -> [t] -> p
count _ [] = 0  
count f (x:xs) = (if f x == True then 1 else 0) + count f xs 

--zad 3
--import System.IO
dzialaj = do 
putStr "Podaj a: "
a <- getLine 
putStr "Podaj b: "
b <- getLine 
 --putStrLn "a+b = " ++ (a+b) 
putStrLn ("a+b = " ++ show ((read a :: Double) + (read b :: Double)))
 
 
 --zad 4
sN n s 
    | n<0 = "Bledna wartosc n"
    | n==0 = ""
    | otherwise = s ++ " " ++ sN (n-1) s 
  

lancuchn = do 
 putStr "Podaj lancuch: "
 s <- getLine 
 putStr "podaj liczbe n: " 
 n <- getLine 
 putStr $ sN (read n::Int) s 

wyswietlPlik = do 
 handle <- openFile "a2.hs" ReadMode 
 tekst <- hGetContens handle 
 putStrLn tekst 
 hClose handle 
 
 

