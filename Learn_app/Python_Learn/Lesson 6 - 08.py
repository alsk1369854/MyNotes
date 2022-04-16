size = 3
mat = []
for i in range(size):
    mat.append([])
    for j in range(size):
        mat[i].append(eval(input()))

mat_max = mat_min = 0
max_index = min_index = [0, 0]

for i in range(size):
    for j in range(size):
        if mat[i][j] > mat_max :
            mat_max = mat[i][j]
            max_index = [i, j]
        if mat[i][j] < mat_min :
            mat_min = mat[i][j]
            min_index = [i, j]

print('Index of the largest number %d is : (%d, %d)'%(mat_max, max_index[0], max_index[1]))
print('Index of the smallest number %d is : (%d, %d)'%(mat_min, min_index[0], min_index[1]))
