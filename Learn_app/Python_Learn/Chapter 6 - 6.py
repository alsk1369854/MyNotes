def compute(a, b):
    lst = []
    for i in range(a,b+1):
        count = 0
        g = i
        num = []
        while g != 0:
            n = g%10
            num.append(n)
            g //= 10
        for x in range(len(num)):
            count += pow(num[x],len(num))
        if i == count :
            lst.append(i)
    return lst

def main():
    a = eval(input())
    b = eval(input())
    lst = compute(a, b)
    for i in range(len(lst)):
        print(lst[i],end = ' ')
    
main()
