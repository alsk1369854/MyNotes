package db

import (
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

func GetConnect() (*gorm.DB, error) {
	// 使用 GORM 連線 PostgreSQL
	dsn := "host=satao.db.elephantsql.com" +
		" user=caldbsoi" +
		" password=OXvixAY8O3uGT9nCdRw9agsFKXP1wpl5" +
		" dbname=caldbsoi" +
		" sslmode=disable"
	db, err := gorm.Open(postgres.Open(dsn), &gorm.Config{})
	if err != nil {
		return db, err
	}
	// 註冊模型映射
	AutoMigrateModels(db)

	return db, nil
}
