def main():
    dict10 = {}
    while True:
        key = input('Input Key: ')
        if key == '-9999' :
            break
        value = input('Input Value: ')
        dict10[key] = value

    print(dict10)

main()
