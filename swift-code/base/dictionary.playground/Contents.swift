

// create Dictionary
var dictionary:[String:String] = ["apple":"蘋果"]
//var dictionary:Dictionary<String, String> = [:]
//var dictionary = Dictionary<String, String>()

// 預留取好一定的記憶體空間
dictionary.reserveCapacity(10)

// set element
dictionary["banana"] = "香蕉"
dictionary.updateValue("鳳梨", forKey: "pineapple") // -> return ole element

// get element
dictionary["apple"]
dictionary["strawberry", default: "不存在時的替代物"] // get element else default

// remove
dictionary["banana"] = nil
dictionary.removeValue(forKey: "banana")
//dictionary.removeAll()

dictionary
for (key, value) in dictionary {
    print(key, value)
}
