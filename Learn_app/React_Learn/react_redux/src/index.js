import React from 'react'
import ReactDOM from 'react-dom'
import store from './redux/store'
// npm install react-redux
// 自動檢查需要 redux 的容器 分別傳入store
import {Provider} from 'react-redux' 

import App from './App'

ReactDOM.render(
    <Provider store={store}>
        <App/>
    </Provider>,
    document.getElementById('root')
);
