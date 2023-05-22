# Build lambda function

## Linux and MaxOS

```bash
# build go app
GOOS=linux GOARCH=amd64 go build -o bootstrap main.go

# package to zip
zip -r bootstrap.zip bootstrap resources
```

## Windows

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
go build -o bootstrap main.go

# package to zip
%USERPROFILE%\Go\bin\build-lambda-zip.exe -o bootstrap.zip bootstrap resources
```

#### in Powershell:

```bash
# build go app
$env:GOOS = "linux"
$env:GOARCH = "amd64"
$env:CGO_ENABLED = "0"
go build -o bootstrap main.go

# package to zip
~\Go\Bin\build-lambda-zip.exe -o bootstrap.zip bootstrap resources
```