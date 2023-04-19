# Regular expression

## 限定符 (Quantifier)

| 語法     | 解釋       |
| ------ | -------- |
| a*     | a出現0次或多次 |
| a+     | a出現1次或多次 |
| a?     | a出現0次或1次 |
| a{6}   | a出現6次    |
| a{2,6} | a出現2-6次  |
| a{2,}  | a出現兩次以上  |





## 或運算符 (OR Operator)

| 語法         | 解釋       |
| ---------- | -------- |
| (a\|b)     | 匹配a或者b   |
| (ab)\|(cd) | 匹配ab或者cd |





## 字符類 (Character Classes)



| 語法          | 解釋              |
| ----------- | --------------- |
| [abc]       | 匹配a或者b或者c       |
| [a-c]       | 同上              |
| [a-zA-Z0-9] | 匹配小寫+大寫英文字符以及數字 |
| [^0-9]      | 匹配非數字字符         |





## 元字符 (Meta-characters)

| 語法       | 解釋                |
| -------- | ----------------- |
| \d       | 匹配數字字符            |
| \D       | 匹配非數字字符           |
| \w       | 匹配單字字符(英文、數字、下槓線) |
| \W       | 匹配非單字字符           |
| \s       | 匹配空白符(包含換行、Tab)   |
| \s       | 匹配非空白字符           |
| .        | 匹配任意字符(換行符除外)     |
| \bword\b | \b標註字符的邊界(全字匹配)   |
| ^        | 匹配行首              |
| $$       | 匹配行尾              |







## 貪婪/懶惰匹類 (Greedy/ Lazy Match)

| 語法    | 解釋            |
| ----- | ------------- |
| <.+>  | 默認貪婪匹配 "任意字符" |
| <.+?> | 懶惰匹配 "任意字符"   |