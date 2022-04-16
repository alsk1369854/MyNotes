a = eval(input())
count = 0
x = 2
while count < a:
    prime = 1
    for i in range(2, x):
        if x%i == 0:
            prime = 0
    if prime == 1:
        count += 1
        print('%3d'%x,end =' ')
        if count%10 == 0:
            print()
    x += 1
            
