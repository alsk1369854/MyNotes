def compute(x):
    n1 = 0
    n2 = 1
    print('%d %d '%(n1,n2),end= '')
    for i in range(3, x+1):
        n3 = n1+n2
        print(n3,end= ' ')
        n1 = n2
        n2 = n3

a = eval(input())
compute(a)
    
