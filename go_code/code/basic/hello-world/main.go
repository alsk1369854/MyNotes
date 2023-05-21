package main

import (
	"encoding/json"
	"fmt"
)

type Person struct {
	Name string
	Age  int
}

func GetPerson() Person {
	var person = Person{
		Name: "a",
		Age:  12,
	}
	return person
}

func main() {
	name := "ming"
	fmt.Println("Hello: " + name)

	person := GetPerson()
	personJson, err := json.Marshal(person)
	if err != nil {
		return
	}
	fmt.Println("Person: " + string(personJson))
}
