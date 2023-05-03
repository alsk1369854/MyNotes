import React, { Component } from 'react'

// 引入 連接 工具
import {connect} from 'react-redux'
// redux action 操作方法
import {createDecrementAction,createIncrementAction,createIncrementAsyncAction} from '../../redux/actions/count'

class Count extends Component {
    
    increment = () =>{
        const {value} = this.select
        this.props.increment(value)

    }
    decrement = () =>{
        const {value} = this.select
        this.props.decrement(value)
    }
    incrementIfOdd = () =>{
        const {value} = this.select
        const {count} = this.props
        if(count %2 !== 0) this.props.increment(value)
    }
    incrementAsync = () =>{
        const {value} = this.select
        this.props.incrementAsync(value,500)
    }

    render() {
        return (
            <div>
                <h2>現在數 {this.props.count}, 下方人數{this.props.personListLen}</h2>
                <select ref = {c => this.select = c}>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                </select>&nbsp;

                <button onClick={this.increment}>+</button>&nbsp;
                <button onClick={this.decrement}>-</button>&nbsp;
                <button onClick={this.incrementIfOdd}>increment if odd</button>&nbsp;
                <button onClick={this.incrementAsync}>increment async</button>&nbsp;
            </div>
        )
    }
}


export default connect(
    state => ({
        count:state.count,
        personListLen: state.personList.length
    }),
    {
        increment: createIncrementAction,
        decrement: createDecrementAction,
        incrementAsync: createIncrementAsyncAction
    }
)(Count)