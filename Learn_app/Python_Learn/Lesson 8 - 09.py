def main():
    password = input()

    cheak = True
    if len(password) <= 7 \
       or password.islower() \
       or password.isdigit() \
       or password.isalpha() :
        cheak = False
        
    else:
        for i in range(len(password)):
            if not password[i].isalpha() \
               and not password[i].isdigit() :
                cheak = False
                break
    if cheak :
        print('Valid Password')
    else :
        print('Invalid Password')

main()
