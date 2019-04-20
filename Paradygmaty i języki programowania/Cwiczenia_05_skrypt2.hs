import System.IO

main = do 
 hSetBuffering stdout NoBuffering
 putStr "Podaj lancuch: "
 s <- getLine 
 putStr "podaj liczbe n: " 
 n <- getLine 
 putStr $ sN (read n::Int) s 
 
 
sN n s 
	| n<0 = "Bledna wartosc n"
	| n==0 = ""
	| otherwise = s ++ " " ++ sN (n-1) s 