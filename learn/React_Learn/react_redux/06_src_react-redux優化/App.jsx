import React, { Component } from 'react'
import Count from './containers/Count'

export default class App extends Component {
    render() {
        return (
            <div>
                {/* 插入 Count 容器 傳入 store */}
                <Count></Count>
            </div>
        )
    }
}

