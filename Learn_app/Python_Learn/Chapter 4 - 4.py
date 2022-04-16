p = n = m = novalid = 0

for i in range(10):
    print('1: Peter\n2: Nancy\n3: Mary')
    a = eval(input('Which one do you prefer:'))
    if a == 1:
        p += 1
    elif a == 2:
        n += 1
    elif a ==3:
        m += 1
    else:
        novalid += 1
    print('Peter: %d, Nancy: %d, Mary: %d\n'%(p, n, m))
print('\nPeter: %d, Nancy: %d, Mary: %d\nInvalid: %d'%(p, n, m, novalid))
