import React, { Component } from 'react'
import PropTypes from 'prop-types'
import Item from '../Item'

export default class index extends Component {

  static propTypes = {
    updateTodos: PropTypes.func.isRequired,
    todos: PropTypes.array.isRequired,
    deleteTodo: PropTypes.func.isRequired
  }

  render() {
    const {todos, updateTodos, deleteTodo} = this.props
    return (
      <div>
        <ul>
            {
                todos.map(item =>{
                    return <Item key={item.id} {...item} updateTodos={updateTodos} deleteTodo={deleteTodo}/>
                })              
            }
        </ul>
      </div>
    )
  }
}
