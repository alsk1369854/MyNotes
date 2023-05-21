package db

import (
	"aws-lambda-server/src/main/models"

	"gorm.io/gorm"
)

func AutoMigrateModels(db *gorm.DB) {
	db.AutoMigrate(&models.Person{})
}
