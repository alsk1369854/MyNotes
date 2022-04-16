
def compute():
    dic = {}
    while True:
        key = input('Key: ')
        if key == 'end' :
            return dic
        value = input('value: ')

        dic[key] = value

def main():
    print('Create dict1')
    dict1 = compute()

    print('Create dict2')
    dict2 = compute()


    merge_dict = dict1.copy()
    print('1',merge_dict)
    merge_dict.update(dict2)
    print('2',merge_dict)
    
    sorted_dict = sorted(merge_dict)
    print('3',sorted_dict)
    for i in sorted_dict :
        print('%s: %s'%(i, merge_dict[i]))

main()
