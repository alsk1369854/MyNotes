// npm install redux
// 並引入createStore
import {createStore, applyMiddleware} from 'redux'
import countReducer from './count_reducer'
// 異步action工具
import thunk from 'redux-thunk'

export default createStore(countReducer,applyMiddleware(thunk))