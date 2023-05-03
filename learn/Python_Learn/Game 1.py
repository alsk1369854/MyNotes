import random
def main():
    
    while True :
        block = []
        row = []
        column = []
    
        for i in range(9) :
            block.append([])
            row.append([])
            column.append([])

        for i in range(9) :    
            count1 = 0
            while count1 < 9 :
                num = random.randint(1, 9)
                if num not in block[i] :
                    block[i].append(num)
                    count1 += 1

        count = -1
        for i in range(0, 7, 3) :
            for k in range(3) :
                count += 1
                for x in range(3) :
                    row[i].append(block[count][x])
        count = -1
        for i in range(1, 8, 3) :
            for k in range(3) :
                count += 1
                for x in range(3, 6) :
                    row[i].append(block[count][x])
        count = -1
        for i in range(2, 9, 3) :
            for k in range(3) :
                count += 1
                for x in range(6, 9) :
                    row[i].append(block[count][x])

        
        for i in range(0, 7, 3) :
            for k in range(0, 7, 3) :
                
                for x in range(0, 7, 3) :
                    column[i].append(block[k][x])
        
        for i in range(1, 8, 3) :
            for k in range(1, 8, 3) :
                
                for x in range(1, 8, 3) :
                    column[i].append(block[k][x])
        
        for i in range(2, 9, 3) :
            for k in range(2, 9, 3) :
                
                for x in range(2, 9, 3) :
                    column[i].append(block[k][x])
                    
        cheak = True
        count = 0
        while count < 9 :
            if sum(row[count]) != 45 or sum(column[count]) != 45:
                cheak = False
            count += 1
        print('======================')
        print('----------------------')
        count = 0
        for i in range(9) :
            print('|',end = '')
            for x in range(9) :
                
                print(row[i][x],end = ' ')
                count += 1
                if count%3 == 0 :
                    print(end = '|')
                if count%9 == 0 :
                    print()
                if count%27 == 0 :
                    print('----------------------')
        print('======================')
        break
        
        if cheak :
            print('========================')
            print()
            count = 0
            for i in range(9) :
                for x in range(9) :
                    print(row[i][x],end = '')
                    count += 1
                    if count%3 == 0 :
                        print(end = ' ')
                    if count%9 == 0 :
                        print()
                    if count%27 == 0 :
                        print()
            print('========================')  

            break




        
main()
