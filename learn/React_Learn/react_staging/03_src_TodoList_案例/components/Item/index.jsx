import React, { Component } from 'react'

export default class Item extends Component {
    state = {
        mouse: false
    }
    setMouse = (value)=>{
        return ()=>{
            this.setState({mouse: value})
        }
    }
    updateItem = (id)=>{
        return (event)=>{
            const {updateTodos} = this.props;
            const {checked} = event.target;
            updateTodos(id, checked);
        }
    }
    deleteItem = (id)=>{
        if(window.confirm('確定刪除嗎?')){
            this.props.deleteTodo(id);
        }
    }
    render() {
        const { id, name, done } = this.props;
        const { mouse } = this.state;
        return (
            <li style={{ backgroundColor: mouse ? '#ddd' : 'white' }} onMouseEnter={this.setMouse(true)} onMouseLeave={this.setMouse(false)}>
                <label>
                    <input type="checkbox" checked={done} onChange={this.updateItem(id)} />
                    <span>{name}</span>
                </label>
                <button onClick={()=>{this.deleteItem(id)}}>delete</button>
            </li>
        )
    }
}
