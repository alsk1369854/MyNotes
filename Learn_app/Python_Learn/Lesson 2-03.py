year = int(input())

if (year%400==0) or (year%4==0 and year%100!=0):
    print('%.0f is a leap year.'%(year))
else:
    print('%.0f is not a leap year.'%(year))
