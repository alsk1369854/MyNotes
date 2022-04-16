grade = ""

score = eval(input())
while score != -9999:
    if score >= 90:
        grade = 'A'
        print('A')
    elif score >= 80:
        grade = 'B'
    elif score >= 70:
        grade = 'C'
    elif score >= 60:
        grade = 'D'
    else:
        grade = 'E'
    print(grade,)
    score = eval(input())
    
