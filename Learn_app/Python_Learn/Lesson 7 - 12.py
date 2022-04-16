def main():
    tuple1 = ()
    while True:
        number = eval(input())
        if number == -9999:
            break
        tuple1 += (number,)

    print(tuple1)
    print('length of the tuple is %d'%(len(tuple1)))
    print('the first element is %d'%(tuple1[0]))
    print('the last element is %d'%(tuple1[-1]))
        
main()
