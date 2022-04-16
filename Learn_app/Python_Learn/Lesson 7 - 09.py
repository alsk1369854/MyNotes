def main():
    dic = {}
    while True:
        k = input('Key: ')
        if k == 'end':
            break
        v = input('Value: ')
        dic[k]=v

    sorted_dic = sorted(dic)

    for i in sorted_dic:
        print('%s: %s'%(i, dic[i]))
    

main()
