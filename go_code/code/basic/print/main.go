package main

import "fmt"

func main() {
	println("General")
	// form
	// value type
	fmt.Printf("type: %T\n", 10)

	// form
	// value
	fmt.Printf("value: %v\n", 10)

	// sprintf
	myStr := fmt.Sprintf("Sprintf: {type: %T, value: %v}", 10, 10)
	fmt.Println(myStr)
	println()

	println("String")
	// form
	fmt.Printf("string s : %s\n", "hi")
	println()

	// Integer
	fmt.Println("Integer")
	// form
	// digital
	fmt.Printf("digital: %d\n", 10)

	// form
	// binary (2)
	fmt.Printf("binary: %b\n", 10)

	// form
	// octal (8)
	fmt.Printf("octal: %o\n", 9)

	// form
	// hexadecimal (16)
	fmt.Printf("hexadecimal: %x\n", 16)
	println()

	println("Float")
	// form
	fmt.Printf("float e : %e\n", 0.54321)
	fmt.Printf("float f : %f\n", 0.54321)
	fmt.Printf("float g : %g\n", 0.54321)
	fmt.Printf("float .2f : %.2f\n", 0.54321)
	println()

	println("Boolean")
	fmt.Printf("bool: t : %t\n", true)
}
