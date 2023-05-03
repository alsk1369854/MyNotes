import math

a, b, c = eval(input('Enter a, b, c:'))

S = b**2-(4*a*c) 

if S > 0:
    A1 = (-b+math.sqrt(S))/(2*a)
    A2 = (-b-math.sqrt(S))/(2*a)
    print('The solutions are %f and %f'%(A1, A2))

elif S == 0:
    A1 = -b / 2*a
    print('The solution is %f '%A1)

else :
    print('NO solution.')
    
