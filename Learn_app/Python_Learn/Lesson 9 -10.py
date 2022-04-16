def main():
    f_name = 'data.dat'

    c_male = c_female = 0
    with open(f_name, 'rb') as file :
        for line in file :
            row = line.decode('utf-8')
            print(row)
            row = row.strip('\n').split(' ')

            if row[2] == '1' :
                c_male += 1
            elif row[2] == '0':
                c_female += 1

    print('Man: ',c_male)
    print('Woman: ',c_female)

main()
