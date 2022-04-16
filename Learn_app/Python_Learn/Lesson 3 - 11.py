a = eval(input())
b = 1
c = a*2
while c > a:
    a *= 1.03  
    print('#%d year: %.2f'%(b, a))
    b += 1

print(b-1,)
