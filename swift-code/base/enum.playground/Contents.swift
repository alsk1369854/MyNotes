// youtube:https://www.youtube.com/watch?v=hRJw83s6bIg&list=PLXM8k1EWy5ki_TSdt_Gxd3JRnnaucBiFW&index=4
// enum 宣告
// 1. 可在 enum Name: 後方第一個位置，指定此 RawRepresentable 協定的 rawValue 類型
// 2. 遵守 CaseIterable 協定，使 enum 是可以被 loop 的，(調用方法 Enum.allCases)

//⚠️ 請用 shift + commend + option + V 貼上才能維持文件的縮排。
//💡 在 playground 中，從上方選單選 Editor > Show Rendered Markup 可以讓題目看起來更清楚一些。
/*:### 【ChaoCode】 Swift 中級 4：Enum 實作作業
 ---
 1. 建立一個名為「感情狀態」的 enum。
 * 一共有五種選項：單身、穩定交往中、已婚、開放式關係、一言難盡。
 * 穩定交往和結婚需要輸入伴侶名字。
 * 調整這個類型被印出來時顯示的文字，如果是穩定交往或是已婚需要顯示對象。
 ```
 例如：和小白穩定交往中。
 ```
 ---
 */

enum 感情狀態:CustomStringConvertible {
    case 單身, 一言難盡, 開放式關係, 已婚(伴侶:String = "某某"), 穩定交往中(伴侶:String = "某某")
    
    var description: String {
        switch self{
        case .單身:
            return "單身"
        case .一言難盡:
            return "一言難盡"
        case .開放式關係:
            return "開放式關係"
        case .已婚(let 伴侶):
            return "與 \(伴侶) 結婚"
        case .穩定交往中(let 伴侶):
            return "與 \(伴侶) 穩定交往中"
        }
    }
}


// 👇 請勿刪除下面的 print，你需要讓它們可以正常執行，請自行確認結果是否如同預期。
print(感情狀態.單身)
print(感情狀態.一言難盡)
print(感情狀態.開放式關係)
print(感情狀態.已婚())
print(感情狀態.穩定交往中(伴侶: "哈利"))

/*:
 ---
 2. 請根據下列需求設計以下兩個 enum 和一個 struct。
 * 讓 Card 根據大老二的規則比大小（Comparable）。\
 ```- 先比數字大小，數字一樣時再比花色。```\
 ```- 數字大小 2 > ace > king> queen> jack > 10, 9, 8, 7, 6, 5, 4, 3```\
 ```- 同數字時比較花色，黑桃 > 紅心 > 方塊 > 梅花```
 * 讓 Card 被印出來時印出花色表情 + 全形文字。對印文字如下：\
 ```花色：黑桃 ♠️、紅心 ♥️、方塊 ♦️、梅花 ♣️```\
 ```數字：Ａ、２、３、４、５、６、７、８、９、１０、Ｊ、Ｑ、Ｋ```
 * 請勿修改 case 名稱（你可以調整順序）和屬性名稱，也不要增加自訂的啟動方式。
 ```
 例如：紅心 12 應印出♥️Ｑ
 ```
 ---
 */


// enum 宣告後可在:後方第一個位置，指定此 RawRepresentable 協定的 rawValue 類型
//
enum 卡牌花色:Int, Comparable, CaseIterable {
    case 梅花, 方塊, 紅心, 黑桃
    
    var emoji: String {
        switch self {
        case .黑桃:
            return "♠️"
        case .紅心:
            return "♥️"
        case .方塊:
            return "♦️"
        case .梅花:
            return "♣️"
        }
    }
    
    static func < (lhs: 卡牌花色, rhs: 卡牌花色) -> Bool {
        return lhs.rawValue < rhs.rawValue
    }
}

enum 卡牌數字:Int, Comparable, CustomStringConvertible {

    case ace = 11, two = 12, three = 0, four = 1, five = 2, six = 3, seven = 4, eight = 5, nine = 6, ten = 7, jack = 8, queen = 9, king = 10

