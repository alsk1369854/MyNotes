//【ChaoCode】 Swift 中級篇 7：Protocol 實作作業 A
// ✨ 請閱讀完整份內容，了解使用情境後，跟著以下步驟設計一個能管理兩種隊伍的 protocol。
// 1️⃣ 請設計一個名為「有優先隊伍」的 protocol。
// ＊ conforms to 這個 protocol 的資料會有兩種隊伍，一種是優先隊伍、另一種是一般隊伍，並且知道要如何判斷什麼樣的資料能進優先隊伍。
// ＊ 這個 protocol 會提供兩種方法：
//      1. 第一種是「add」，它會接收「一個隊伍內容的參數」，並把他新增到合適的隊伍中。
//      2. 第二種是「next」，它不需要任何參數，會回傳一個 optional 的隊伍內容。假如兩個隊伍都是空的就回傳 nil。如果優先隊伍有人，就回傳優先隊伍的第一位，否則回傳一般隊伍的第一位。回傳之前記得刪除隊列內的資料並且印出下一位的資訊。

protocol 有優先隊伍 {
    associatedtype Element
    var priorityQueue: [Element] { get set }
    var generalQuene: [Element] { get set }
    
    static func isPriority(on element:Element) -> Bool
}

extension 有優先隊伍 {
    public mutating func add(_ element:Element) -> Void {
        if (Self.isPriority(on: element)){
            self.priorityQueue.append(element)
        } else {
            self.generalQuene.append(element)
        }
    }
    
    public mutating func next() -> Element? {
        if let next = self.priorityQueue.first {
            print(">> 下一位是優先隊伍的 \(next)")
            return self.priorityQueue.removeFirst()
        }
    
        if let next = self.generalQuene.first {
            print(">> 下一位是一般隊伍的 \(next)")
            return self.generalQuene.removeFirst()
        }
        
        return nil
    }
}



// ✋ 請勿修改玩家和病患這兩個類型。
struct 玩家: CustomStringConvertible {
    var 名字: String
    var 有快速通關: Bool
    var description: String { 名字 }
}

struct 病人: CustomStringConvertible {
    var 名字: String
    var 是急診: Bool
    var description: String { 名字 }
}

// 2️⃣ 請完成以下這兩個 struct，讓他們能 conforms to 你設計的「有優先隊伍」。
// ＊ 遊樂設施中的排隊內容會是玩家，有快速通關的可以進到優先隊伍；診所的排隊內容會是病人，是急診的會進到優先隊伍。
// ＊ 請確保它們都可以不輸入任何參數啟動（不輸入啟動時隊伍都會是空的）。
struct 遊樂設施: 有優先隊伍 {
    var priorityQueue: [玩家] = []
    var generalQuene: [玩家] = []
    
    static func isPriority(on element: 玩家) -> Bool {
        return element.有快速通關
    }
}

struct 診所: 有優先隊伍 {
    var priorityQueue: [病人] = []
    var generalQuene: [病人] = []
    
    static func isPriority(on element: 病人) -> Bool {
        return element.是急診
    }
}

public func runHomeworkA(){
    // 3️⃣ 下面是測試，請勿修改。
    var 大怒神 = 遊樂設施()
    var allPlayer: [玩家] = [.init(名字: "約翰", 有快速通關: false), .init(名字: "馬可", 有快速通關: false), .init(名字: "亞妮", 有快速通關: true), .init(名字: "艾連", 有快速通關: false), .init(名字: "米卡莎", 有快速通關: false), .init(名字: "阿爾敏", 有快速通關: false), .init(名字: "萊納", 有快速通關: true), .init(名字: "柯尼", 有快速通關: false), .init(名字: "莎夏", 有快速通關: false), .init(名字: "貝爾托特", 有快速通關: true), .init(名字: "法蘭茲", 有快速通關: false), .init(名字: "漢娜", 有快速通關: false), .init(名字: "尤米爾", 有快速通關: true), .init(名字: "希斯特莉亞", 有快速通關: true)]

    print("🎢 遊樂園測試...")
    while !allPlayer.isEmpty {
        let nextGroup = allPlayer.prefix(2)
        nextGroup.forEach { _ in 大怒神.add(allPlayer.removeFirst()) }
        
        let _ = 大怒神.next()
    }

    var 皮膚科 = 診所()
    var allPatients: [病人] = [.init(名字: "艾維", 是急診: true),.init(名字: "萊拉", 是急診: false),.init(名字: "泰勒", 是急診: false),.init(名字: "格雷森", 是急診: false),.init(名字: "艾登", 是急診: false),.init(名字: "安娜", 是急診: false),.init(名字: "金斯頓", 是急診: false),.init(名字: "埃莉諾", 是急診: false),.init(名字: "艾莉", 是急診: true),.init(名字: "阿貝爾", 是急診: false),.init(名字: "亞瑟", 是急診: true)]

    print("🏥 看診測試...")
    while !allPatients.isEmpty {
        let nextGroup = allPatients.prefix(2)
        nextGroup.forEach { _ in 皮膚科.add(allPatients.removeFirst()) }
        
        let _ = 皮膚科.next()
    }
}

