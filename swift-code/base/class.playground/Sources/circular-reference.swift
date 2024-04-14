/*
 youtube:https://www.youtube.com/watch?v=zorK263knsw
 googleDoc:https://tinyurl.com/swift210b
 */
//【ChaoCode】 Swift 中級篇 10 Reference Type & ARC： 實作作業
//
/*: 請閱讀以下的每一組 class，請根據你的判斷調整修改 class，確保不會有「retain cycle」。\
 ⚠️ 作業沒有測試，請自己加入 deinit 確認每個物件都被釋放。答案中有寫「哪裡產生了循環」，請務必也確認這個部分是否跟你想的一樣。
 */

public func runCircularReference(){
    // 1️⃣
    class 教授 {
        var 課程: 課程?
        
        deinit {
            print("professor deinit")
        }
    }
    
    class 課程 {
        // ✨ 因為課程一定有教授，所以使用 unowned 來產生弱連結。
        unowned var 教授: 教授
        
        init(教授: 教授) {
            self.教授 = 教授
            教授.課程 = self
        }
        
        deinit {
            print("course deinit")
        }
    }
    
    let 指導教授: 教授? = 教授()
    var _: 課程? = 課程(教授: 指導教授!)
    
    // 以下兩行執行完後空間應被釋放
//    指導教授 = nil
//    計算機概論 = nil
    
    
    
    // 2️⃣
    class 老闆 {
        var 發薪水: ((Int) -> Void)?
        
        deinit {
            print("boos deinit")
        }
    }
    
    class 帳戶 {
        unowned var 擁有者: 老闆
        var 存款 = 10000
        
        func 扣款(amount: Int) -> Void {
            存款 -= amount
            print("帳戶存款剩下 \(存款)")
        }
        
        init(擁有者: 老闆) {
            self.擁有者 = 擁有者
            擁有者.發薪水 = 扣款
        }
        
        deinit {
            print("account deinit")
        }
    }
    
    let boss: 老闆? = 老闆()
    var _: 帳戶? = 帳戶(擁有者: boss!)
    
    // 以下兩行執行完後空間應被釋放
//    bank = nil
//    boss = nil
    
    
    // 3️⃣
    class 人 {
        var 工作: 工作?
        
        deinit {
            print("person deinit")
        }
    }
    
    class 工作 {
        // ✨ 人可以沒工作，工作也可能沒有負責人，所以用 weak。或者把 人.工作 設成 weak 也沒問題。因為只要其中一端有是弱連結就不會產生 cycle。
        weak var 負責人: 人?
        
        deinit {
            print("work deinit")
        }
    }
    
    let 男孩: 人? = .init()
    let 打工機會: 工作? = .init()
    
    男孩?.工作 = 打工機會
    打工機會?.負責人 = 男孩
    
    // 以下兩行執行完後空間應被釋放
//    男孩 = nil
//    打工機會 = nil

    
}
