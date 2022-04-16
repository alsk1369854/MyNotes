def display(dict1):
    for i in dict1:
        print('%s:%s'%(i,dict1[i]))
    
def query(dict1):
    key = input('Input key: ')
    print(dict1[key])
    
def delete(dict1):
    key = input('Input key: ')
    if key not in dict1:
        print('the key is not found')
        return dict1
    dict1.pop(key)
    return dict1
    
def add(dict1):
    key = input('Input Key: ')
    if key in dict1:
        print('the key is alredy existed.')
        return dict1
    value = input('Input value: ')
    dict1[key] = value
    return dict1
    
def main():

    dict1 = {}
    while True:
        print()
        print('1: add')
        print('2: delete')
        print('3: query')
        print('4: display')
        print('5: exit')
        n = eval(input('Which one: '))
        if n == 1 :
            dict1 = add(dict1)
        elif n == 2 :
            dict1 = delete(dict1)
        elif n == 3 :
            query(dict1)
        elif n == 4 :
            display(dict1)
        elif n == 5 :
            break
        else:
            print('Try again')

main()
