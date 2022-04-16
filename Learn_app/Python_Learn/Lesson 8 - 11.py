def main():
    b = []
    while True:
        str1 = input()
        if str1 == 'end':
            break
        if str1.startswith('B'):
            b.append(str1)
            
    print(b)

main()
