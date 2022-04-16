// npm install redux
// 並引入createStore
import {createStore, applyMiddleware} from 'redux'
// 引入彙總 reducer
import reducers from './reducers'
// 異步action工具
import thunk from 'redux-thunk'
import {composeWithDevTools} from 'redux-devtools-extension'



export default createStore(reducers,composeWithDevTools(applyMiddleware(thunk)));