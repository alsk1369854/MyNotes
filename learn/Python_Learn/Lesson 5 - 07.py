def compute(a):
    if a < 2 :
        f = 0
    elif a == 2:
        f = 1
    for i in range(2, a):
        f = 1
        if a%i == 0:
            f = 0
    return f

a = eval(input())
if compute(a) == 0:
    print('Not prime.')
else:
    print('Prime.')
