a = []

for i in range (5):
    b = eval(input())
    a.append(b)

print(a)
print('Sum = %.1f'%(sum(a)))
print('Average = %.1f'%(sum(a)/len(a)))
