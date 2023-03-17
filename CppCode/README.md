# C++
## Install C++ Compare in VSCode
### 1. 安裝 VSCode C++ 延伸模組
```base
延伸模組識別碼: ms-vscode.cpptools
```

### 2. 安裝 MSYS2
```base
# 至官網下載最新版: https://www.msys2.org/
# 或使用此連結下載: https://github.com/msys2/msys2-installer/releases/download/2023-01-27/msys2-x86_64-20230127.exe
# 完成後使用下方命令開始安裝 MinGW
pacman -S --needed base-devel mingw-w64-x86_64-toolchain
```

### 3. 檢查系統環境
```
gcc --version
g++ --version
gdb --version
```

### 4. 編寫 c++ 程式
創建一個 HelloWorld.cpp 文件
```cpp
#include <iostream>

using namespace std;

int main(){
    cout << "Hello, world!";
    return 0;
}
```

### 5. 編譯 c++ 程式
在 vscode 中使用快捷鍵 "ctr + shift + b" 來執行編譯

### 6. 延伸工具
```base
# 快速運行 .exe 檔工具
vscode擴充套件識別碼: brandonfowler.exe-runner
```
