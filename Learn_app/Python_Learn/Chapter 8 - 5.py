def main():
    str1 = input()
    for i in range(len(str1)) :
        if str1[i].isdigit() :
            print(str1[i]+':is a digit.')
        elif str1[i].isalpha() and str1[i].isupper() :
            print(str1[i]+':is upper alpha.')
        elif str1[i].isalpha() and str1[i].islower() :
            print(str1[i]+':is lower alpha.')
        elif str1[i].isspace() :
            print(str1[i]+':is a space.')
        else :
            print(str1[i]+':is a symbol character.')

main()
