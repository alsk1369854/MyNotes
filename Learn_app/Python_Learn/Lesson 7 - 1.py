def operation(set1,set2):
    print()
    print('set1 | set2',(set1|set2))
    print('set1 & set2',(set1&set2))
    print('set1 - set2',(set1-set2))
    print('set1 ^ set2',(set1^set2))

def inputData():
    set1 = set()
    while True:
        n = eval(input())
        if n == -9999:
            return set1
        set1.add(n)

def main():
    print('Input set1 data:')
    set1 = inputData()
    print('Input set2 data:')
    set2 = inputData()

    print('set1',set1)
    print('set2',set2)
    operation(set1,set2)
    
   

main()
