a = eval(input())
b = 1
for i in range(2, a):
    if a%i == 0:
        b = 2 
        break
if b == 1:
    print('%d is a prime number.'%a)
else:
    print('%d is not a prime number.'%a)
