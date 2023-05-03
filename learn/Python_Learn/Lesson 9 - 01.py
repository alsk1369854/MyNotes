def main():
    outfile = open('write.txt', 'w')
    for i in range(5):
        data = input()
        outfile.write(data + '\n')

    outfile.close()

main()
