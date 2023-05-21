package main

import "fmt"

type Person struct {
	name string
	age  int
}

func (p *Person) setAge(newAge int) {
	p.age = newAge
}
func (p *Person) run() {
	fmt.Println(p.name + " do run")
}

type Student struct {
	*Person
	grades []int
}

func (s *Student) getAvgGrade() float32 {
	sum := 0
	for _, grade := range s.grades {
		sum += grade
	}
	return float32(sum) / float32(len(s.grades))
}

func NewStudent() *Student {
	return &Student{
		Person: &Person{
			name: "ming",
			age:  20,
		},
		grades: []int{60, 70, 80},
	}
}

func main() {
	println("Structs")

	student := NewStudent()

	// struct values
	println("Struct values")
	fmt.Printf("Student name: %s\n", student.name)
	fmt.Printf("Student age: %d\n", student.age)
	fmt.Printf("Student grade: %v\n", student.grades)
	fmt.Println(student)
	println()

	// struct methods
	println("Struct methods")
	fmt.Printf("before set age: %v\n", student.age)
	student.setAge(30)
	fmt.Printf("after set age: %v\n", student.age)
	fmt.Printf("student avg grade: %.2f\n", student.getAvgGrade())
	student.run()
	println()

}
