package main

import (
	"api-CRUD/initializers"
	"api-CRUD/models"
)

func init() {
	initializers.LoadEnv()
	initializers.ConnectDB()
}

func main() {
	initializers.DB.AutoMigrate(&models.Person{})
}
