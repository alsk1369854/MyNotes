def main():
    f_name = 'friends.dat'
    with open(f_name, 'wb+') as file :
        for i in range(5) :
            inp = bytearray(input() +'\n', 'utf-8')
            file.write(inp)

        file.seek(0, 0)
        for line in file :
            print(line.decode('utf-8'))

main()
