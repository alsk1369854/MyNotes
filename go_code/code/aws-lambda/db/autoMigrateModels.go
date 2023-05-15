package db

import (
	"aws-lambda/models"

	"gorm.io/gorm"
)

func AutoMigrateModels(db *gorm.DB) {
	// 註冊物件關聯映射模型
	db.AutoMigrate(&models.Person{})
}
