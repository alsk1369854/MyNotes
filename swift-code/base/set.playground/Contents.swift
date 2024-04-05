
// Set<T>
var set:Set<String> = Set(["banana"])

// 預留取好一定的記憶體空間
set.reserveCapacity(10)

set.insert("apple") // 新增
set.remove("banana") // 刪除
set.removeAll() // 刪除全部

// 集合運算
let demoSet1 = Set<String>(["apple", "banana", "strawberry"])
let demoSet2 = Set<String>(["apple", "banana", "pineapple"])

// 聯集
demoSet1.union(demoSet2)
// 交集
demoSet1.intersection(demoSet2)
// 刪去(差集)
demoSet1.subtracting(demoSet2)
// 對稱差集(兩邊都沒有的項目)
demoSet1.symmetricDifference(demoSet2)
