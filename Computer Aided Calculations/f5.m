function x = f5(n)

x = det(eye(n-1)+diag(i*ones(1, n-2), 1) + diag(i*ones(1, n-2), -1));


end