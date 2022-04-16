a = eval(input())

for i in range(1, a+1):
    total = 1
    for x in range(1, i+1):
        total *= x
    print('#%2d! = %d'%(i, total), end='')
    print()
