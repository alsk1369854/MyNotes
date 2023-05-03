import React, { Component } from 'react'
import store from '../../redux/store'
import {createIncrementAction, createDecrementAction} from '../../redux/count_action'

export default class Count extends Component {
    
    increment = () =>{
        const {value} = this.select
        store.dispatch(createIncrementAction(value))
        // this.setState({count : count + value*1});
    }
    decrement = () =>{
        const {value} = this.select
        store.dispatch(createDecrementAction(value));
    }
    incrementIfOdd = () =>{
        const {value} = this.select
        const count = store.getState();
        if(count % 2 === 1) store.dispatch(createIncrementAction(value));
    }
    incrementAsync = () =>{
        const {value} = this.select
        setTimeout(()=>{
            store.dispatch(createIncrementAction(value));
        }, 3000)
        
    }

    render() {
        return (
            <div>
                <div>現在數 {store.getState()}</div>
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
