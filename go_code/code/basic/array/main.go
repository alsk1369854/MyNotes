package main

import (
	"fmt"

	"github.com/JonasMuehlmann/datastructures.go/lists/arraylist"
)

func main() {
	println("Array")

	println("Create")
	// one dimension
	arr := [3]int{1, 2, 3}
	fmt.Printf("arr: %v\n", arr)
	fmt.Printf("arr len: %v\n", len(arr))

	// two dimension
	arr2D := [2][3]int{{1, 2}, {3, 4}}
	fmt.Printf("arr2D: %v\n", arr2D)
	fmt.Printf("arr2D len: %v\n", len(arr2D))

	// dynamic create
	myDynArr := make([]int, 5)
	fmt.Printf("myDynArr: %v\n", myDynArr)
	fmt.Printf("myDynArr len: %v\n", len(myDynArr))
	println()

	println("Slice")
	// slice (非拷貝, 指向相同記憶位置)
	sliceDemoArr := []int{1, 2, 3, 4, 5}
	fmt.Printf("baseArr: %v\n", sliceDemoArr)
	fmt.Printf("[1:3]: %v\n", sliceDemoArr[1:3])
	fmt.Printf("[2:]: %v\n", sliceDemoArr[2:])
	fmt.Printf("[:4]: %v\n", sliceDemoArr[:4])
	println()

	println("Append")
	// append (淺拷貝)
	appendDemoArr := []int{1, 2, 3, 4, 5}
	fmt.Printf("baseArr: %v\n", appendDemoArr)
	afterAppendArr := append(appendDemoArr, 6)
	fmt.Printf("afterAppendArr: %v\n", afterAppendArr)
	println()

	println("Vector")
	// vector
	myArrayList := arraylist.New("")
	myArrayList.PushBack("a")
	println(myArrayList.ToString())
	println(myArrayList.Get(5))
}
