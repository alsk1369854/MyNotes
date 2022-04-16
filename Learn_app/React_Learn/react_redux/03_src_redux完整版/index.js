import React from 'react'
import ReactDOM from 'react-dom'
import store from './redux/store'
import App from './App'

ReactDOM.render(<App/>, document.getElementById('root'));

// 每次更新 store 自動重新渲染頁面
store.subscribe(()=>{  
    ReactDOM.render(<App/>, document.getElementById('root'));
})