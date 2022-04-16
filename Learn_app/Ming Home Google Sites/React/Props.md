# Props



### 父組件傳遞 Props 給子組件



```jsx
import ChildComponent from '@xxx/ChildComponent'
// 經由組件標籤傳遞參數給子組件
class ParentComponent extends React.Component{
    childName = 'ming'
    childAge = 18
    childSpeak = () => console.log("Hi")
    
    // ...............
    
    render(){
        return(
            <>
                {/*傳遞參數給子組件*/}
                <ChildComponent 
                    childName = {this.childName} 
                    childAge = {this.childAge}
                    childSpeak = {this.childSpeak}/>
            </>
        )
    }
}
```



### 子組件接收 Props



```jsx
// 子組件透過 this.props 接取父組件傳送的參數
class ChildComponent extends React.Component{

    // ...............
    
    render(){
        // ES6解構接收到的props
        const {childName, childAge, childSpeak} = this.props

        return(
            <>
                {/*使用接收到的參數*/}
                <div>
                    {`My name is ${childName}, I'm ${childAge} years old`}
                </div>
                <buttom onClick={childSpeak}>Say Hi~</buttom>
                    
            </>
        )
    }
}
```



## Jsx 代碼演示



```jsx
// 創建類式組件
class Person extends React.Component{   
    render(){
        // 透過 this.props 讀出傳入的值
        const {name, age, sex, speak} = this.props;
        console.log(this.props)
        return(
            <ul onClick={speak}>
                <li>姓名: {name}</li>
                <li>性別: {sex}</li>
                <li>年齡: {age+1}</li>   
            </ul>
        )
    }
}

// 定義要傳入的 function
function speak(){
    console.log("Hi");
}

// 渲染組件到頁面
// 方法一:
ReactDOM.render(
    <Person name="susan" age={18} sex="女" speak={speak}/>,
    document.getElementById('root'));

// 方法二: 
const data = {name:"tom", age:16}
const data2 = {...data, sex:"女", name:"jack"} // 複製物件, 合併新值 
ReactDOM.render(<Person {...data2}/>, document.getElementById('root'))
```



## Tsx 代碼演示



```tsx
// 建立 Props 約束
type Props = {
    name: string,
    age: number,
    sex?: string,
    speak?: Function,
}

// 接收組件使用泛型宣告接收 Props 參數
class Person extends React.Component<Props>{   
    render(){
        // 透過 this.props 讀出傳入的值
        const {name, age, sex, speak} = this.props;
        console.log(this.props)
        return(
            <ul onClick={speak}>
                <li>姓名: {name}</li>
                <li>性別: {sex}</li>
                <li>年齡: {age+1}</li>   
            </ul>
        )
    }
}

// 定義要傳入的 function
function speak(){
    console.log("Hi");
}

// 渲染組件到頁面
// 方法一:
ReactDOM.render(
    <Person name="susan" age={18} sex="女" speak={speak}/>,
    document.getElementById('root'));

// 方法二: 
const data = {name:"tom", age:16}
const data2 = {...data, sex:"女", name:"jack"} // 複製物件, 合併新值 
ReactDOM.render(<Person {...data2}/>, document.getElementById('root'))
```





## Props-types



一個 Props 參數型態約束工具



### Installation



    npm i prop-types



### props-types 代碼演示



```jsx
class Person extends React.Component{
    // 對標籤屬性進行類型、必要性的限制
    static propTypes = {
        name: PropTypes.string.isRequired, //限制name為字串, 且必須存在
        sex: PropTypes.string, // 限制sex為字串
        age: PropTypes.number, // 限制age為數字
        speak: PropTypes.func, // 限制speak為函數
    }
    // 指定默認屬性值
    static defaultProps = {
        sex: "不男不女",
        age: 18
    }
    render(){
        console.log(this.props)
        return(
            <div>
                Person...
            </div>
        )
    }
}
```



### **End**