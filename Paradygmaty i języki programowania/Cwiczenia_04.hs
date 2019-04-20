
--zad 1
dzielniki :: Int -> [Int]
dzielniki n = [x | x<-[1..n], n `mod` x ==0]

isPrime :: Int -> Bool
isPrime n = dzielniki n ==[1,n]

nPrimes :: Int -> [Int]
nPrimes n = [x | x<-[2,3.. n], isPrime x == True]

suma []=0 
suma (x:xs) = x + suma xs 

iloczyn []=0
iloczyn (x:xs) = x * iloczyn xs 

--zad 2
c :: Int -> Int 
c 1 =1 
c n = 1 +2*(c (n-1))

--zad 4
--a)
firstFromList :: [a] -> a
firstFromList [] = error "blad"
firstFromList (x:_) = x

--b)
secondFromList :: [a] -> a
secondFromList [] = error "pusta"
secondFromList [_] = error "tylko jeden"
secondFromList (_:y:_) = y

--c)
lastFromList :: [a] -> a
lastFromList [] = error "pusta"
lastFromList (_:xs) = lastFromList xs 

--d)
prelastFromList :: [a] -> a
prelastFromList [] = error "pusta"
prelastFromList [_] = error "tylko jeden"
prelastFromList [x,_] = x 
prelastFromList (_:xs) = prelastFromList xs 

--zad 5
mergeLists :: [a] -> [a] -> [a]
mergeLists xs     []     = xs
mergeLists []     ys     = ys
mergeLists (x:xs) (y:ys) = x : y : mergeLists xs ys










