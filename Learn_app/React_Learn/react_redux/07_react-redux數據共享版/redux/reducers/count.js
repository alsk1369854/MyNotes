import {INCREMENT, DECREMENT} from '../constant'

// 建立處理事件
const defaultState = 0
export default function countReducer(preState=defaultState, action){
    // console.log("@Count")
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