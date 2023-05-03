num_alph = 26
k = eval(input())

for i in range(k):
    pangram = input()
    set_pangram = set(pangram.lower())

    set_pangram.remove(' ')

    
    print(len(set_pangram) == num_alph)
