run = eval(input())
b = 0
for i in range(run):
    a = eval(input())
    ans = 0
    b = a
    while b != 0:
        ans += b%10
        b = b//10
    print('Sum of all digits of %d is %d'%(a, ans))
