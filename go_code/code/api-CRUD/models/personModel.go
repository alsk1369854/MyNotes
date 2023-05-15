package models

import (
	"time"

	"gorm.io/gorm"
)

type Tabler interface {
	TableName() string
}

func (Person) TableName() string {
	return "person"
}

type Person struct {
	gorm.Model
	Name     string    `json:"name"`
	Age      int       `json:"age"`
	Gender   string    `json:"gender"`
	Birthday time.Time `json:"birthday"`
}
