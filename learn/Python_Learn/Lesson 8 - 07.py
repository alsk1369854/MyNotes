def main():
    str1 = input()
    list1 = [eval(x) for x in str1.split()]

    print('Tatal =',(sum(list1)))
    print('Average = ',(sum(list1)/len(list1)))
    

main()
