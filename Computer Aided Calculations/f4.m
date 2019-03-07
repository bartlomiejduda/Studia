function x = f4(n)

y(1:2) = 1;

for k=3:n
    y = [y(2) y(1)+y(2)];
end

x=y(2);


end