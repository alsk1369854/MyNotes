def bmi(h, w):
    f = w / ((h/100)**2)
    if f >= 30 :
        return 'Fat'
    elif f >= 25 :
        return 'Over weight'
    elif f >= 18.5 :
        return 'Normal'
    else:
        return 'unber weight'

def main():
    h = eval(input())
    w = eval(input())
    print('Your bmi is',bmi(h, w))

main()
