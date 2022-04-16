def compute(q,p):
    if q < p:
        f = q
    else:
        f = p
    for i in range(f, 0, -1):
        if q%i == 0 and p%i == 0:
            return i


x,y = eval(input())
m,n = eval(input())

p = x*n + y*m
q = y*n
f = compute(p,q)
print('%d/%d + %d/%d = %d/%d'%(x, y, m, n, p/f, q/f))
