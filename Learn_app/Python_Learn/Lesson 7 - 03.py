def main():
    tuple1 = ()

    while True:
        word = input()
        if word == 'end':
            break
        tuple1 += (word,)

    print(tuple1)
    print(tuple1[0:3])
    print(tuple1[-3:])

main()
