package initializers

import (
	"log"
	"os"

	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

var DB *gorm.DB

func ConnectDB() {
	dsn := "host=" + os.Getenv("db.host") +
		" user=" + os.Getenv("db.user") +
		" password=" + os.Getenv("db.password") +
		" dbname=" + os.Getenv("db.database") +
		// " port=" + os.Getenv("db.port") +
		" sslmode=disable"
	db, err := gorm.Open(postgres.Open(dsn), &gorm.Config{})
	if err != nil {
		log.Fatal("Failed to connect to database")
	}
	DB = db
}
