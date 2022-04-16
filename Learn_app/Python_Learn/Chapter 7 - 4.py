def main():
    dict1 = {}
    while True:
        key = input('Input Key: ')
        if key == '-9999':
            break
        value = input('Input Value: ')
        dict1[key] = value

    print(dict1)
    key = input('which key do you want to delete: ')
    if key in dict1:
        dict1.pop(key)
    else:
        print('no found')
    print(dict1)
    
main()
