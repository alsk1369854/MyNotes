a = []

while True:
    b  = eval(input())
    if b != 9999:
        a.append(b)
    else:
        break

print(min(a),)
