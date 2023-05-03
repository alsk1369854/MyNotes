def main():
    set1 = set()
    while True:
        number = eval(input())
        if number == -9999 :
            break
        set1.add(number)

    print(set1)

main()
