def gpa(a):
    if a >= 90 :
        return 'A'
    elif a >= 80 :
        return 'B'
    elif a >= 70 :
        return 'C'
    elif a >= 60 :
        return 'D'
    else:
        return 'E'

def main():
    score = eval(input())
    print('Your gpa is',gpa(score))

main()
