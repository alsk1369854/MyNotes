tuple1 = set()

while True:
    n = eval(input())
    if n == -9999:
        break
    tuple1.add(n)

print('Length:',len(tuple1))
print('Max',max(tuple1))
print('Min',min(tuple1))
print('Sum',sum(tuple1))
