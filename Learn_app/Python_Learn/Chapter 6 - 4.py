import math
def meanAndsd(alst):
    total = 0
    mean = sum(alst)/len(alst)
    for i in range(len(alst)):
        total += pow(alst[i]-mean, 2)
    sd = math.sqrt(total/(len(alst)-1))
    return mean, sd
    
def main():
    alst = []
    for i in range(10):
        alst.append(eval(input()))

    m, s = meanAndsd(alst)
    print(alst)
    print('Mean = %.2f, Standard deviation = %.2f'%(m, s))
    

main()
