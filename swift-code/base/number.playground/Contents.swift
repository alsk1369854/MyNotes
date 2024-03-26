import UIKit

// 整數類型
// Int 64bits 預設整數類型
let integar = 1
// 最大值與最小值
Int.max // 9223372036854775807
Int.min // -9223372036854775808
// 可以使用 "_" 分割較長的數值，使其更容易閱讀
999_999_999

// 浮點數類型
// Double 64bits 預設浮點數類型
let double = 0.1
// 會出現精確度問題
print(0.1 + 0.2) // output: "0.30000000000000004\n"
// 最大可顯示數值
Double.greatestFiniteMagnitude

// Float 32bits
let float:Float = 0.1
// 不會出現精度問題？(待研究證實)
print(Float(0.1) + Float(0.2)) // output: "0.3\n"

// Decimal 128bits 精度安全類型，缺點：運算較慢
let decimal:Decimal = 0.1
// 不會出現精度問題
print(Decimal(0.1) + Decimal(0.2)) // output: "0.3\n"
// 使用 string 構造器來創建物件，避免精度錯誤
Decimal(3.24) // 3.240000000000000512
Decimal(string: "3.24") // 3.24

