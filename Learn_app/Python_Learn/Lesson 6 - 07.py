score_list = []
order_list = ['1st', '2nd', '3rd']

for i in range(3):
    print('The %s student:'%order_list[i])
    score_list.append([])
    for j in range(5):
        score_list[i].append(eval(input()))

for i in range(3):
    a = sum(score_list[i])
    print('Student %d'%(i+1))
    print('#Sum ',a)
    print('#Average %.2f'%(a/5))
