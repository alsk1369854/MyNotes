def main():
    f_name = 'data.dat'

    file = open(f_name, 'wb')
    for i in range(6):
        inp = bytearray(input() + '\n', 'utf-8')
        file.write(inp)
    file.close()

    file = open(f_name, 'rb')
    for line in file :
        print(line.decode('utf-8'))
    file.close()

main()
