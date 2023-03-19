# C++ Basic

## Libs

[C++ libs docs](https://cplusplus.com/reference/)

標頭戴 c 表示此庫在 C 時代就已經存在了

```cpp
#include <iostream> // I/O方法庫
#include <climits> // 整數類型的極限大小 INT_MAX, INT_MIN ...
#include <cmath> // 數學運算庫 sqrt(), abs() ...
#include <iomanip> // 設定打印精度 setprecision()
```

## Basic C++ program

```cpp
#include <iostream> // I/O流處裡方法庫。
using namespace std; // 基本 standard 命名空間標示，裡面標示了 cout/cin 等多種參數名，所代表的意義。

int main()
{
    cout << "Hello, world!";
    return 0;
}
```

## Basic Data Type

unsigned 表示為純正數類型

| Category           | Type           | Bytes |
| ------------------ | -------------- | ----- |
| Integer            | bool           | 1     |
| Integer            | char           | 1     |
| Integer            | int            | 4     |
| Integer            | short          | 2     |
| Integer            | long           | 4     |
| Integer            | unsigned int   | 4     |
| Integer            | unsigned short | 2     |
| Integer            | unsigned long  | 4     |
| Fractional numbers | float          | 4     |
| Fractional numbers | double         | 8     |

## Type Casting

1. static_cast<T>()

2. dynamic_cast<T>()

3. const_cast<T>()

4. reinterpret_cast<T>()

```cpp
#include <iostream>
using namespace std;

int main()
{
    int grade1 = 0;
    int grade2 = 0;

    cout << "Input two grades: " << endl;
    cin >> grade1 >> grade2;

    // 使用 static_cast 將 int 相加解果轉換為 float，以利呈現平均計算精度
    float avgGrade = static_cast<float>(grade1 + grade2);
    avgGrade /= 2;

    cout << "Average grade: " << avgGrade << endl;

    return 0;
}
```

## Arrays

1. C++ 不會自動幫忙初始化陣列的值

2. C++ 指標越位不會有提示，需要自己小心(若讀取到其它應用程序的進程則會報 Run time error)

3. 假設你有兩個 Arrays，a1 和 a2，就算大小與型態都相同也<b><mark>不能</mark></b>夠寫成 a1 = a2，必須寫for 迴圈一個一個拷貝。

```cpp
#include <iostream>
using namespace std;

int main()
{
    // 初始化 int Arrays 所有元素為 0
    cout << "@Initialization Arrays:" << endl;
    int array[100] = {0};
    for (int i = 0; i < sizeof(array) / sizeof(array[0]); i++)
    {
        cout << array[i] << " ";
        if (i % 10 == 9)
        {
            cout << endl;
        }
    }
}
```

## Switch case

```cpp
int main(){
    int inputNumber = 1;
    switch(inputNumber){
        case 1:
            break;
        case 2:
            break;
        default:
            ...    
    }
}
```

## Function

### Define a Function

1. 宣告 Function prototype 

2. 實作 Function body

```cpp
#include <iostream>
using namespace std;

// 1. 宣告 Function
int add(int num1, int num2);

int main()
{
    int input1 = 0, input2 = 0;
    cout << "Input two numbers: ";
    cin >> input1 >> input2;

    int result = add(input1, input2);
    cout << "Two number total: " << result;

    return 0;
}

// 2. 實作 Function body
int add(int num1, int num2)
{
    return num1 + num2;
}
```

### Static Variable

* 靜態變數

```cpp
int testStaticVariable()
{    
    // 使用 static variable 變數會一直存在，不會被銷毀，直到程式結束
    static int count = 0;
    return ++count;
}
```

### Constant Parameters

- 常量參數

```cpp
// 加上 const 表示選告參數不能夠被修改
void printArray(const int array[], int len)
{
    cout << "[";
    for (int i = 0; i < len; i++)
    {
        cout << array[i];
        if (i < len - 1)
        {
            cout << ", ";
        }
    }
    cout << "]";
    return;
}
```

### Over Loading

- 相同的方法名實現不同的方法功能

- 根據傳入的參數決定要調用的 function (不看 return type)

```cpp
#include <iostream>
using namespace std;

// 函數多載宣告，同樣的方法名，不同的參數型態
void print(int intData);
int print(char charData);
void print(bool boolData);

int main()
{
    // 根據傳參決定要調用哪一個 function
    print(10);
    print('A');
    print(false);
    return 0;
}

void print(int intData)
{
    cout << "Input is integer type: " << intData << endl;
}
int print(char charData)
{
    cout << "Input is character type: " << charData << endl;
    return 0;
}
void print(bool boolData)
{
    cout << "Input is boolean type: " << boolData << endl;
}
```

### Default arguments

- 在選告函數的 prototype 時，指定參數預設值

```cpp
#include <iostream>
using namespace std;

// 在 prototype 傳入參數宣告預設的初始值
int circleArea(double radius, double pi = 3.14);

int main()
{
    // 使用預設的 pi value
    cout << circleArea(5) << endl;
    // 使用自定義的 pi value
    cout << circleArea(8, 3.1415926) << endl;
    return 0;
}

int circleArea(double radius, double pi)
{
    return radius * radius * pi;
}
```



## Pointers

### Base

1. 變數取"址" : 使用 &variable 取出變數記憶體地址。

2. 指標取"值" : 使用 *pointer 取出指標指向記憶體地址的值。

3. 宣告指標
   
   1. 記憶體地址指標 <mark>int *pointer = &variable; </mark> 可存放變數記憶體地址。
   
   2. 參照指標 <mark> int &referencePointer = variable; </mark>   可直接參照變數的值，直接操作原始值。
   
   3. 空指標 <mark> int *pointer = nullptr; </mark> 宣告指標目前未指向任何地址，若不小心 使用了 <mark> *pointer</mark> 取值，則會丟出 run time error 方便 debug。

```cpp
#include <iostream>
using namespace std;

int main()
{
    // 1. 宣告一個變數
    int num = 10;

    // 2. 宣告一個變數
    int *ptrNum = nullptr;

    // 3. 存儲變數 num 的記憶體位置
    ptrNum = &num;

    // 4. 對指向的記憶體位置的值做出修改，會直接影響到 num 變數
    *ptrNum = 5; // 此時 num = 5

    // 5. 宣告參照指針直接指向 num 變數
    int &refNum = num;

    // 6 對參照指標做出修改會直接引響到被參照的 num 變數
    refNum = 80; // 此時 num = 80

    return 0;
}
```



### Swap by reference

```cpp
#include <iostream>
using namespace std;

void swap(int &num1, int &num2);

int main()
{
    int a = 2, b = 5;
    cout << "a: " << a << ", b: " << b << endl;
    // output: a: 2, b: 5

    swap(a, b);
    cout << "a: " << a << ", b: " << b << endl;
    // output: a: 5, b: 2    
    return 0;
}

void swap(int &num1, int &num2)
{
    int temp = num1;
    num1 = num2;
    num2 = temp;
}
```



### Swap by address

```cpp
#include <iostream>
using namespace std;

void swap(int *num1, int *num2);

int main()
{
    int a = 2, b = 5;
    cout << "a: " << a << ", b: " << b << endl;
    // output: a: 2, b: 5

    swap(a, b);
    cout << "a: " << a << ", b: " << b << endl;
    // output: a: 5, b: 2
    return 0;
}

void swap(int *num1, int *num2)
{
    int temp = *num1;
    *num1 = *num2;
    *num2 = temp;
}
```
