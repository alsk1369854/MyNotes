import UIKit


/*
 | 類型              | 符號  | 意思            |
 | ---------------- | ----- | ------------- |
 | Range            | a..<b | a 到小於 b 的所有數字 |
 | ClosedRange      | a...b | a 到 b 的所有數字   |
 | PartialRangeFrom | a...  | 所有等於大於 a 的數字  |
 | PartialRangeFrom | ...a  | 所有小於或等於 a 的數字 |
 | PartialRangeFrom | ..<a  | 所有小於 a 的數字    |
 */


// Range 屬性
let range = 0..<5
range.isEmpty // 是否為空
range.count // 內含數量
range.first // 首位
range.last // 末位
range.lowerBound // 下界
range.upperBound // 上界

