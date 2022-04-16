import {Component} from 'react'
import Header from './components/Header'
import List from './components/List'
import Footer from './components/Footer'


export default class App extends Component{
    state = {
        todos:[
            {id: 1, name:'吃飯', done: true},
            {id: 2, name:'睡覺', done: false},
            {id: 3, name:'打代碼', done: false},
        ]
    }
    addTodo = (todoObj)=>{
        const {todos} = this.state;
        const newTodos = [todoObj, ...todos];
        this.setState({todos: newTodos});
    }
    updateTodos = (id, done)=>{
        const {todos} = this.state
        const newTodos = todos.map((item)=>{
            if(item.id === id){
                return {...item, done};
            }else{
                return item;
            }
        })
        this.setState({todos: newTodos});
    }
    deleteTodo = (id)=>{
        const {todos} = this.state;
        const newTodos = todos.filter((item)=>{
            return item.id !== id;
        })
        this.setState({todos: newTodos});
    }
    checkAllTodos = (done)=>{
        const {todos} = this.state;
        const newTodos = todos.map((item)=>{
            return {...item, done};
        })
        this.setState({todos: newTodos});
    }
    cleanAllDone = ()=>{
        const {todos} = this.state
        const newTodos = todos.filter((item)=>{
            return !item.done;
        })
        this.setState({todos: newTodos});
    }
    render(){
        const {todos} = this.state
        return(
            <div>
                <Header addTodo={this.addTodo}/>
                <List todos={todos} updateTodos={this.updateTodos} deleteTodo={this.deleteTodo}></List>
                <Footer todos={todos} checkAllTodos={this.checkAllTodos} cleanAllDone={this.cleanAllDone}></Footer>
            </div>
        )
    }
}


