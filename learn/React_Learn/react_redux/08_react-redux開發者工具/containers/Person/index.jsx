import React, { Component } from 'react'
import {connect} from 'react-redux'
import {createAddPersonAction} from '../../redux/actions/person'
import {nanoid} from 'nanoid'

class Person extends Component {
    addPerson = ()=>{
        const {value:name} = this.nameNode
        const {value:age} = this.ageNode
        const newPerson = {id:nanoid(), name, age}
        console.log(newPerson);
        this.props.addPerson(newPerson)
    }
    render() {
        return (
            <div>
                <input ref={c => this.nameNode = c} type="text" placeholder="Name" />
                <input ref={c => this.ageNode = c} type="text" placeholder="Age" />
                <button onClick={this.addPerson}>Add</button>
                <h2>上方總合 {this.props.count}</h2>
                <ul>
                    {
                        this.props.personList.map((person)=>{
                            return <li key={person.id}>{person.name}--{person.age}</li>
                        })
                    }
                </ul>
            </div>
        )
    }
}

export default connect(
    state => ({
        personList: state.personList,
        count: state.count
    }),
    {
        addPerson: createAddPersonAction
    }
)(Person)

