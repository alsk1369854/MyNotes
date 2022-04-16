import React, { Component } from 'react'

export default class Item extends Component {
  render() {
    const {name, age} = this.props
    return (
      <div>
        <span>{name + ", " + age}</span>     
        <input type="text" />   
      </div>
    )
  }
}
