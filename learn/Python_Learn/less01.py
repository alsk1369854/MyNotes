a = []

for i in range (4):
    b = eval(input())
    a.append(b)

print ("|{:5.0f} {:5.0f}|".format(a[0],a[1]))
print ("|{:5.0f} {:5.0f}|".format(a[2],a[3]))
print ("|{:<5.0f} {:<5.0f}|".format(a[0],a[1]))
print ("|{:<5.0f} {:<5.0f}|".format(a[2],a[3]))
    
print ("|%5.0f %5.0f|"%(a[0],a[1]))
print ("|%5.0f %5.0f|"%(a[2],a[3]))
print ("|%-5.0f %-5.0f|"%(a[0],a[1]))
print ("|%-5.0f %-5.0f|"%(a[2],a[3]))
