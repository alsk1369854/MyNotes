# AWS lambda with go

[AWS lambda with go doc](https://docs.aws.amazon.com/zh_tw/lambda/latest/dg/golang-handler.html)

[GitHub aws/aws-lambda-go](https://github.com/aws/aws-lambda-go)

[AWS API Gateway](https://docs.aws.amazon.com/apigateway/latest/developerguide/api-gateway-create-api-as-simple-proxy-for-lambda.html)

## Info

```bash
go 1.20
```



## Init

- go init mod {you_module_name}

```bash
go init mod aws-lambda
```

 

## Install lib

```bash
# aws-lambda lib
go get github.com/aws/aws-lambda-go

# gorm lib
go get -u gorm.io/gorm

# postgresSQL driver
go get gorm.io/driver/postgres
```

## Build lambda function

### Linux and MaxOS

```bash
# build go app
GOOS=linux GOARCH=amd64 CGO_ENABLED=0 go build -o main main.go

# package to zip
zip main.zip main
```



### Windows

#### Get the tool

```bash
go.exe install github.com/aws/aws-lambda-go/cmd/build-lambda-zip@latest
```



#### in cmd.exe:

```bash
# build go app
set GOOS=linux
set GOARCH=amd64
set CGO_ENABLED=0
go build -o main main.go

# package to zip
%USERPROFILE%\Go\bin\build-lambda-zip.exe -o main.zip main
```



#### in Powershell:

```bash
# build go app
$env:GOOS = "linux"
$env:GOARCH = "amd64"
$env:CGO_ENABLED = "0"
go build -o main main.go

# package to zip
~\Go\Bin\build-lambda-zip.exe -o main.zip main
```