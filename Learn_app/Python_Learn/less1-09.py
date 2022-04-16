import math

a = eval(input())
b = eval(input())

area = (a*pow(b,2)) / (4*math.tan(math.pi/a))

print('%.4f'%(area))
