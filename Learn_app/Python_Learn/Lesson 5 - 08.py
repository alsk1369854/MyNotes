def compute(a, b):
    if a<b:
        f = a
    else:
        f = b
    for i in range(f, 0, -1):
        if a%i == 0 and b%i == 0:
            return i

a, b = eval(input())
print(compute(a,b))
