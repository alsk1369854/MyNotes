def main():
    n = eval(input())

    for i in range(n):
        str1 = input()
        str1_list = str1.split()
        str1_list = [eval(x) for x in str1_list]
        print('%.2f'%(max(str1_list)-min(str1_list)))

main()
