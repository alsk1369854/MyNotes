# CommonJS NodeJS

> ## cli 運行 node.js 
```shell
# node [程式進入點檔案]
node ./app.js 
```

> ## 導出
### 默認導出 (類似 ES6: export default)
```javascript
// 導出一個物件
module.exports = {
    name : 'Ming',
    age: '18',
    setHi(){
        console.log('Hi')
    }
}

// 導出一個函數
module.exports = function(){
    return 'export function'
}

// 導出一個值
module.exports = 'Just value'
```
### 一般導出 (類似 ES6: export)
```javascript
// 導出一個物件
exports.obj = {
    name : 'Han',
    age : 15
}

// 導出一個函數
exports.fun = ()=>{
    return 'export fun'
}

// 導出一個值
exports.number = 20
exports.arr = [1,2,3,5,4]
```

<br/>

> ## 導入
### 使用默認導出
```javascript
// let [變數名稱] = require([js檔案路徑])
let obj = require('./modules/obj')
let func = require('./modules/func')

// 直接使用
console.log(obj) // {XXX:xxx, XXX:xxx}
console.log(func) // function(){ .... }
```

### 使用一般導出
```javascript
// let [變數名稱] = require([js檔案路徑])
let Util = require('./modules/Util')

// 透過 .[對象] 來選擇使用
console.log(Util.obj) // {XXX:xxx, XXX:xxx}
console.log(Util.func) // function(){ .... }
```