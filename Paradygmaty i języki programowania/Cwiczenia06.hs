import System.IO
import System.Random

--zad 1
zPliku = do
  putStr "Podaj nazwe pliku: "
  nazwa <- getLine
  handle <- openFile nazwa ReadMode
  tekst <- hGetContents handle 
  putStrLn "\n=========="
  putStr tekst 
  hClose handle 
  
  
zPliku' = do 
  putStr "Podaj nazwe pliku: "
  nazwa <- getLine
  tekst <- readFile nazwa 
  putStrLn "=========="
  putStrLn tekst 
  
  
--zad 2
doPliku = do 
  putStr "Podaj tekst: "
  tekst <- getLine 
  putStr "Podaj nazwe pliku: "
  nazwa_pliku <- getLine 
  writeFile nazwa_pliku tekst
  
  
--zad 3
doPlikuOdwrotnie = do 
  putStr "Podaj nazwe pliku: "
  nazwa <- getLine
  tekst <- readFile nazwa 
  let t = lines tekst 
  let t2 = reverse t
  let t3 = unlines t2
  putStrLn "Odwrocone linijki:"
  putStr t3
  writeFile "out.txt" t3
  
  
--zad 4 
wczytaj_i_policz = do 
  putStr "Podaj nazwe pliku: "
  nazwa <- getLine
  tekst <- readFile nazwa 
  let t = lines tekst 
  let t_len = length t 
  let t_len_str = "Liczba wierszy: " ++ (show t_len)
  putStrLn t_len_str
  
  let w = words tekst 
  let w_len = length w 
  let w_len_str = "Liczba slow: " ++ (show w_len)
  putStrLn w_len_str
  
  let l_len = 55 
  let l_len_str = "Liczba liter: " ++ (show l_len)
  putStrLn l_len_str
  
  
--zad 5
fib :: Int -> Integer
fib 0 = 0
fib 1 = 1
fib n = fib (n-1) + fib (n-2) 

fib' :: Int -> Integer 
fib' n = fst $ sequence !! n 
  where
    sequence = iterate (\(x, y) -> (y, x + y)) (0,1)
 
 

 
 
data Week = Mo | Tue | Wed | Thur | Fri | Sat | Sun 

day :: Week -> String
day Sat = "Weekend" 
day Sun = "Weekend" 
day x = "Time for work" 

ff :: Int -> Maybe Int 
ff 0 = Nothing 
ff x = Just $ 2*x

losuj = do 
  gen <- newStdGen 
  putStrLn $ take 4  (randomRs ('1', '6') gen)
  
  
--zad 6
data Coin = Avers | Revers deriving (Eq, Ord, Show)
fc :: Int -> Coin 
fc 0 = Avers 
fc 1 = Revers 

coin3 = do
  gen <- newStdGen 
  putStrLn . show $ map fc (take 3 (randomRs (0, 1) gen :: [Int]))
  
  
  
  
  