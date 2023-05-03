a = []

for i in range (4):
    b = input()
    a.append(b)

print ('|%10s %10s|'%(a[0],a[1]))
print ('|%10s %10s|'%(a[2],a[3]))
print ('|%-10s %-10s|'%(a[0],a[1]))
print ('|%-10s %-10s|'%(a[2],a[3]))
