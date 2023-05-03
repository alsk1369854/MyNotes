def factor(n):
    for x in range(1, n+1):
        ans = 1
        for i in range(1, x+1):
            ans *= i
        print('%2d! ='%(x),ans)

def main():
    n = eval(input())
    factor(n)




main()
