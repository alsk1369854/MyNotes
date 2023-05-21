package main

import (
	"fmt"
)

func calc(a int, b int) (int, int) {
	defer println("after return")
	println("before return")
	return a + b, a - b
}

func doCallback(num int, callbackFunc func(num int)) {
	newNum := num * 2
	callbackFunc(newNum)
}

func returnFunc(msg string) func() {
	return func() {
		println(msg)
	}
}

func main() {
	println("Function")

	println("General func")
	// general func
	add, red := calc(10, 5)
	fmt.Println(add, red)

	println("Var func")
	// var func
	myVarFunc := func(message string) {
		fmt.Println(message)
	}
	myVarFunc("call myVarFunc")
	println()

	println("Concepts func")
	// 1. concepts func
	myConFuncReturn := func(num int) int {
		return num * 2
	}(2)
	fmt.Printf("1. myConFuncReturn: %v\n", myConFuncReturn)

	// 2. concepts func
	func(message string) {
		println("2. con func: " + message)
	}("Hello world")
	println()

	// callback func
	println("Callback func")
	doCallback(2, func(num int) {
		fmt.Printf("myCallbackFunc: %v\n", num)
	})
	println()

	// Closures func
	println("Closures func")
	returnFunc("call returnFunc")()
}
