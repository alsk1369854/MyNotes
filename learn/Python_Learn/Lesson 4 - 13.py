a = []
for i in range(3):
    a.append(eval(input()))
b = min(a)
for x in range(b, 0, -1):
    if a[0]%x == 0 and a[1]%x == 0 and a[2]%x == 0:
        print('gcd(%d, %d, %d) = %d'%(a[0],a[1],a[2],x))
        break
