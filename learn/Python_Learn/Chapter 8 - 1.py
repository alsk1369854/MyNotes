def main():
    str1 = ''
    while True :
        time = input()
        if time != 'end' :
            time = time.split(':')
            print('hour: '+ time[0] +' min: '+ time[1] +' second: '+ time[2])
        else :
            break
main()
