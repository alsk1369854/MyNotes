package main

import (
	"fmt"
	"reflect"
)

func myf1(f interface{}, args ...interface{}) {
	fmt.Println("Hi")
	// myf2(myf1, myf1)
	argsLen := len(args)
	params := make([]reflect.Value, argsLen) //参数
	for i, argsItem := range args {
		params[i] = reflect.ValueOf(argsItem)
	}
	reflect.ValueOf(f).Call(params)
}

func myf2(f1, f2 interface{}) {
	p1 := fmt.Sprintf("%v", f1)
	p2 := fmt.Sprintf("%v", f2)
	fmt.Println(p1, p2)
	fmt.Println("Expecting true:", p1 == p2)
}

func main() {
	fmt.Println("data struct")
	// myf2()
	myf1(myf2, myf1, myf1)
}
