a = eval(input())
for i in range(a):
    for x in range(a-i,1,-1):
        print(' ',end = '')
    for y in range(i*2+1):
            print('*',end = '')
    print()
