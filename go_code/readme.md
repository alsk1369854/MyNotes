### Complie

#### macOS or Linux

```bash
SET GOOS=linux&SET GOARCH=arm64&go build .
```

#### Windows

```bash
set GOOS=windows
set GOARCH=amd64
go env -w GOOS=windows
go env -w GOARCH=amd64
go build .
```
