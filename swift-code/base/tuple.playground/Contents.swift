import UIKit


// 宣告一個 Tuple 類型
let tuple = ("item1", "item2")
tuple.0


// 此用 Tuple 宣告一個類型別名
typealias Human = (name:String, age:Int)
let person:Human = (name:"Ming", age:27)
person.name


