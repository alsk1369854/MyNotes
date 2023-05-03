a = eval(input())

for i in range(1, a+1):
    for x in range(1, a+1):
        print('%d * %d = %2d'%(x, i, x*i), end='   ')
    print()
