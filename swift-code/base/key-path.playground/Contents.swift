/*
 youtube:https://www.youtube.com/watch?v=QkF3_gnh8Dg&list=PLXM8k1EWy5ki_TSdt_Gxd3JRnnaucBiFW&index=9
 googleDoc:https://docs.google.com/document/d/17tuXjB9D59p4ojHt4t13QGPn7cCJJTe1WDRTd8jvvXE/edit#heading=h.atnvh9uty58e
 */
//【ChaoCode】 Swift 中級篇 8：KeyPath 實作作業
//: 1. 在 Array 的 extension 中寫一個能把所有資料的某個 Double 屬性加總後算出平均值的方法。
extension Array {
    func average(_ keyPath:KeyPath<Element, Double>) -> Double {
        let total = self.reduce(0, { total, element in total + element[keyPath: keyPath] })
        return total / Double(self.count)
    }
}

// ✨ 以下測試請自行完成，前面是 Array，然後是要計算哪個屬性的平均，最後 == 後面是預期結果，你只需要把中間文字的部分改成你設計的方法並放入 keyPath，然後確認兩邊相比結果是 true 即可

// [100, 60, 5.0] 平均 == 55 // 這個就是平均數字本身
// [長度單位(m: 3), 長度單位(m: 0.23), 長度單位(m: 935), 長度單位(m: 1130)] 公尺屬性平均 ==  517.0575
// [長度單位(m: 23), 長度單位(m: 32.311), 長度單位(m: 935), 長度單位(m: 113.0)] 公分屬性平均 == 27582.775
// [長度單位(m: 9), 長度單位(m: 12321), 長度單位(m: 935), 長度單位(m: 1.130)] 公里屬性平均 == 3.3165325
[100, 60, 5.0].average(\.self) == 55
[長度單位(m: 3), 長度單位(m: 0.23), 長度單位(m: 935), 長度單位(m: 1130)].average(\.公尺) ==  517.0575
[長度單位(m: 23), 長度單位(m: 32.311), 長度單位(m: 935), 長度單位(m: 113.0)].average(\.公分) == 27582.775
[長度單位(m: 9), 長度單位(m: 12321), 長度單位(m: 935), 長度單位(m: 1.130)].average(\.公里) == 3.3165325



/*: 2. 在 Sequence 的 extension 中寫一個透過某個屬性分類後回傳字典的方法。
 ```
 假設一筆資料是 [(name: "小芳", 性別: "女"), (name: "偉偉", 性別: "男"), (name: "芯宜", 性別: "女")]
 這筆資料用「性別」分類的話，回傳的字典結果就會是
 ["女": [(name: "小芳", 性別: "女"), (name: "芯宜", 性別: "女")], "男": [(name: "偉偉", 性別: "男")]]
 ```
 */
extension Sequence {
    func groupBy<V:Hashable>(_ keyPath:KeyPath<Element, V>) -> [V:[Element]] {
        var result:[V:[Element]] = [:]
        self.forEach { (element) in
            let key = element[keyPath: keyPath]
            result[key, default:[]].append(element)
        }
        return result
    }
    
}

// ✨ 請使用以下兩個變數進行分類，並印出回傳的字典。
// spends 請分別印出用「付款方式」和用「類別」分類的方式
spends.groupBy(\.付款方式)
spends.groupBy(\.類別)

// movies 請用「類型」分類
movies.groupBy(\.類型)


