package db

import (
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

var DB *gorm.DB

func GetConnect() (*gorm.DB, error) {
	// 使用 GORM 連線 PostgreSQL
	dsn := "host=satao.db.elephantsql.com" +
		" user=caldbsoi" +
		" password=OXvixAY8O3uGT9nCdRw9agsFKXP1wpl5" +
		" dbname=caldbsoi" +
		" sslmode=disable"
	DB, err := gorm.Open(postgres.Open(dsn), &gorm.Config{})
	if err != nil {
		return DB, err
	}
	// 註冊模型映射
	AutoMigrateModels(DB)

	return DB, nil
}
