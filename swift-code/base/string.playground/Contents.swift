import UIKit


// 字串類型
// String 預設字串類型
let string = "一個 String 宣告"

// 常用功能
"template: \(string)" // 模板字符串
string.count // 長度
string.first // 首字
string.last // 尾字
string.uppercased() // 大寫
string.lowercased() // 小寫
string.isEmpty // 使否為空
string.hasPrefix("一") // 符合前綴
string.hasSuffix("一") // 符合後綴
string.contains("String") // 包含子字串

// unicode
// unicode converter: https://r12a.github.io/app-conversion/
let tw = "🇹🇼"
tw.count
tw.unicodeScalars.count
tw.unicodeScalars.first
tw.unicodeScalars.last
"\u{1F1F9}\u{1F1FC}"

// 多行字串 連接符"\"
"""
這是!
多行字串\
演示
"""

// 特殊符號
// zalgo 文本專換器： https://tw.piliapp.com/cool-text/zalgo-text/
let zalgo = "Z͕͈̘̝̖̾͒͗͒̅ä͓͇͉̩̏̎́̈̾ͅl͍͙̪̠̗͇͕̘͍͖͐͒͛̽̏̓̒̋̀̚g̤͙̪̥͖̠͚̘͚̘͕̎̑̒͐̾̀͒̚o̪̞̳̪͚͐̽̉̎̄͂̾̌̍.͎̦̤͙̟̜̯͍͚͚͛̓̅̅̀ͅͅ.̲̙̬̯̀̍͌̾͒́̑͗͗̀̾.͚͔͇̰̣͍̥̘͇̭͚̍̌́͂̄ͅ"
zalgo.count
zalgo.unicodeScalars.count


// 字串格式化 四捨五入到小數點後第二位
let number = 3.1415926
// 方法一 使用String構造方法
String(format: "%.2f", number)
// 方法二 使用格式化器
let numberFormatter = NumberFormatter() // 實例一個數字格式化器
numberFormatter.minimumFractionDigits = 2 // 設定最小小數點位數
numberFormatter.string(for: number) // 進行格式化
