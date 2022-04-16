a = 1
while a <= 9 :
    b = 1
    while b <= 9:
        print('%d*%d=%2d'%(b, a, b*a), end ='  ')
        b += 1
    print()
    a += 1
