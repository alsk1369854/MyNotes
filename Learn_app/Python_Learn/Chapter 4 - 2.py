a = eval(input('Enter a number1:'))
b = eval(input('Enter a number2:'))
m = a
if b < a:
    m=b

for i in range(m, 0, -1):
    if a%i == 0 and b%i == 0:
        print('The greatest common factor is',i)
        break
