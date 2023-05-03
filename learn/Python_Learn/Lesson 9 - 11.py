def main():
    f_name = 'students.dat'
    with open(f_name, 'w') as file :
        while True :
            name = input()
            if name != 'none' :
                score1 = input()
                score2 = input()
                file.write(name)
                file.write(' ')
                file.write(score1)
                file.write(' ')
                file.write(score2)
                file.write('\n')
            else :
                break

main()
