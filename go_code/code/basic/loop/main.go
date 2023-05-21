package main

import "fmt"

func main() {
	// while loop
	println("While loop:")
	x := 0
	for x < 5 {
		fmt.Printf("%v, ", x)
		x++
	}
	println("\n")

	// for loop
	println("For loop:")
	for x := 0; x < 5; x++ {
		if x == 1 {
			continue
		}
		if x == 4 {
			break
		}
		fmt.Printf("%d, ", x)
	}
	println("\n")

	// 1. array loop
	println("Array loop")
	intList := []int{1, 2, 3}
	fmt.Printf("intList: %v\n", intList)

	print("1. : ")
	sum := 0
	for i := 0; i < len(intList); i++ {
		sum += intList[i]
	}
	fmt.Printf("For intList => sum : %d\n", sum)

	// 2. array loop (未用到可使用 "_" 來充當佔位符)
	print("2. : ")
	for i, item := range intList {
		fmt.Printf("(%d:%d), ", i, item)
	}
	println("\n")
}
