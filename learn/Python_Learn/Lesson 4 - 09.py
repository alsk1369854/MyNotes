n1 = 0
n2 = 0
null = 0

for i in range(5):
    a = eval(input())
    if a == 1:
        n1 += 1
    elif a ==2:
        n2 += 1
    else:
        null += 1
    print('Total votes of No.1: Nami = %d'%n1)
    print('Total votes of No.2: Chopper = %d'%n2)
    print('Total null votes = %d'%null)

if n1 == n2:
    print('No one won the election.')
elif n1 > n2:
    print('No.1 Nami won the election.')
else:
    print('No.2 Chopper won the election.')
 
