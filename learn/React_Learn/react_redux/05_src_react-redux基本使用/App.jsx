import React, { Component } from 'react'
import Count from './containers/Count'
import store from './redux/store'

export default class App extends Component {
    render() {
        return (
            <div>
                {/* 插入 Count 容器 傳入 store */}
                <Count store={store}></Count>
            </div>
        )
    }
}

