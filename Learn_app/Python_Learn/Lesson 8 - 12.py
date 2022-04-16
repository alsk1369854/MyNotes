def main():
    list1 = []
    while True :
        str1 = input()
        if str1 != 'end' :
            list1 = str1.split()
            print(list1)
        else :
            break
        

main()
