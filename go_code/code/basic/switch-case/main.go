package main

func main() {
	println("Switch case")

	caseType := 5
	switch caseType {
	case 1, -1:
		println("one")
	case 2:
		println("two")
	case 3:
		println("three")
	default:
		println("default")
	}
}
