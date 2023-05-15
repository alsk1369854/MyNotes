package models

import (
	"time"

	"gorm.io/gorm"
)

type Tabler interface {
	TableName() string
}

// 配置 物件關聯映射 的表名
func (Person) TableName() string {
	return "person"
}

// Person 模型
type Person struct {
	gorm.Model
	Name     string    `json:"name"`
	Age      int       `json:"age"`
	Gender   string    `json:"gender"`
	Birthday time.Time `json:"birthday"`
}
