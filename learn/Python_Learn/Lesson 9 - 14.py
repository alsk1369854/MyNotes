def main():
    f_name = 'students.dat'
    data = []
    with open(f_name, 'r') as file :
        for line in file :
            inp = line.strip('\n').split(' ')
            inp = (inp[0], eval(inp[1]), eval(inp[2]))
            data.append(inp)

    name = [data[x][0] for x in range(len(data))]
    score1 = [data[x][1] for x in range(len(data))]
    score2 = [data[x][2] for x in range(len(data))]

    print('#1 calculus score is')
    print('%10s: %d'%(name[score1.index(max(score1))], max(score1)))
    
main()
