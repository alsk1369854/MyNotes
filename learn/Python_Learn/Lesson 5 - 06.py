def compute(a, b, c):
    data = b**2 - 4*a*c

    if data < 0:
        return 0
    elif data == 0:
        return -b / (2*a)
    else:
        ans1 = (-b - data**0.5) / (2*a)
        ans2 = (-b + data**0.5) / (2*a)
        return str(ans1) + ', ' + str(ans2)

a = eval(input())
b = eval(input())
c = eval(input())
f = compute(a, b, c)
if f == 0: 
    pirnt('You equation has no root.')
else:
    print(f)
