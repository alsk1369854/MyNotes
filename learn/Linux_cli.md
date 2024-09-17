## 查看檔案夾下的目錄

### 查看目錄下檔案

    展開顯示
    $ ls
    
    條列式顯示
    $ ls -l
    $ ll 【簡寫】 

### 條列式查看目錄下檔案 【最常用】

    1. 詳細數據 (不含隱藏) 
        $ ll -ll
    
    2. 詳細數據 (含隱藏) (除./和../) 
        $ ll -lA
    
    3. 並附上符號說明檔案型別 
        $ ll -lF
        “*”表示可執行的普通檔案；
        “/”表示目錄；
        “@”表示符號連結；
        “|”表示FIFOs；
        “=”表示套

## 路徑切換 【嚴格使用 "/"】

### 磁碟轉換

    $ cd /c
        C:/
    
    $ cd /d
        D:/

### 切換到使用者目錄

    $ cd ~
        C:/Users/ChiaMing

### 前往子孫目錄

    子級目錄
    $ cd subDir
        .../subDir
    
    孫級目錄
    $ cd subDir/subSubDir
        .../subDir/subSubDir

### 返回上層目錄

    回父級目錄
    $ cd ../
    
    回祖父級目錄
    $ cd ../../

## 目錄與檔案處理

### 目錄

    新增目錄
    $ mkdir [目錄名稱]
        ex. mkdir dir
    
    刪除目錄 **包含目錄下所有子檔案**
    $ rm -rvf [目錄名稱]
    
    搬移目錄
    $ mv [目錄名稱] [搬移位置]
        ex. $ mv dir ../ 【將目錄搬至上一層】

### 檔案

    新建檔案    【使用 vim 編輯器】
    $ vim [檔案名稱]    
        ex. $ vim file.txt
        新建檔案後進入檔案編輯
            按下 "i" 進入編輯模式
            按下 "ESC" 進入指令模式
            $ :wq  
                保存退出
            $ :q!
                不保存退出
            $ :set nu 
                顯示行號
    
    刪除檔案
    $ rm [檔案名稱]
        ex. $ rm file.txt
    
    搬移檔案
    $ mv [檔案名稱] [搬移位置]
        ex. $ mv file.txt ../ 【將檔案搬至上一層】
    
    查看檔按內容
    $ cat [檔案名稱]
        ex. $ cat file.txt
    
    查看檔案尾行內容
    $ tail -n [顯示行數] [檔案名稱]
        ex. $ tail -n 3 good.txt 【顯示文件內容倒數3行】

## 網路

    查看ip位址
    $ ifconfig
    
    發送網路封包
    $ ping [網址] -c [封包個數]
        ex. $ ping www.google.com -c 3 【發送3網路封包到 www.google.com】
        ex. $ ping 192.168.0.1 -c 3 【發送3網路封包到 192.168.0.1】
    
    查詢封包路由路徑
    $ tracert [網址]
        ex. $ tracert www.google.com 【追蹤封包發送到 www.google.com 經過的路徑】
        ex. $ tracert 192.168.0.1 【追蹤封包發送到 192.168.0.1 經過的路徑】
    
    重啟網路
    $ service network restart

## 防火牆

    開啟
    $ service firewall start
    
    關閉
    $ service firewall stop
