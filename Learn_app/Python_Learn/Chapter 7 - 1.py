import random

def main():
    list1 = []
    for i in range(10):
        list1.append(random.randint(1,100))

    tuple1 = tuple(list1)

    print(list1)
    print(tuple1)
    
main()
