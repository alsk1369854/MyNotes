def main():
    data = []
    file = open('read.txt', 'r')

    for line in file :
        print(line)
        tmp = line.split()
        tmp = [tmp[0], eval(tmp[1]), eval(tmp[2])]
        data.append(tmp)

    print(data)
    name = [data[x][0] for x in range(len(data))]
    height = [data[x][1] for x in range(len(data))]
    weight = [data[x][2] for x in range(len(data))]

    print((sum(height)/len(height)))
    print((sum(weight)/len(weight)))

    print('%s, %.2f'%(name[height.index(max(height))], max(height)))
    print((name[weight.index(max(weight))]), (max(weight)))
    
main()
