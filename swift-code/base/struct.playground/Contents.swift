
struct Human: CustomStringConvertible & Equatable{
    
    // (靜態參數) 使用 struct name 來訪問 ex. Human.population
    static private(set) var population = 0
    
    // (實例參數)
    // private 私有宣告 | public 公有宣告(default)
    private let name:String
    // private(set|get) 單純私有讀或寫
    private(set) var age:Int
    // (參數監聽)
    private(set) var height:Int {
        // 設置前 newValue 為新值
        willSet (newValue){
            print(height, newValue)
        }
        // 設置後 oldValue 為舊值
        didSet (oldValue){
            print(oldValue, height)
        }
    }
  
    // (計算參數)
    private var defaultValue:String = ""
    public var value:String {
        get {
            return defaultValue
        }
        set (newValue){
            self.defaultValue = newValue
        }
    }
    
    // @Override print 樣式
    // 實現 CustomStringConvertible 協定
    var description: String {
        return "\(self.name)_\(self.age)_\(self.height)"
    }
  
    
    // (構造器)
    public init(name:String) {
        // 實例函數中可使用 Self 或 struct name 來訪問靜態參數
        Human.population += 1
        // 使用 self 來訪問自身參數
        self.name = name
        self.age = 0
        self.height = 5
    }
    
    // (構造器) 多載
    public init(name:String, age:Int, height:Int) {
        self.init(name: name)
        self.age = age
        self.height = height
    }
    
    // (靜態方法) Self 表示自身類型
    // 實現 Equatable 協定
    static public func == (lhs:Self, rhs:Self) -> Bool {
        return lhs.description == rhs.description
    }
    
    // (實例方法)
    public func eat(){
        print("\(self.name) eating...")
    }

    // (實例方法)
    // mutating 表示此方法會修改內部參數的宣告
    public mutating func growUp(){
        self.age += 1
        if age <= 20 {
            self.height += (1...10).randomElement()!
        }
    }
    
    public mutating func reboot() -> Void{
        self = Human(name: self.name)
    }
}

var person = Human(name:"Ming", age:27, height:180)
let person2 = Human(name:"Wei", age:25, height:162)
print(person)
person == person2
Human.population
person.eat()
person.reboot()
person.growUp()
person.value
person.value = "set value"
person.value


// 單例
struct Singleton {
    static private var instance:Self?
    
    private init(){}
    
    static public func getInstance() -> Self {
        if (Self.instance == nil){
            Self.instance = Singleton()
        }
        return Self.instance!
    }
    
    public func run(){
        print("run")
    }
}

let singleton = Singleton.getInstance()
singleton.run()
