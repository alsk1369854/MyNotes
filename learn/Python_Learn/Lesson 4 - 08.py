odd = 0
even = 0
for i in range(10):
    a = eval(input())
    if a%2 == 0:
        even += 1
    else:
        odd += 1
print('\nEven number: %d'%even)
print('Odd number: %d'%odd)
