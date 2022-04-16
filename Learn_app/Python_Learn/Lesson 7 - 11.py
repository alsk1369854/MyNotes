def main():
    tuple1 = ()
    for i in range(5):
        n = eval(input())
        tuple1 += (n,)

    print(tuple1)
    print('max of the tuple is %d'%(max(tuple1)))
    print('min of the tuple is %d'%(min(tuple1)))
    print('sum of the tuple is %d'%(sum(tuple1)))

main()
