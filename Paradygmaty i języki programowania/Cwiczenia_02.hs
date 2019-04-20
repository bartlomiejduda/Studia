dodaj :: Double -> Double -> Double
dodaj x y = x+y

a1::[Double] 
a1=[2.4, 6,8,9]

napis:: String -> String 
napis x = "Czesc " ++ x

--zad 1
witaj:: String -> String
witaj x = "Witaj " ++ x ++ "!!!"

--zad 2
objetosc x y z = x*y*z 

--zad 3a
podwajam x = x*2 

--zad 3b
liczby = [11,12.. 99]

--zad 3c
--map podwajam liczby

--zad3d
mod13 x = [a | a <- liczby, mod a 13 == 0]

--zad 4
--inicjaly :: String -> String -> String
inicjaly x y = take 1 x ++ "." ++ take 1 y ++ "."
inicjaly2 x y = [head x] ++ "." ++ [head y] ++ "."

--zad 5 
--unitaryN :: Num -> [[Num]]
unitaryN n =[[ if i==j then 1 else 0 | i<-[1..n] ] | j <- [1..n]]

--zad 6
--trzy_f x y z = [ 100*x + 10*y + z |  mod z 3 ==0, x /= y, x /= z, y /= x, y /= z      ] 

allDifferent :: (Eq a) => [a] -> Bool
allDifferent []     = True
allDifferent (x:xs) = x `notElem` xs && allDifferent xs

digits :: Integer -> [Int]
digits n = map (\x -> read [x] :: Int) (show n)


listDigits = [ x | x <- [100..999] , x `mod` 3 == 0, allDifferent (digits x)==True ]

listDigits' = [100*x+10*y+z | x <- [1..9], y <- [0..9], z <- [0..9], mod (x + y + z) 3 == 0, x /= y, x /= z, y /= z ]

--zad 7
getLower :: [Char] -> [Char]
getLower "" = error "pusty string"
getLower str = str
