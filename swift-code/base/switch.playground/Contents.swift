/*
 Switch 必須要包含所有可能的 case
 */

// range條件、fallthrough
let age = 23
switch age {
case ..<18:
    print("未成年")
case 18...:
    print("成年")
    fallthrough // 繼續跳下一個case執行
default:
    print("不知道")
}

// 變數、where條件、與break
let 相親對象 = ("陳葦宸", 162)
switch 相親對象 {
case (let name, 160...) where name.first != "梁":
    print("按排與\(name)相親")
default:
   break
}
