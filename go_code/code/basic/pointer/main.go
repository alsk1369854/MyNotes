package main

import "fmt"

func changeValue(numPtr *int) {
	*numPtr = 1000
}

func main() {
	println("Pointer")
	/*
		&{varName}: get value pointer
		*{varName}: get pointer value

		*{typeName}: define a pointer type
	*/
	x := 7
	myPtr := &x
	fmt.Println(x, myPtr)
	*myPtr = 8
	fmt.Println(x, myPtr)

	changeValue(myPtr)
	fmt.Println(x, myPtr)
}
