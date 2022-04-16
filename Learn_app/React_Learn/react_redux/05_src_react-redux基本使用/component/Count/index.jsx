import React, { Component } from 'react'

export default class Count extends Component {
    
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
                <div>現在數 {this.props.count}</div>
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
