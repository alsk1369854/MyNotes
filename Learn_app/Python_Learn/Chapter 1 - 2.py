import math

def main():
    r = eval(input())
    s = 2 * r * math.sin(math.pi/5)
    area = ((5 * (s**2)) / (4 * math.tan(math.pi/5)) )
    print(area)


main()