    var description: String {
        switch self{
        case .ace:
            return "Ａ"
        case .two:
            return "２"
        case .three:
            return "３"
        case .four:
            return "４"
        case .five:
            return "５"
        case .six:
            return "６"
        case .seven:
            return "７"
        case .eight:
            return "８"
        case .nine:
            return "９"
        case .ten:
            return "１０"
        case .jack:
            return "Ｊ"
        case .queen:
            return "Ｑ"
        case .king:
            return "Ｋ"
        }
    }
    
    static func < (lhs: 卡牌數字, rhs: 卡牌數字) -> Bool {
        return lhs.rawValue < rhs.rawValue
    }
}

struct Card:Comparable & CustomStringConvertible{
    var 花色: 卡牌花色
    var 數字: 卡牌數字
    
    var description: String {
        return "\(self.花色.emoji)\(self.數字)"
    }
    
    static func < (lhs: Card, rhs: Card) -> Bool {
        if (lhs.數字 == rhs.數字) {
            return lhs.花色 < rhs.花色
        }
        return lhs.數字 < rhs.數字
    }
    
}

let caseList = 卡牌花色.allCases


// Test
func testCard() {
    let testCases = [(Card(花色: .紅心, 數字: .ace), Card(花色: .黑桃, 數字: .nine), true, "♥️Ａ"),
                     (Card(花色: .梅花, 數字: .two), Card(花色: .梅花, 數字: .queen), true, "♣️２"),
                     (Card(花色: .梅花, 數字: .ace), Card(花色: .梅花, 數字: .three), true, "♣️Ａ"),
                     (Card(花色: .黑桃, 數字: .ten), Card(花色: .黑桃, 數字: .nine), true, "♠️１０"),
                     (Card(花色: .方塊, 數字: .queen), Card(花色: .黑桃, 數字: .ten), true, "♦️Ｑ"),
                     (Card(花色: .梅花, 數字: .king), Card(花色: .紅心, 數字: .king), false, "♣️Ｋ"),
                     (Card(花色: .紅心, 數字: .two), Card(花色: .紅心, 數字: .king), true, "♥️２"),
                     (Card(花色: .梅花, 數字: .six), Card(花色: .梅花, 數字: .ace), false, "♣️６"),
                     (Card(花色: .方塊, 數字: .six), Card(花色: .黑桃, 數字: .two), false, "♦️６"),
                     (Card(花色: .紅心, 數字: .three), Card(花色: .梅花, 數字: .seven), false, "♥️３"),
                     (Card(花色: .紅心, 數字: .five), Card(花色: .黑桃, 數字: .seven), false, "♥️５"),
                     (Card(花色: .梅花, 數字: .ace), Card(花色: .紅心, 數字: .three), true, "♣️Ａ"),
                     (Card(花色: .梅花, 數字: .five), Card(花色: .紅心, 數字: .five), false, "♣️５"),
                     (Card(花色: .方塊, 數字: .king), Card(花色: .黑桃, 數字: .four), true, "♦️Ｋ"),
                     (Card(花色: .梅花, 數字: .ace), Card(花色: .黑桃, 數字: .queen), true, "♣️Ａ"),
                     (Card(花色: .黑桃, 數字: .seven), Card(花色: .方塊, 數字: .seven), true, "♠️７"),
                     (Card(花色: .紅心, 數字: .jack), Card(花色: .梅花, 數字: .eight), true, "♥️Ｊ"),
                     (Card(花色: .方塊, 數字: .jack), Card(花色: .方塊, 數字: .queen), false, "♦️Ｊ"),
                     (Card(花色: .梅花, 數字: .jack), Card(花色: .紅心, 數字: .four), true, "♣️Ｊ")]
    for testCase in testCases {
        if String(describing: testCase.0) != testCase.3 {
            print("❌ 您印出的是\(String(describing: testCase.0))，應印出 \(testCase.3)")
            return
        }
    }
    
    
    for test in testCases {
        if (test.0 > test.1) != test.2 {
            let answer = test.2 ? "大於" : "小於"
            print("❌ \(test.0) 應\(answer) \(test.1)")
            return
        }
    }
    print("✅ 您的卡牌設計沒有問題。")
}
testCard()
