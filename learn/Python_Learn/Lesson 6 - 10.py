num_week = 4
num_day = 3
mat = []

for i in range(num_week):
    print('Week %d:'%(i+1))
    mat.append([])
    for x in range(num_day):
        mat[i].append(eval(input('Day %d:'%(x+1))))

comb = []
for i in range(num_week):
    comb.extend(mat[i])

a = sum(comb)/len(comb)
print('Average: %.2f'%(a))
print('Highest: %.2f'%(max(comb)))
print('Lowest: %.2f'%(min(comb)))
