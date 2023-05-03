n = eval(input())
total = 0

for i in range (3, n+1, 2):
    total += (i-2)/i

print('total = ',total)
