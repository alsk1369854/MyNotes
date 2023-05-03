def distance(x1,y1,x2,y2):
    dist = ((x1-x2)**2+(y1-y2)**2)**0.5
    print('The distance of ((%d, %d), (%d, %d)) = %.2f'%(x1,y1,x2,y2,dist))

def main():
    x1, y1 = eval(input())
    x2, y2 = eval(input())
    distance(x1,y1,x2,y2)

main()
