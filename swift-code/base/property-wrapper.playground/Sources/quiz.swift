//⚠️ 請用 shift + commend + option + V 貼上才能維持文件的縮排。
//💡 在 playground 中，從上方選單選 Editor > Show Rendered Markup 可以讓題目看起來更清楚一些。
/*:
### 【ChaoCode】 Swift 中級篇 3：Property Wrapper 作業
 ---
1. 設定一個名為 Trimmed 的屬性包裝，功能是把文字的前後空白或換行移除。
    * 限制：wrappedValue 需為儲存屬性（Stored Property）。
    * 💡 你可以使用 String 裡的 .trimmingCharacters 方法。
 ---
*/
import Foundation

@propertyWrapper
struct Trimmed {
    var wrappedValue:String {
        didSet {
            self.wrappedValue = self.wrappedValue.trimmingCharacters(in: .whitespacesAndNewlines)
        }
    }
}

/*:
---
2. 設定一個名為 Log 的屬性包裝，功能是在每次存取或是更改時印出通知。
    * 可搭配任何屬性使用，需要提供一個名為「描述」的 String 參數。
    * 在存取時印出「🔍 存取\(描述)...」
    * 在修改時印出「✏️ \(描述)的值改為 ＯＯＯ」
    * 請定義 init 啟動方法，請勿修改下方提供的參數名稱和引數名稱。
 ---
*/
@propertyWrapper
struct Log<T> {
    
    private var value:T
    private var 描述:String
    var wrappedValue:T{
        get{
            print("🔍 存取\(描述)...")
            return self.value
        }
        set(newValue){
            print("✏️ \(描述)的值改為 \(newValue)")
            self.value = newValue
        }
    }
    
    init(wrappedValue: T, 描述: String) {
        self.value = wrappedValue
        self.描述 = 描述
    }
}



/*:
 ---
 3. 設定一個名為 Percentage 的屬性包裝，功能是把 Double 數字，透過 projectedValue 用百分比的文字顯示。
    - 需要提供一個名為「小數點位數」的 Int 參數，預設為 2，以該位數四捨五入後顯示，小數點位數不應小於 2。
        ```
         例如：0.18532 小數點位數小於 2 位數都應顯示 18%；小數點位數 3 位應顯示 18.5%。
        ```
    - 請使用 NumberFormatter 或 .formatted 來處理進位和文字轉換。
    - 使用 swift 自動產生的啟動，不自行定義 init。並確保以下兩種提供 wrappedValue 的方式都能執行。
        ```
        @Percentage(小數點位數: 3) var a = 0.2231
        @Percentage(wrappedValue: 0.338, 小數點位數: 3) var b
        ```
 ---
 */

@propertyWrapper
struct Percentage {
    var wrappedValue:Double
    var projectedValue:String {
        get {
            let digits = max(0, 小數點位數 - 2)
                let formatter = NumberFormatter()
                formatter.maximumFractionDigits = digits
                formatter.minimumFractionDigits = digits
                formatter.roundingMode = .halfUp
                let string = formatter.string(from: (wrappedValue * 100) as NSNumber)! + "%"
            
            return string
        }
    }
    
    private(set) var 小數點位數 = 2
}



// ⚠️ 這次的測試需要跟你的 property wrapper 在同一個檔案中。請自行把要測試的 code 貼回你的作業下方測試。你要全放到最下方或是一個一個單獨測試都可以。
// ⚠️ 每個 struct 代表一個測試，直接呼叫他的靜態方法 .check() 即可檢查。

// 1️⃣
struct TrimTest {
    @Trimmed var input: String

