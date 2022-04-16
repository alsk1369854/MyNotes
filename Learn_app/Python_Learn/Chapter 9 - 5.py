def main():
    score = []
    f_name = 'scores.dat'
    with open(f_name, 'rb') as file :
        for line in file :
            inp = line.decode('utf-8').strip('\n').split(' ')
            score.append(eval(inp[1]))

    print(score)
    print('average score : %.2f'%(sum(score)/len(score)))   
            

main()
