def main():
    f_name = 'scores.dat'
    with open(f_name, 'wb+') as file :
        while True :
            name = input()
            if name != 'none' :
                score = input()
                file.write(bytearray('%s %s\n'%(name, score), 'utf-8'))
            else :
                break
        file.seek(0, 0)
        for line in file :
            print(line.decode('utf-8'))

main()