    static func check() {
        let testCases: [(test: String, answer: String)] = [
            ("\n \t  Hello, \nWorld  \n", "Hello, \nWorld"),
            (" 　你好世界。", "你好世界。"),
            ("Hello~~", "Hello~~"),
            ("         \n\n\n\n", ""),
            ("🐶     ", "🐶"),
        ]
        for (string, answer) in testCases {
            let testCase = TrimTest(input: string)
            guard testCase.input == answer  else {
                print("❌「\(string)」應該被儲存為「\(answer)」，但您的結果為「\(testCase.input)」")
                return
            }
        }

        var trimmed = TrimTest(input: "")
        for (string, answer) in testCases {
            trimmed.input = string
            guard trimmed.input == answer  else {
                print("❌「\(string)」應該被儲存為「\(answer)」，但您的結果為「\(trimmed.input)」")
                return
            }
        }

        print("✅ 您的 Trimmed 屬性包裝沒有問題。")
    }
}
//
//
// 2️⃣
struct LogTest {
    static func check() {
        print("⚠️ 請自行比對以下 print 內容是否正確，如果儲存的值沒有修改會報錯。")
        
        let answer1 = Date(timeIntervalSince1970: 1)
        print("測試 1：應印出 \(answer1)")
        let test1 = LogTestModel(description: "日期", from: Date.init(), to: answer1)
        assert(test1.data == answer1, "⚠️ set 沒有正確修改到儲存的值哦")
        
        let answer2 = (802, "苓雅區")
        print("測試 2：應印出 \(answer2)")
        let test2 = LogTestModel(description: "郵遞區號", from: (100, "中正區"), to: answer2)
        assert(test2.data == answer2, "⚠️ set 沒有正確修改到儲存的值哦")
    }

    struct LogTestModel<T> {
        @Log var data: T
        var secondValue: T

        init(description: String, from data: T, to secondValue: T) {
            _data = .init(wrappedValue: data, 描述: description)
            self.secondValue = secondValue
            self.data = secondValue
        }
    }
}
//
//
// 3️⃣
struct PercentTest {
    @Percentage var defaultDigitTest: Double
    @Percentage var customDigitTest: Double

    init(number: Double, digits: Int) {
        self.defaultDigitTest = number
        self._customDigitTest = .init(wrappedValue: number, 小數點位數: digits)
    }

    static func check() {
        let testCases: [(number: Double, digits: Int, defaultAnswer: String, customAnswer: String)] = [
            // 測整數
            (1, 2, "100%", "100%"),
            (2, 2, "200%", "200%"),
            (-1, 2, "-100%", "-100%"),
            (1.999, 2, "200%", "200%"),
            // 測小數四捨五入
            (0.2345, 3, "23%", "23.5%"),
            (0.8712, 3, "87%", "87.1%"),
            (0, 3, "0%", "0.0%"),
            (0.1234, 4, "12%", "12.34%"),
            (0.12345, 4, "12%", "12.35%"),
            (-0.123, 4, "-12%", "-12.30%"),
            (-0.09756, 4, "-10%", "-9.76%"),
            // 測小數位數小於 2
            (-1, 1, "-100%", "-100%"),
            (0, 0, "0%", "0%"),
            (-0.881, -2, "-88%", "-88%"),
            (0.7787, -3, "78%", "78%"),
        ]

        for (number, digits, defaultAnswer, customAnswer) in testCases {
            let testCase = PercentTest(number: number, digits: digits)
            guard testCase.$defaultDigitTest == defaultAnswer else {
                print("❌ 兩位小數的 \(number) 應顯示為 \(defaultAnswer)，而您的結果是「\(testCase.$defaultDigitTest)」")
                return
            }
            guard testCase.$customDigitTest == customAnswer else {
                print("❌ \(digits) 位小數的 \(number) 應顯示為 \(customAnswer)，而您的結果是「\(testCase.$customDigitTest)」")
                return
            }
        }

        print("✅ 您的 Percentage 屬性包裝沒有問題。")
    }
}
//
//
// 👇 下面這幾行會執行測試。
TrimTest.check()
print("-------------------")
LogTest.check()
print("-------------------")
PercentTest.check()
