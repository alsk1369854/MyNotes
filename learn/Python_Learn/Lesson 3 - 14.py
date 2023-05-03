n = eval(input())
total = 0
for i in range(1, n+1):
    total += 1/i
print('1 + 1/2 + 1/3 + 1/4 + ... + 1/%d = %f'%(n, total),total)
