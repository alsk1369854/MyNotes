import math

x, y  = eval(input('Enter x, y:'))

dist = math.sqrt((x-0)**2+(y-0)**2)

if dist <= 8 :
    print('(%d, %d) is inside of the circle.'%(x,y))
else:
    print('(%d, %d) is outside of the circle.'%(x,y))
