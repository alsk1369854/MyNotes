import React, { Component } from 'react'

export default class Count extends Component {
    
    state = {count : 0};
    increment = () =>{
        const {value} = this.select
        const {count} = this.state
        this.setState({count : count + value*1});
    }
    decrement = () =>{
        const {value} = this.select
        const {count} = this.state
        this.setState({count : count - value*1});
    }
    incrementIfOdd = () =>{
        const {value} = this.select
        const {count} = this.state
        if(count % 2 === 1) this.setState({count : count + value*1});
    }
    incrementAsync = () =>{
        const {value} = this.select
        const {count} = this.state
        setTimeout(()=>{
            this.setState({count : count + value*1});
        }, 3000)
        
    }

    render() {
        return (
            <div>
                <div>現在數 {this.state.count}</div>
                <select ref = {c => this.select = c}>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                </select> |

                <button onClick={this.increment}>+</button> | 
                <button onClick={this.decrement}>-</button> | 
                <button onClick={this.incrementIfOdd}>increment if odd</button> |
                <button onClick={this.incrementAsync}>increment async</button> |
            </div>
        )
    }
}
