size = 2
mat = []
for x in range(2):
    mat.append([])
    print('Enter matrix %d'%(x+1))
    for i in range(size):
        mat[x].append([])
        for j in range(2):
            mat[x][i].append(eval(input('[%d, %d]:'%(i+1,j+1))))

count = 0
for x in range(2):
    print('Matrix %d:'%(x+1))
    for i in range(size):
        for j in range(2):
            print('%-2d'%(mat[x][i][j]),end='')
            count += 1
            if count%2 == 0:
                print()

print('Sum of 2 matrices:')
for i in range(2):
    for j in range(2):
        print((mat[0][i][j]+mat[1][i][j]),end=' ')
        count += 1
        if count%2 == 0:
            print()
