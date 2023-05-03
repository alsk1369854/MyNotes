def main():
    file = open('data.txt', 'a+')
    for i in range(5):
        file.write('\n%s'%(input()))

    file.seek(0,0)

    print('Append completed!')
    print('content of "data.txt"')
    print(file.read())

    file.close()
    

main()
