//【ChaoCode】 Swift 中級篇 12 Class & 啟動： 實作作業

public func runInit(){
    
    
    // 請根據下列數字步驟完成練習。
    
    class File {
        var filename: String
        var data: String
        
        
        init(filename: String, data: Any) {
            self.filename = filename
            self.data = String(describing: data)
        }
    }
    
    // 1️⃣ 請讓這個 class 繼承 File，並新增一個「version」的屬性，類型是 Double。預設值是 1。
    class VersionedFile: File {
        var version:Double = 1.0
        
        convenience init(filename: String, data: Any, version: Double) {
            self.init(filename: filename, data: data)
            self.version = version
        }
    }
    
    // 2️⃣ 請讓這個 class 繼承 VersionedFile，並新增一個「author」的屬性，類型是 String?。此類型不會再被繼承。
    final class BookFile: VersionedFile {
        var author:String?
        
        convenience init(filename: String, data: Any, version: Double = 1, author: String) {
            self.init(filename: filename, data: data, version: version)
            self.author = author
        }
    }
    
    // 3️⃣ 請調整 VersionedFile 和 BookFile 的啟動，請只使用「convenience init」，讓以下六種啟動方式都能正常執行（不會報錯即可）。
    let _ = VersionedFile(filename: "Homework", data: 123)
    let _ = VersionedFile(filename: "Homework", data: "abc", version: 2)
    let _ = BookFile(filename: "First Book", data: "")
    let _ = BookFile(filename: "Second Book", data: "", author: "Jane")
    let _ = BookFile(filename: "Second Book", data: 2.0, version: 2, author: "Jane")
    let _ = BookFile(filename: "First Book", data: 3, version: 3)
    
    
}
