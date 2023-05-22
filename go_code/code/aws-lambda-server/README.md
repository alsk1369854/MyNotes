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

## Project structure

```markdown
.
├── README.md   # 基本使用文件
├── docs        # 開發文件目錄 
├── go.mod      # go mod 配置
├── go.sum      # go mod 包
├── main.go     # go 主程式進入點
├── resources   # 前端資源目錄
│   ├── static       # 靜態資源目錄
│   └── templates    # view 目錄
└── src         # 開發目錄
    ├── main         # 主程式目錄
    │   ├── controllers     # 控制器相關目錄
    │   ├── db              # 資料庫處理相關目錄
    │   ├── exceptions      # 例外處理相關目錄
    │   ├── initializers    # 初始化相關目錄
    │   ├── models          # ORM 模型相關目錄
    │   └── services        # 服務相關目錄
    └── test         # 測試程式目錄
```
