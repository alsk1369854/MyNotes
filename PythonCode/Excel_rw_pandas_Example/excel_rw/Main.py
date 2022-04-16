# ../Scripts => cmd => pip install pandas

import pandas as pd
from pandas import DataFrame

if __name__ == '__main__':
    # 讀檔
    pf = pd.read_excel('QQQ歷史資料.xlsx', sheet_name='Sheet1', parse_dates=['日期'])
    
    # 檔案內容
    pf_values = pf.values
    # 內容總共多少 列
    values_len = len(pf_values)
    print('總列: ' + values_len)

    # 日期表
    days = ['五','四','三','二','一']
    days_len = len(days)

    # 設定起始日期 的下一個日期
    next_day_index = 0
    if(days.index(pf_values[0][1]) != days_len-1):
        next_day_index = days.index(pf_values[0][1])+1
    print('初始日期的下一日: ' + next_day_index)

    # 處理完成的資料陣列
    result_values = []
    # 紀錄起始項
    result_values.append(pf_values[0])
    print(result_values[0])
    for i in range (1, values_len):
        print('執行原檔第 ' + i + ' 列')
        # 日期溢位處理
        if (next_day_index == days_len):
            next_day_index = 0
          
        # 當前列資料
        row = pf_values[i]
        # 如果下一項日期對不上, 持續插入上一向數據
        while (days[next_day_index] != row[1]):
            item = result_values[len(result_values)-1].copy()
            item[1] = days[next_day_index]
            # 加入完成的資料陣列
            result_values.append(item)
            next_day_index += 1
            print(result_values[len(result_values)-1])
            
            # 日期溢位處理
            if (next_day_index == days_len):
                next_day_index = 0
        
        # 加入完成的資料陣列
        result_values.append(row)
        next_day_index += 1
        print(result_values[len(result_values)-1])

    # 解果 行 陣列
    date = [] # 日期
    day = [] # 日
    close = [] # 收盤
    open = [] # 開盤
    hight = []
    low = []
    up_down = []
    # 將解果陣列轉換 至對應 行
    for row in result_values:
        date.append(row[0])
        day.append(row[1])
        close.append(row[2])
        open.append(row[3])
        hight.append(row[4])
        low.append(row[5])
        up_down.append(row[6])

    # 設定DataFrame格式
    data = {
        # '日期 星期 收市 開市 高 低 漲跌%'
        '日期':date,
        '星期':day,
        '收市':close,
        '開市':open,
        '高':hight,
        '低':low,
        '漲跌%':up_down
    }
    
    # 寫入 result.xlse 檔案
    df = DataFrame(data)
    df.to_excel('result.xlsx')
