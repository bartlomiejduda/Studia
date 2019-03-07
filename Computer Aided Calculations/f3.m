function y = f3(n)

%x = zeros(1, n)
x(1:2) = 1;

for k = 3:n
    x = [x x(k-1)+x(k-2)];
end

    y = x(end);
end