import math
r = eval(input())
s = 2*r*math.sin(math.pi/5)
area = 5*(s**2)/4*math.tan(math.pi/5)

print('s: %.2f\nArea: %.2f'%(s, area))
