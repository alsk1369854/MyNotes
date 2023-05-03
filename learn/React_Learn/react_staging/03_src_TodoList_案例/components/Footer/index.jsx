import React, { Component } from 'react'

export default class Footer extends Component {
  handleCheckAll=(event)=>{
    const {checked} = event.target;
    const {checkAllTodos} = this.props
    checkAllTodos(checked);
  }
  render() {
    const {todos, cleanAllDone} = this.props;
    const done = todos.reduce((pre,item)=> {
      return pre + (item.done ? 1 : 0)
    },0)
    const todosLen = todos.length;
    
    return (
      <div>
        Footer
        <label>
          <input type="checkbox" checked={todosLen === done && todosLen !== 0} onChange={this.handleCheckAll}/>
        </label>
        <span>{done}完成/{todosLen}總數</span>
        <button onClick={cleanAllDone}>Send</button>
      </div>
    )
  }
}
