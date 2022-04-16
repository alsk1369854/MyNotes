def output(aList):
    for i in range(len(aList)):
        aList[i] = eval(input())
    return aList

def MAX(aList):
    max_list = aList[0]
    for i in range(len(aList)):
        if aList[i] > max_list :
            max_list = aList[i]
    return max_list

def MIN(aList):
    min_list = aList[0]
    for i in range(len(aList)):
        if aList[i] < min_list :
            min_list = aList[i]
    return min_list

def main():
    aList = [0]*5
    print(output(aList))
    print('Max =',MAX(aList))
    print('Min =',MIN(aList))
    
main()
