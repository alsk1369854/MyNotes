package main

import (
	"fmt"
	"strconv"
)

func main() {
	// str
	// var myString string = "str"
	myString := "str"
	// type check
	fmt.Printf("myStringType: %T\n", myString)
	fmt.Println("myString: " + myString)
	fmt.Println()

	// str to int
	if myIntParse, err := strconv.Atoi("20"); err == nil {
		myIntParse += 1
		// type check
		fmt.Printf("myIntParseType: %T\n", myIntParse)
		// int to str
		myStrInt := strconv.Itoa(myIntParse)
		fmt.Println("myStrInt: " + myStrInt)
	}
	fmt.Println()

	// str to float
	if myFloatParse, err := strconv.ParseFloat("0.5432", 64); err == nil {
		myFloatParse += 1
		// type check
		fmt.Printf("myFloatParseType: %T\n", myFloatParse)
		// float to str
		myStrFloat := fmt.Sprintf("%f", myFloatParse)
		fmt.Println("myStrFloat: " + myStrFloat)
	}
	fmt.Println()

	// str to bool
	if myBoolParse, err := strconv.ParseBool("true"); err == nil {
		// type check
		fmt.Printf("myBoolParseType: %T\n", myBoolParse)
		if myBoolParse {
			// bool to str
			myStrBool := strconv.FormatBool(myBoolParse)
			fmt.Println("myString: " + myStrBool)
		}
	}
	fmt.Println()

}
