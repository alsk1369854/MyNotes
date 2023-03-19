# C++

## Write C++ with VSCode

### 1. 安裝 MSYS2

```base
# 至官網下載最新版: https://www.msys2.org/
# 或使用此連結下載: https://github.com/msys2/msys2-installer/releases/download/2023-01-27/msys2-x86_64-20230127.exe
# 完成後使用下方命令開始安裝 MinGW
pacman -S --needed base-devel mingw-w64-x86_64-toolchain
```

### 2. 檢查系統環境

```
gcc --version
g++ --version
gdb --version
```

### 3. 編寫 c++ 程式

創建一個 HelloWorld.cpp 文件

```cpp
#include <iostream>

using namespace std;

int main(){
    cout << "Hello, world!";
    return 0;
}
```

### 4. 編譯 c++ 程式

在 vscode 中使用快捷鍵 "ctr + shift + B" 來執行編譯

### 5. 延伸工具

```base
# vacode C++ 語法識別工具
套件識別碼: ms-vscode.cpptools

# 快速運行 .exe 檔工具
套件識別碼: brandonfowler.exe-runner
```

 

## API reference docs

[API Web Docs](https://cplusplus.com/reference/)