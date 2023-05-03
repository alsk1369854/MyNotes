def main():
    str1 = input()
    if len(str1) == 6:
        print('|%-10s|'%(str1))
        print('|%s|'%(str1.center(10)))
        print('|%10s|'%(str1))

main()
