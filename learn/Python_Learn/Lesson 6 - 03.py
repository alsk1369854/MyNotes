def main():
    num = []
    for i in range(10):
        num.append(eval(input()))
    num.sort()
    print(num[-1],num[-2],num[-3])

main()
