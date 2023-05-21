package db

import (
	"log"
	"os"

	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

var DB *gorm.DB

func ConnectDB() {
	dsn := "host=" + os.Getenv("db_host") +
		" user=" + os.Getenv("db_user") +
		" password=" + os.Getenv("db_pass") +
		" dbname=" + os.Getenv("db_name") +
		" port=" + os.Getenv("db_port") +
		" sslmode=disable"
	db, err := gorm.Open(postgres.Open(dsn), &gorm.Config{})
	if err != nil {
		log.Fatal("Failed to connect to database")
	}
	// db.raw("select 8")
	AutoMigrateModels(db)
	DB = db
}
