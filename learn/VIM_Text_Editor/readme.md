# VIM Text Editor
> 一, 一般模式(常用按鍵): 刪除, 複製, 插入
> 
> 二, 編輯模式
>
> 三, 命令模式

## 一, 一般模式(常用按鍵): 刪除, 複製, 插入
| 按鍵       | 功能敘述                                  |
| ---------- | ----------------------------------------- |
| yy         | 複製光標當前行                            |
| y 數字 y   | 複製一段 (從光標行道後n行)                |
| p          | 光標處貼上複製文本                        |
| u          | 回上一步                                  |
| dd         | 刪除光標當前行                            |
| x          | 剪下當前光標字符                          |
| X          | 剪下當前光標(前一個字符)，相當於Backspace |
| yw         | 複製一個詞                                |
| dw         | 刪除一個詞                                |
| shift+^(6) | 移動到行頭                                |
| shift+$(4) | 移動到行尾                                |
| w          | 移動到下一個詞(詞頭位置)                  |
| e          | 動到當前詞尾                              |
| 1G         | 移動到頁頭                                |
| G          | 移動到頁尾                                |
| 數字 G     | 移動到目標行                              |
<br/>

## 二, 編輯模式
### 編輯模式進入方法
```
一般模式下，按下對應按鍵進入編輯模式
```
| 按鍵 | 功能描述       |
| ---- | -------------- |
| i    | 當前光標前     |
| a    | 當前光標後     |
| o    | 當前光標下一行 |
| I    | 光標所在行最前 |
| A    | 光標所在行最後 |
| O    | 當前光標上一行 |
### 退出編輯模式
```
按下 ESC 退出編輯模式，回到一般模式
```
<br/>

## 三, 命令模式
```
在一般模式下輸入指令操作
```
| 命令          | 功能描述                        |
| ------------- | ------------------------------- |
| :w            | 保存                            |
| :q            | 不保存退出                      |
| :wq           | 保存並退出                      |
| :w!           | 強制保存                        |
| :q!           | 強制不保存退出                  |
| :wq!          | 強制保存並退出                  |
| /要查找的詞   | n 找下一個, N 找上一個          |
| :noh          | 取消高亮顯示                    |
| :set nu       | 顯示行號                        |
| :set nonu     | 關閉行號                        |
| :s/old/new    | 替換當前行匹配第一個 old 為 new |
| :s/old/new/g  | 替換當前行匹配所有 old 為 new   |
| :%s/old/new   | 替換所有行匹配第一個 old 為 new |
| :%s/old/new/g | 替換所有行匹配所有old 為 new    |
