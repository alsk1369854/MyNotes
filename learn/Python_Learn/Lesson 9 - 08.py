def main():
    f_name = input()
    n = eval(input())
    word_dict = dict()

    file = open(f_name, 'r')
    for line in file :
        word = line.strip('\n').split(' ')

        for i in word :
            if i in word_dict :
                word_dict[i] += 1
            else :
                word_dict[i] = 1

    word_list = word_dict.items()
    wordQ = [x for (x, y) in word_list if y == n]

    for i in wordQ :
        print(i)
main()
