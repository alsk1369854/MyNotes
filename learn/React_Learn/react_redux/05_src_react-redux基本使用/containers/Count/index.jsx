// 引入 UI組件
import CountUI from '../../component/Count'

// 引入 連接 工具
import {connect} from 'react-redux'

import {createDecrementAction,createIncrementAction,createIncrementAsyncAction} from '../../redux/count_action'


const mapStateToProps = (state) =>{
    return {count:state}
}   

const mapDispatchToProps = (dispatch)=>{
    return {
        increment: data => dispatch(createIncrementAction(data)),
        decrement: data => dispatch(createDecrementAction(data)),
        incrementAsync: (data, time) => dispatch(createIncrementAsyncAction(data,time))
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(CountUI)