// For loop

let numbers = [256, 4, 512, 2, 128, 8, 1024, 32, 64]

// 實作 numbers.min()
var minNumber = numbers.first!
for  number in numbers[1...] {
    minNumber = min(number, minNumber)
}
numbers.min()
minNumber

// 實作 numbers.firstIndex(of: <#T##Int#>)
var firstIndex = -1
let element = 8
for index in 0..<numbers.count { // 0..<numbers.count 也可以用 numbers.indices 來替換
    if numbers[index] == element {
        firstIndex = index
        break // 跳出迴圈
    }
}
numbers.firstIndex(of: element)
firstIndex


// 打印數組中所有小於 8 的數字
print("打印數組中所有小於 8 的數字")
for number in numbers {
    if number >= 8 { continue } // 直接跳到下一個迴圈任務
    print(number)
}

// 指定要跳出的迴圈
print("指定要跳出的迴圈")
for1: for number1 in 3...5 {
    for number2 in 0...5 {
        if (number1 == number2) {
            break for1 // 跳出的迴圈 for1
        }
        print(number1, number2)
    }
}
