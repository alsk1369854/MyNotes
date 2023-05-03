def main():
    f_name = input()

    c_line = c_word = c_char = 0

    file = open(f_name, 'r')
    for line in file :
        c_line +=1

        word = line.strip('\n').split(' ')
        c_word += len(word)
        print(word)
        
        c_char += sum([len(x) for x in word])

    print(c_line)
    print(c_word)
    print(c_char)
    
main()
