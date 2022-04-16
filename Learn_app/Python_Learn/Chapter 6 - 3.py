import random

def maxAndmin(lst):
    print(lst[1])
    print(lst[len(lst)-2])

def main():
    lst = []
    count = 0
    while count < 100:
        num = random.randint(1,1000)
        if num not in lst :
            lst.append(num)
            count += 1
            
    lst.sort()
    for i in range(1, 100+1):
        print('%4d'%(lst[i-1]),end=' ')
        if i%10 == 0 :
            print()
    print()
    maxAndmin(lst)
    
main()
