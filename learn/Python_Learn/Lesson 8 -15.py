def main():
    str1 = input()

    str2 = input()
    if str1.find(str2) != -1 :
        str1 = str1.replace(str2, 'Bright')
        print(str1)
    else :
        print(str2 + 'is not found')
    
main()
