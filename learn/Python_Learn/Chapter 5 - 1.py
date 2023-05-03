def multiply99():
    for i in range(1,10):
        for x in range(1, 10):
            print('%d*%d=%2d'%(x, i, x*i),end=' ')
        print()

def printStar():
    for i in range(72):
        print('*',end='')
    print()
def main():
    printStar()
    multiply99()
    printStar()
    

main()
