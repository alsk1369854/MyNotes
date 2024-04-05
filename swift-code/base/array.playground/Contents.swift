



var array:Array<String> = ["Ming", "Hang", "JJ"]

// 預留取好一定的記憶體空間
array.reserveCapacity(10)

array.isEmpty
array.count
array.first
array.last
array.startIndex
array.endIndex

array.append("Mark") // 添加到最後
array.insert("Wei", at: 1) // 插入指定位置
array.remove(at: 1) // 刪除指定位置
array.popLast() // 吐出最後一項
array.contains("Ming")
