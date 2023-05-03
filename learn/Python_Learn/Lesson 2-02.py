a = eval(input())

if (a%3==0) and (a%5==0):
    print('%.0f is a multiple of 3 and 5.'%(a))
elif(a%3==0):
    print('%.0f is a multiple of 3.'%(a))
elif(a%5==0):
    print('%.0f is a multiple of 5.'%(a))
else:
    print('%.0f is not a multiple of 3 or 5.'%(a))
