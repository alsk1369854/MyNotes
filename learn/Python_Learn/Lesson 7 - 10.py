def main():
    dic = {}
    while True:
        key = input('Key: ')
        if key == 'end' :
            break
        value = input('value: ')
        dic[key]=[value]
    n = input('Search Key: ')
    print( n in dic)

main()
