# Go api CURD

## Init project

### Install Lib

```bash
go mod init myapp
```

#### Gin

- 後端開發框架

[Gin framework](https://gin-gonic.com/docs/quickstart/)

```bash
go get -u github.com/gin-gonic/gin
```

#### GORM

- 物件關聯映射庫

```bash
go get -u gorm.io/gorm
```

##### SQL

###### PortgreSQL

```bash
go get gorm.io/driver/postgres
```

#### Godotenv

- 配置文件讀取庫

```bash
go get github.com/joho/godotenv
```





### Creat basic file

#### .evn

```properties
port=8080

db.host=localhost
db.port=5432
db.user=postgres
db.password=root
db.database=test_db
```

#### initializers

##### loadEnv.go

```go
package initializers

import (
	"log"

	"github.com/joho/godotenv"
)

func LoadEnv() {
	err := godotenv.Load()
	if err != nil {
		log.Fatal("Error load .env file")
	}
}
```



##### connectDB.go

```go
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
		" port=" + os.Getenv("db.port") +
		" sslmode=disable"
	db, err := gorm.Open(postgres.Open(dsn), &gorm.Config{})
	if err != nil {
		log.Fatal("Failed to connect to database")
	}
	DB = db
}
```



#### main.go

```go
package main

package main

import (
	"api-CRUD/initializers"
	"errors"
	"net/http"
	"os"
	"strconv"

	"github.com/gin-gonic/gin"
)

func index(c *gin.Context) {
	c.HTML(http.StatusOK, "index.html", nil)
}

func init() {
	initializers.LoadEnv()
	initializers.ConnectDB()
}

func main() {
	router := gin.Default()
	router.Static("/static", "./static")
	router.LoadHTMLGlob("templates/*.html")

	router.GET("/", index)

	router.Run("localhost:" + os.Getenv("PORT"))
}
```





## API

### Response Data

#### JSON

```go

```
