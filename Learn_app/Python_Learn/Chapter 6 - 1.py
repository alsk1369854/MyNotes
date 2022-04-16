def add(lst):
    print('Sum of matrices')
    for i in range(2):
        for j in range(2):
            print('%3d'%(lst[0][i][j]+lst[1][i][j]),end = '')
        print()
    
def show(lst):
    for i in range(2):
        print('Matrix%2d'%(i+1))
        for j in range(2):
            for x in range(2):
                print('%3s'%(lst[i][j][x]),end = '')
            print()    

def main():
    lst = []
    for i in range(2):
        print('Enter matrix%d'%(i))
        lst.append([])
        for j in range(2):
            lst[i].append([])
            for x in range(2):
                lst[i][j].append(eval(input('[%d %d]: '%(j+1,x+1))))
    show(lst)
    add(lst)
main()
