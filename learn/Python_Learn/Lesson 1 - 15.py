monthlyDeposit = eval(input('Enter monthly saving amount:'))
a = monthlyDeposit

for i in range(6):
    a = a *(1+ 0.0123/12)
    

print('After the sixth month, the account avlue is %.2f'%a)
