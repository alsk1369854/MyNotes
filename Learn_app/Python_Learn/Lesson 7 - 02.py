tuple1 = ()
tuple2 = ()

print('Create tuple1:')
while True:
    n = eval(input())
    if n == -9999:
        break
    tuple1 += (n,)
    
print('Create tuple2:')
while True:
    n = eval(input())
    if n == -9999:
        break
    tuple2 += (n,)
    
tuple_comb = tuple1 + tuple2

print('Combined tuple before sortion:',tuple_comb)
tuple_list = list(tuple_comb)
tuple_list.sort()
print('Combined list after sorting:',tuple_list)
