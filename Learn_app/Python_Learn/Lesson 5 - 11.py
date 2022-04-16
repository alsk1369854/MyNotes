import random

def randNum(n):
    count = 0
    for i in range(n):
        print('%3d'%random.randint(1,100),end=' ')
        count += 1
        if count%10 == 0:
            print()



def main():
    n = eval(input())
    randNum(n)

main()
