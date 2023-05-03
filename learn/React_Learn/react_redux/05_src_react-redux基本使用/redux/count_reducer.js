import {INCREMENT, DECREMENT} from './contant'

// 建立處理事件
const defaultState = 0
export default function countReducer(preState=defaultState, action){
    const {type, data} = action
    switch (type) {
        case INCREMENT:
            return preState + data*1
        case DECREMENT:
            return preState - data*1
        default:
            return preState;
    }
}