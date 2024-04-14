/*
 youtube:https://www.youtube.com/watch?v=tgwSgXzU1ok&list=PLXM8k1EWy5ki_TSdt_Gxd3JRnnaucBiFW&index=12
 gooleDoc:https://docs.google.com/document/d/1xDmu7q5Odk-kLfE5Xj_Vfn_wN9F69r9HMydDJzmL4JI/edit#heading=h.atnvh9uty58e
 */
//【ChaoCode】 Swift 中級篇 11 Class & 繼承： 實作作業
//

public func runExtends(){
    
    
    class 蜘蛛 {
        func 吐絲() {
            print("嘶～～🕸")
        }
        func 注入毒液() {
            print("🧪")
        }
    }
    
    // 1️⃣
    // 請讓蜘蛛人繼承蜘蛛，並新增「name」（String）和「剩餘毒液攻擊次數」（Int）屬性，剩餘毒液攻擊次數預設值為 3。
    // 覆蓋注入毒液方法，首先檢查是否還有剩餘次數，如果沒有次數就印出「\(name)：沒有可用毒液😱」並 return，有的話則扣掉一次攻擊次數，接著印出「\(name)：毒液攻擊！」，再呼叫蜘蛛的注入毒液方法。
    
    class 蜘蛛人: 蜘蛛{
        private(set) var name:String = ""
        var 剩餘毒液攻擊次數:Int = 3
        
        init(name: String){
            self.name = name
        }
        
        override func 注入毒液() {
            guard self.剩餘毒液攻擊次數 > 0 else {
                print("\(name)：沒有可用毒液😱")
                return
            }
            
            self.剩餘毒液攻擊次數 -= 1
            print("\(name)：毒液攻擊！", terminator:"")
            super.注入毒液()
        }
        
        func 補充毒液() {
            print("\(name)補充毒液中...")
            self.剩餘毒液攻擊次數 = 3
        }
    }
    
    // 2️⃣
    // 請讓鋼鐵蜘蛛人繼承蜘蛛人，覆蓋「剩餘毒液攻擊次數」，讓它在 0 的時候自動補充。
    // 請確保此類型無法被繼承。
    final class 鋼鐵蜘蛛人: 蜘蛛人 {
        override var 剩餘毒液攻擊次數:Int {
            get {
                if super.剩餘毒液攻擊次數 == 0 { 補充毒液() }
                return super.剩餘毒液攻擊次數
            }
            set { super.剩餘毒液攻擊次數 = max(0, newValue) }
        }
        
        func 鋼爪攻擊() {
            print("👊 \(name)：鋼爪攻擊！")
        }
    }
    
    // 3️⃣ 請在以下變數中建立一個 collection，並放入一位名為「安德魯」的蜘蛛人和一位名為「湯姆」的鋼鐵蜘蛛人。
    let 蜘蛛人集合:[蜘蛛人] = [蜘蛛人(name: "安德魯"), 鋼鐵蜘蛛人(name: "湯姆")]
    
    // 請勿修改下內容，如「蜘蛛人集合」設定正確應正常執行。
    蜘蛛人集合.forEach {
        $0.吐絲()
        $0.注入毒液()
        $0.注入毒液()
        $0.注入毒液()
        $0.注入毒液()
        print("-----------------")
    }
    
}
