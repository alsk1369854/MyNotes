package main

import "fmt"

func main() {
	println("Map")

	// Create
	println("Create")
	myMap := map[string]int{
		"one":   1,
		"two":   2,
		"three": 3,
	}
	fmt.Printf("myMap: %v\n", myMap)

	// dynamic create
	myDynMap := make(map[int]string)
	myDynMap[1] = "one"
	myDynMap[2] = "two"
	fmt.Printf("myDynMap: %v\n", myDynMap)
	println()

	// Get
	println("Get")
	mapOneValue, ok := myMap["one"]
	fmt.Println(mapOneValue, ok) // 1 true
	mapFiveValue, ok := myMap["five"]
	fmt.Println(mapFiveValue, ok) // 0 false
	println()

	// Set
	println("Set")
	fmt.Printf("before set myMap: %v\n", myMap)
	myMap["four"] = 4
	myMap["one"] = -1
	fmt.Printf("after set myMap: %v\n", myMap)
	println()

	// Delete
	println("Delete")
	fmt.Printf("before delete myMap: %v\n", myMap)
	delete(myMap, "four")
	fmt.Printf("after delete myMap: %v\n", myMap)

}
