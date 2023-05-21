package main

import "fmt"

type PetInterface interface {
	move()
}

type Dog struct {
	name string
}

func (d *Dog) move() {
	fmt.Printf("%s do run\n", d.name)
}

func NewDog() *Dog {
	return &Dog{
		name: "dog",
	}
}

type Cat struct {
	name string
}

func (c *Cat) move() {
	fmt.Printf("%s do jump\n", c.name)
}

func NewCat() *Cat {
	return &Cat{
		name: "cat",
	}
}

type i interface{}

func found(i interface{}) {
	fmt.Printf("Type: %T, Value: %v\n", i, i)
}

func main() {
	println("Interface")

	dog := NewDog()

	cat := NewCat()

	petList := []PetInterface{dog, cat}
	for _, pet := range petList {
		// found(pet)
		switch pet.(type) {
		case *Dog:
			dogPet := pet.(*Dog)
			fmt.Printf("Dog name: %s\n", dogPet.name)
		case *Cat:
			catPet := pet.(*Cat)
			fmt.Printf("Cat name: %s\n", catPet.name)
		}
		pet.move()
	}
}
