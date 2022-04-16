import random

def main():
    list1 = []
    for i in range(10):
        list1.append(random.randint(1,100))

    set1 = set(list1)
    print(list1)
    print(set1)

main()
