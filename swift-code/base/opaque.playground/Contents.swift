/*
 youtube:https://www.youtube.com/watch?v=21bRqauym3k&list=PLXM8k1EWy5ki_TSdt_Gxd3JRnnaucBiFW&index=9
 googleDoc:https://docs.google.com/document/d/1yZBdu_rqxki1POjvV9syFM_g-jYLEnJxEoMoqNKigaE/edit
 */
//【ChaoCode】 Swift 中級篇 9 Opaque： 實作作業
//
//: 1. 請把下面這個變數類型變成一個 Opaque 的類型，修改完之後依然可以 loop 自己的 index。
// 👇 請改這變數的「類型定義」就好

let 某種資料: some Collection = [String()].dropFirst().reversed()
某種資料.indices.forEach { _ in } // 這行只是用來檢查你改完之後是否還能 loop 自己的 index


//: 2. 請調整下面的資料，讓最後在操作「呼叫狗狗」和「呼叫貓咪」的時候，只公開他們的名字、打招呼和玩的方法，同時確保「帶大家結紮」依然能正常執行。

protocol Pet {
    var 名字: String { get }
    
    func 打招呼() -> Void
    func 玩() -> Void
}

struct 狗狗:Pet {
    var 名字: String
    var 完成狗狗疫苗接種: Bool
    var 已結紮: Bool
    
    func 打招呼() {
        print("汪汪～")
    }
    func 玩() {
        print("翻肚子🐶")
    }
}

struct 貓咪:Pet {
    var 名字: String
    var 完成貓咪疫苗接種: Bool
    var 已結紮: Bool
    
    func 打招呼() {
        print("喵～")
    }
    func 玩() {
        print("征服逗貓棒😼")
    }
}

struct 動物咖啡店 {
    private var 狗狗們: [狗狗] = [.init(名字: "小乖", 完成狗狗疫苗接種: true, 已結紮: false), .init(名字: "皮皮", 完成狗狗疫苗接種: true, 已結紮: true)]
    private var 貓咪們: [貓咪] = [.init(名字: "蛋蛋", 完成貓咪疫苗接種: true, 已結紮: true), .init(名字: "布丁", 完成貓咪疫苗接種: true, 已結紮: false)]
    
    func 呼叫狗狗()  -> some Pet {
        let dog = 狗狗們.randomElement()!
        print("✨ \(dog.名字) 來了")
        return dog
    }
    
    func 呼叫貓咪()  -> some Pet {
        let cat = 貓咪們.randomElement()!
        print("✨ \(cat.名字) 來了")
        return cat
    }
    
    
    mutating func 帶大家結紮() {
        // 請勿修改這個方法
        狗狗們.indices.forEach { 狗狗們[$0].已結紮 = true }
        貓咪們.indices.forEach { 貓咪們[$0].已結紮 = true }
        print("👍 大家都結紮好了")
    }
}

// ⚠️ 請勿修改下面的 code，請修改完上方的資料後請確認以下內容依然正常執行，並且自行檢查操作「cat」和「dog」時是否只有「名字、玩、打招呼」這三個公開屬性/方法。

var 咖啡店 =  動物咖啡店()
咖啡店.帶大家結紮()

let cat = 咖啡店.呼叫貓咪()
cat.打招呼()
cat.玩()

var dog = 咖啡店.呼叫狗狗()
dog.打招呼()
dog.玩()

