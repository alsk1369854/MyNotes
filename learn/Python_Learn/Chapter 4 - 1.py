a = eval(input('Enter a number:'))
factor = 0
for i in range(2, a+1):
    if a%i == 0:
        factor = i
        break
print('The smallest factor is',factor)
