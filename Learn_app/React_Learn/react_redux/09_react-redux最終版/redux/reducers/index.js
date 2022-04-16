import {combineReducers} from 'redux'
import count from './count'
import personList from './person'

export default combineReducers({
    count,
    personList
})