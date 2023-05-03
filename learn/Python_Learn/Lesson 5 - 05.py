def compute(a, b, c):
    for i in range(c):
        for x in range(b):
            print('%s'%(a),end=' ')
        print()

a = input()
b = eval(input())
c = eval(input())
compute(a, b, c)
