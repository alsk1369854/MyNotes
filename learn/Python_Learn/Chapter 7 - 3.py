def compute():
    set1 = set()
    while True:
        n = eval(input())
        if n == -9999 :
            return set1
        set1.add(n)

def main():
    print('Input set1 data:')
    set1 = compute()
    print('Input set2 data:')
    set2 = compute()

    print('set1',(set1))
    print('set2',(set2))
    print('set1 is a subset of set2: ',(set1.issubset(set2)))
    print('set1 is a superset of set2: ',(set1.issuperset(set2)))
    
main()
