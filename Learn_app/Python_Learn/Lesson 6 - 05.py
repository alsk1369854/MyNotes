def main():
    num = []
    num_sum = 0
    for i in range(10):
         num.append(eval(input()))
    for i in range(len(num)):
        if num[i] != max(num)or min(num):
             num_sum += num[i]
        
    print(sum(num))
    avge = num_sum/(len(num)-2)
    print(num_sum)
    print('%.2f'%avge)

    total = sum(num) - max(num) - min(num)
    print(total)
    print(total/8)
    print(len(num)-2)
    
main()
