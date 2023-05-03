// npm install redux
// 並引入createStore
import {createStore} from 'redux'
import countReducer from './count_reducer'

export default createStore(countReducer)