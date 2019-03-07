function result = f1(n)



if n < 3
    result = 1;
else
    result = f1(n-1) + f1(n-2);
end

end