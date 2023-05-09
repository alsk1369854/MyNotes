# Go

## Installation

[Download and install - The Go Programming Language](https://go.dev/doc/install)

```bash
go version
```



## Go with vscode

### 1. Install vscode extension module

1. golang.go



### 2. Update go module

-  Open search bar : ctr + shift + P

```bash
Go: Install/Update tools
```



### 3. Create go app directory

- go mod init {add_directory_name}

```bash
mkdir hello-world

go mod init hello-world
```



### 4. Create main.go file

```bash
touch main.go
```

#### main.go

```go
package main

import "fmt"

func main() {
	name := "ming"
	fmt.Println("Hello: " + name)
}
```

