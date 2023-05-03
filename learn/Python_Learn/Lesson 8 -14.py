def main():
    list1 = []

    count = 0
    while True:
        str1 = input()
        if len(str1) <= 10:
            list1.append(str1)
            count += 1
            if count == 9 :
                break
        else :
            print('try again')
            continue

    count = 0
    for i in range(9):
        print('|%s|'%(list1[i].center(15)),end='')
        count += 1
        if count%3 == 0:
            print()

main()
