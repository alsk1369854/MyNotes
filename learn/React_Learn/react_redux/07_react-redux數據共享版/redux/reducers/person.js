
import { ADD_PERSON } from "../constant";

const defaultPerson = [{id:'001', name:'tom', age:18}];
export default function personReducer(preState = defaultPerson, action){
    const {type, data} = action
    // console.log("@Person")
    switch (type) {
        case ADD_PERSON:
            return [data,...preState]
        default:
            return preState
    }
} 