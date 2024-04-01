### Complie

#### macOS or Linux

```bash
# build for linux
env GOOS=darwin GOARCH=arm64 go build .

# build for window .exe
env GOOS=windows GOARCH=amd64 go build .
```

#### Windows

```bash
set GOOS=windows
set GOARCH=amd64
go env -w GOOS=windows
go env -w GOARCH=amd64
go build .
```
