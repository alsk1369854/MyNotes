def main():
    count = [0]*10
    num = []
    for i in range(10):
        num.append(eval(input()))
        count[num.index(num[i])] += 1
    print('%d'%(num[count.index(max(count))]))
    print('%d'%max(count))
    print(num)
    print(count)

main()
