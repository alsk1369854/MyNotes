import random

def main():
    lst = []
    count1 = 0
    while count1 < 100:
        num = random.randint(1, 1000)
        if num not in lst:
            lst.append(num)
            count1 += 1
    lst.sort()
    count = 0

    for i in range(100):
        print('%4d'%(lst[i]),end= ' ')
        count += 1
        if count%10 == 0:
            print()
    print()
    print(lst[1])
    print(lst[-2])
    
main()
