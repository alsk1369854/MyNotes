a = eval(input())
b = eval(input())
count = 1
c = 0
total = 0
for i in range(a, b+1):
    if i%4 == 0 or i%9 == 0:
        if count%10 == 0:
            print('%-4d'%(i))
        else:
            print('%-4d'%(i),end='')
        c += 1
        total += i
        count += 1
print('\n',c)
print(total,)
