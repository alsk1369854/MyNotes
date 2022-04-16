def main():
    name = input()
    cheak = True

    if not name[0].isalpha() :
        cheak = False
    for i in range(len(name)) :
        if not name[i].isalnum() :
            cheak = False

    if cheak :
        print('Valid variable name')
    else :
        print('Invalid variable name')
    

main()
