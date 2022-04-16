def main():
    f_name = input()
    str_old = input()
    str_new = input()

    file = open(f_name, 'r+')
    data = file.read()
    print('=== Before the deletion')
    print(data)

    print('\n=== After the deletion')
    new_data = data.replace(str_old, str_new)
    print(new_data)

    #file.seek(0, 0)
    #file.truncate()
    #file.write(data)
    #file.close

main()
