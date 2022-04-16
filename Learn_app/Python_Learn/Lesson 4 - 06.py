state = ''
height = eval(input())
while height != -9999:
    weight = eval(input())
    bmi = weight/pow((height/100), 2)
    if bmi > 30:
        state = 'fat'
    elif bmi >= 25:
        state = 'over weight'
    elif bmi >= 18.5:
        state = 'normal'
    else:
        state = 'number weight'

    print('BMI: %.2f'%(bmi))
    print('State: %s'%(state))
    height = eval(input())
