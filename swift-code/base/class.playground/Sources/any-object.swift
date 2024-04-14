/*
 youtube:https://www.youtube.com/watch?v=xuxd07h9Q50&t=247s
 googleDoc:https://docs.google.com/document/d/1_1eprEPgpgj3_hK64gZ20xCROwnUL_QRksYo15t3i7Q/edit#heading=h.atnvh9uty58e
 */
//【ChaoCode】 Swift 中級篇 13 Class 總結 實作作業
//
// 寫一個 extension，讓所有 conforms to Equatable 的 class 自動比較「連結」來判斷是否相等。

extension Equatable where Self: AnyObject {
    static func == (lhs:Self, rhs:Self) -> Bool { lhs === rhs }
}


public func runAnyObject(){

    // 請勿修改以下內容，執行結果應印出 false & true
    class Cat: Equatable { }
    
    let cat = Cat()
    let cat2 = Cat()
    let cat3 = cat
    print(cat == cat2)
    print(cat == cat3)
}
