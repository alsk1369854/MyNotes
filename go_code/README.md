# Go



## 環境安裝

### 安裝 Golang

```bash
# 從 Mac Homebrew 工具安裝 golang
brew install go

# 查看 golang 版本
go version
```

### 設定 Vscode 開發環境

[設定 Go 開發的 Visual Studio Code | Microsoft Learn](https://learn.microsoft.com/zh-tw/azure/developer/go/configure-visual-studio-code)

1. 安裝 Golang 延伸模組
   
   - golang.go

2. 更新延升模組
   
   1. 開啟搜索欄：ctr + shift + P
   
   2. 輸入命令：Go: Install/Update tools
   
   3. 全選所有選項 > OK





## Hello, World!

### 建立專案

```bash
mkdir hello-world

# go mod init 初始化專案命令
go mod init hello-world
```

### 編寫程式

#### main.go

```go
package main

import "fmt"

func main() {
    fmt.Println("Hello, World!")
}
```

### 運行程式

```bash
# 運行 main.go 文件
go run main.go
```

## 命令行

### 依賴管理

#### 安裝依賴

```bash
# 安裝依賴
go get github.com/example/dependency
```

#### 刪除依賴

```bash
# @none 表示刪除對應依賴
go get github.com/example/dependency@none
# 重新理整 go.mod 文件
go mod tidy
```

#### 整理依賴

```bash
# 重新理整 go.mod 文件
go mod tidy
```

#### 更新依賴

```bash
# 更新依賴
go get -u
```

### 環境配置

#### 查看設定

```bash
# 查看所有設定
go env
```

#### 設定配置

- go env -w {ENV_NAME}={value}

```bash
# 設定 GO111MODULE = on
go env -w GO111MODULE=on
```
