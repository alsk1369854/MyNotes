def totalAndmean():
    a = []
    for i in range(10):
        a.append(eval(input()))
    return(sum(a),sum(a)/10)

def main():
    s, a = totalAndmean()
    print('sum = %.2f, mean = %.2f'%(s, a))

main()
