package models

import (
	"aws-lambda-server/src/main/enums"
	"time"

	"gorm.io/gorm"
)

func (Person) TableName() string {
	return "person"
}

type Person struct {
	gorm.Model
	Name     string             `json:"name"`
	Age      int                `json:"age"`
	Gender   enums.PersonGender `json:"gender"`
	Birthday time.Time          `json:"birthday"`
}
