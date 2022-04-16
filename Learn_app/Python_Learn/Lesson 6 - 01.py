a = []
sum_even = 0
count = 0
for i in range(12):
    a.append(eval(input()))

for i in range(len(a)):
    print('%3d'%a[i],end='')
    count += 1
    if count%3 == 0:
        print()
    if i%2 == 0:
        sum_even += a[i]
print(sum_even)
