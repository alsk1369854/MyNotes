import React, { Component } from 'react'
import PropsTypes from 'prop-types'
import {nanoid} from 'nanoid'

export default class Header extends Component {
    
    // 對接收的props進行: 類型, 必要性的限制
    static propsTypes = {
        addTodo:PropsTypes.func.isRequired
    }

    sendFn =(event)=>{
        const { addTodo } = this.props;
        const {target, key} = event;
        if(key !== 'Enter') return
        if(target.value.trim() === ''){
            alert('不能輸入空值');
            return
        }
        const todoObj = {id: nanoid(), name: target.value, done:false}
        addTodo(todoObj);
        target.value = '';
    }
    render() {
        return (
            <div>
                <div>
                    <span>Header: </span>
                    <input type="text" onKeyUp={this.sendFn}></input>
                </div>
            </div>
        )
    }
}
