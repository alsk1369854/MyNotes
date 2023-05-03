def main():
    num = []

    while True:
        n = eval(input())
        if n == -9999:
            break
        num.append(n)
    tuple1 = tuple(num)
    print(tuple1)
    print('Length:',(len(tuple1)))
    print('Max:',(max(tuple1)))
    print('Min:',(min(tuple1)))
    print('Sum:',(sum(tuple1)))
    
    
main()
