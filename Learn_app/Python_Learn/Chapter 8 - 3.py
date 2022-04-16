def main():
    list1 = []
    count1 = 1
    while count1 <= 9 :
        str1 = input()
        if len(str1) <= 10 :
            list1.append(str1)
            count1 += 1
        else :
            print('Try again')
            continue

    for i in range(1,10):
        print('|%-15s|'%(list1[i-1]),end='')
        if i%3 == 0 :
            print()

main()
