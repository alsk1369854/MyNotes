
def compute(lit):
    for i in range(len(lit)):
        for j in range(len(lit[i])):
            print('%4d'%lit[i][j],end='')
        print()

rows = eval(input())
cols = eval(input())
lit = []
for i in range(rows):
    lit.append([])
    for j in range(cols):
        lit[i].append(j-i)

compute(lit)
