a = eval(input())

for i in range(1, a+1):
    for x in range(1, i+1):
        print('%4d'%(i*x),end ='')

    print()
