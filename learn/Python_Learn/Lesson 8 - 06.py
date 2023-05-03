def compute(sentence, word):
    return sentence.count(word)

def main():
    sentence = input()
    word = input()

    print('%s occurs %d time(s)'%(word, compute(sentence, word)))

main()
