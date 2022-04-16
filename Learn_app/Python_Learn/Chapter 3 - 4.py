a = eval(input('Enter a number:'))
for i in range(a, 0, -1):
    for x in range(i):
        print('%4d'%(x+1), end='')
    print()
