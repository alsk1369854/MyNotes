def main():
    str1 = input()
    count = 0
    for i in range(len(str1)):
        num = ord(str1[i])
        count += num
        print("ASCII code for '%s' is %d"%(str1[i], num))
    print(count)
    
main()
