print('%s \t %s '%('華氏', '攝氏'))
w = 0

while w != 240:
    w += 10
    print('%d \t %.2f'%(w, (w-32)*5/9))
