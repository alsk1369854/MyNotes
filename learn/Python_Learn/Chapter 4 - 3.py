a = eval(input())
b = eval(input())
count = 0
for i in range(a, b+1):
    prime = 1
    for x in range(2, i):
        if i%x == 0:
            prime = 0
    if prime == 1:
        print('%2d'%(i),end = '  ')
        count += 1
        if count%10 == 0:
            print()
