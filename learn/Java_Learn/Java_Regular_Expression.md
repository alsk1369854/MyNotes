# Regular_Expression 正規表達式
> Javascript - Exp

## 1.正規表達式使用三步驟:
### 1) 定義正規表達式
    + 正規表達式定義的兩個方式:
      + 物件型式
        + var reg = new regExp("abc")
      + 直接量型式
        + var reg = /abc/;

    + 匹配模式:
      + g 全局匹配
      + i 忽略大小寫匹配
      + m 多行匹配
      + gim這三個可以組合使用, 不區分先後順序
        + 例如: var reg = /abc/gim, var reg = new RegExp("abc", "gim");
### 2) 定義待檢查的字串
### 3) 檢查

## 2. 元字符
| 代碼 | 說明                                                                |
| ---- | ------------------------------------------------------------------- |
| .    | 匹配除了換行以外的所有字符                                          |
| \w   | 匹配字母或數字或"_"等價於   [a-zA-Z0-9_]                            |
| \W   | 匹配任何非單辭字符。等價於[^a-zA-Z0-9_]                             |
| \s   | 匹配任何空白字符，包括空格、制表符、煥頁符等等。等價於 [\f\n\r\t\v] |
| \S   | 匹配任何非空白字符。等價於[^\f\n\r\t\v]                             |
| \d   | 匹配數字。等價於[0-9]                                               |
| \D   | 匹配非數字字符。等價於[^0-9]                                        |
| \b   | 匹配單辭的開始獲結束                                                |
| ^    | 匹配字符串的開始,但在[]中使用表示取反                               |
| $    | 匹配字符串的結束                                                    |


## 3. 表示集合

| 代碼   | 說明                                |
| ------ | ----------------------------------- |
| [abc]  | 表示 a或者b或者c                    |
| [^abc] | 表示取反, 只要不是a不是b不是c就匹配 |
| [a-c]  | 表示a到c這個範圍匹配                |

## 4. 出現次數

| 代碼  | 說明                 |
| ----- | -------------------- |
| *     | 表示多次 (0 ~ n)     |
| +     | 表示至少一次 ( >= 1) |
| ?     | 最多一次 (0 ~ 1)     |
| {n}   | 出現n次              |
| {n,}  | 出現n次或者多次      |
| {n,m} | 出現n到m次           |


# JavaScript 使用方式
```javascript
// 定義表達式
var reg = /[eo]/

// 定義被檢查字串
var str = "hello world!"


// 檢查 匹配或不匹配
var flag = reg.test(str)
console.log(flag) // output: true

// 匹配字符替換成"A"
str = str.replace(reg, "A")
console.log(str) // output: hAllA wArld!

```

# Java 使用方式
```java
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main{
    public static void main(String[] args){
        // 定義表達式
        String REGEX = "[eo]"; 
        Pattern pattern = Pattern.compile(REGEX);

        // 定義被檢查字串
        String str = "hello world!";

        // 檢查 匹配或不匹配
        Matcher matcher = pattern.matcher(str);
        boolean find = matcher.find();
        System.out.println(find) // output: true

        // 匹配文字替換
        matcher = pattern.matcher(str);
        while(matcher.find()){
            str = replace(matcher.group(), "A");
        }
        System.out.println(str) // output: hAllA wArld!
    }
}

```



