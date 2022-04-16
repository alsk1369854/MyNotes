// npm install redux
// 並引入createStore
import {createStore, applyMiddleware, combineReducers} from 'redux'
import countReducer from './reducers/count'
import personReducer from './reducers/person'
// 異步action工具
import thunk from 'redux-thunk'

const allReducer = combineReducers({
    count: countReducer,
    personList: personReducer
})

export default createStore(allReducer,applyMiddleware(thunk))