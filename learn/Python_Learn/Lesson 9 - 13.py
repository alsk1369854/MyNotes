def main():
    f_name = 'students.dat'
    with open(f_name, 'r') as file :
        for line in file :
            file_list = line.strip('\n').split(' ')
            average = eval(file_list[1])*0.6 + eval(file_list[2])*0.4
            print('|%10s: %.2f|'%(file_list[0], average))

main()
