def main():
    list1 = []
    while True :
        str1 = input()
        if str1 != 'end' :
            if str1.endswith('e') :
                list1.append(str1)
        else :
            print(list1)
            break
        

main()
