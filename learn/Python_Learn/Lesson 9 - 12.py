def main():
    f_name = 'students.dat'
    with open(f_name, 'r') as file :
        while True :
            line = file.readline()
            if line != '' :
                print(line)
            else :
                break

main()
