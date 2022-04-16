def compute(x, y):
    ans = 0
    for i in range(x, y+1):
        ans += i
    return ans

a = eval(input())
b = eval(input())
print(compute(a, b))
