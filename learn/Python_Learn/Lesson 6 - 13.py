import random

def lotto():
    lst = []
    count = 0
    while count < 5:
        lstnub = random.randint(1, 49)
        if lstnub not in lst:
            lst.append(lstnub)
            count += 1
    lst.sort()
    print(lst)

def main():
    for i in range(5):
        lotto()

main()
