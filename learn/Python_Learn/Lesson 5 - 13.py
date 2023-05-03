def isLeap(a):
    if a%400 == 0 or (a%4 == 0 and a%100 != 0):
        print(a,'is a leap year.')
    else:
        print(a,'is not a leap year.')

def main():
    a = eval(input())
    while a != -9999:
        isLeap(a)
        a = eval(input())





main()
