# Go AWS lambda server with Gin

### Init

- go init mod {you_module_name}

```bash
go mod init aws-lambda-server
```

## Install libs

```bash
# aws-lambda lib 
go get -u github.com/aws/aws-lambda-go

# aws-lambda api proxy
go get -u github.com/awslabs/aws-lambda-go-api-proxy

# gin framwork
go get -u github.com/gin-gonic/gin

# gorm lib
go get -u gorm.io/gorm

# postgresSQL driver
go get gorm.io/driver/postgres
```

## Start local server

```bash
go run main.go
```
