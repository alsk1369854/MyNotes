n = eval(input())
total = 0

for i in range(n, 0 ,-1):
    total += 1/i
print('1/n + 1/(n-1) + 1/(n-2) +...+ 1 =',total)
