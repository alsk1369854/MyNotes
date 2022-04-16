import math

def nEdge(n, g):
    f = (n*pow(g,2)) / (4*math.tan(math.pi/n))
    return f
    
def main():
    n = eval(input())
    g = eval(input())
    
    print('area =',nEdge(n, g))
    
main()
