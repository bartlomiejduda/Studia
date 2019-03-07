function x = f6(n)

if n < 3
    x = 1;
elseif mod(n, 2)
    x = (f6(n-1)/2)^2 + (f6((n+1)/2))^2;
else
    x = (f6(n-1)/2)^2 - (f6((n+1)/2))^2;

end
end