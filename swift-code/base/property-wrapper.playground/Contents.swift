import Foundation


/*
 Property wrapper 寫法
 1. 建立一個 strcut 並加上 @propertyWrapper 修飾
 2. 必須要有一個 wrappedValue 屬性、至少可以 get 的屬性
 3. 可加上 projectedValue 投射任何屬性或本生
 
 限制
 1. 被裝飾的屬性不能是計算屬性
 2. 被裝飾的屬性不能是全域屬性
 3. 被裝飾的屬性不能是 lazy、weak、unowned
 4. PropertyWrapper 類型本身和 wrappedValue、projectedValue 必須要有同樣的 access control level
 */
@propertyWrapper
struct ChangeLog<T>{
    var wrappedValue:T{
        didSet{
            print("\(self.valueName) change to \(self.wrappedValue)")
        }
    }
    var projectedValue:Self {self}
    
    private(set) var valueName:String
}

struct Accounts {
    @ChangeLog(valueName: "title") var title:String = ""
    @ChangeLog var cost:Int
    
    init(title:String, cost:Int){
        self.title = title
        // 使用 _valueName 來設定屬性的包裝
        self._cost = ChangeLog(wrappedValue: cost, valueName:"cost")
    }
}
var accounts = Accounts(title: "development", cost: 100)
accounts.title = "dev"
accounts.cost = 1000
accounts.$title.valueName



@propertyWrapper
struct Validation<T:Collection> {
    var wrappedValue:T
    var projectedValue:Self {self}
    var isPass:Bool {
        get {
            if(self.isRequired){
                return !self.wrappedValue.isEmpty
            }
            return true
        }
    }
    private(set) var isRequired:Bool
}

struct Form {
    @Validation(isRequired: true) var name:String = ""
    @Validation(isRequired: true) var phone:String = ""
    
    func submit(){
        if(!self.$name.isPass || !self.$phone.isPass){
            print("驗證未通過")
            return
        }
        print("驗證通過")
    }
}
var form = Form(name: "", phone: "")
form.submit()
form.name = "name"
form.phone = "0900000000"
form.submit()

