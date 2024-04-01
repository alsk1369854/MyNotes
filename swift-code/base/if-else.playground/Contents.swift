import UIKit


// 分數層級判斷
let score = Int.random(in: 0...100)
if (score > 85){
    print("A")
}else if (score > 60){
    print("B")
}else {
    print("C")
}

// 三元運算
let isMale = true
let sayHello = isMale ? "你好" : "妳好"
