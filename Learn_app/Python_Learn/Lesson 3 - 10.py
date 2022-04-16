import math

n = eval(input())
ans = 0

for i in range(2, n+1):
    ans += 1/(pow(i-1, 0.5) + pow(i, 0.5))

print(ans)
