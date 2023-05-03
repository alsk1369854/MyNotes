a = []

for i in range (4):
    b = eval(input())
    a.append(b)

print("(",a[0],",",a[1],")")
print("(",a[2],",",a[3],")")

print('Distance = %.4f'%(pow(pow(a[0]-a[2],2)+pow(a[1]-a[3],2),0.5)))
