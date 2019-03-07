A = randi(10, 1000, 10);
tic
B = zeros(size(A));

t=0

for i=1:size(A, 1)
    B(i, :)=A(size(A, 1)+1-i, :);
end

t=toc