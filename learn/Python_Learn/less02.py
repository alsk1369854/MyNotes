a = []

for i in range (4):
    b = eval(input())
    a.append(b)

print ("|%7.2f %7.2f|"%(a[0], a[1]))
print ("|%7.2f %7.2f|"%(a[2], a[3]))
print ("|%-7.2f %-7.2f|"%(a[0], a[1]))
print ("|%-7.2f %-7.2f|"%(a[2], a[3]))
