def main():
    intfile = open('read.txt', 'r')
    data = intfile.read()
    intfile.close()

    data_list = data.split()
    total = 0
    for i in range(len(data_list)) :
        total += eval(data_list[i])

    print(total)

main()
