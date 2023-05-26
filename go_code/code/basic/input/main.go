package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

func main() {
	fmt.Println("go Input")
	scanner := bufio.NewScanner(os.Stdin)

	fmt.Print("Input a string:")
	scanner.Scan()
	input := scanner.Text()
	fmt.Printf("You input: %s\n", input)
	println()

	fmt.Print("Input a number:")
	scanner.Scan()
	input = scanner.Text()
	num, _ := strconv.ParseInt(input, 10, 64)
	fmt.Printf("You input: %d\n", num)
}
