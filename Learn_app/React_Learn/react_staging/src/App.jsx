import React, { Component } from 'react'
import Item from './components/Item';


export default class App extends Component {
  state = {
    person: [
      { id: 1, name: 'tom', age: 18 },
      { id: 2, name: 'ming', age: 8 },
      { id: 3, name: 'gin', age: 10 },
    ]
  }

  addPerson = () => {
    const newPerson = { id: 5, name: 'han', age: 9 }
    const { person } = this.state
    this.setState({ person: [newPerson, ...person] })
  }

  render() {
    return (
      <div>
        <ul>
          {
            this.state.person.map(item => {
              return <Item key={item.id} {...item} />
            })
          }
        </ul>
        <button onClick={this.addPerson}>Add Persons</button>
      </div>
    )
  }
}
