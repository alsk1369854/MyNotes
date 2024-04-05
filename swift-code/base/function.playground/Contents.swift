import Foundation


// func 函數名稱(參數...) -> 回傳類型
func getAge(_ birthYear: Int, inYear:Int = 2024) -> Int {
    return inYear - birthYear
}
getAge(1996)
getAge(1996, inYear: 2050)


// 多載、剩餘參數、協定約束
// 常用協定約束 Equatable、Comparable、Sequence、Hashable、Collection
func getSum<T: AdditiveArithmetic>(_ args: [T]) -> T{
    var sum = T.zero
    for item in args {
        sum += item
    }
    return sum
}
func getSum<T: AdditiveArithmetic>(_ args: T...) -> T {
    return getSum(args)
}
getSum(1,2,3,4)
getSum([1,2,3,4])


// 多協定約束(使用 & 連結)
func add<T: Numeric & SignedNumeric & AdditiveArithmetic>(_ num1:T, _ num2:T) -> T {
    return num1 + num2
}
add(1, 2)
add(1.0, 2.0)
add(Decimal(1.0), Decimal(2.0))


// 匿名函數
func printEach<T:Sequence>(_ data:T, _ callback: (T.Element) -> String) {
    for item in data {
        print(callback(item), terminator: "")
    }
}
// 匿名函數傳遞與使用方法 (沒辦法使用協定約束，沒辦法使用預設值)
let data = [1,2,3]
print("\n方法一")
printEach(data, {a in return "\(a), "})

print("\n方法二")
printEach(data, { return "\($0), " })

print("\n方法三")
printEach(data) {
   return "\($0), "
}

print("\n方法四")
let myCallback = { (a:Int) -> String in
    return "\(a), "
}
printEach(data, myCallback)

print("\n方法五")
data.sorted(by: >)

