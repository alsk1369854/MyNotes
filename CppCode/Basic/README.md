# C++ Basic

## Libs

[C++ libs docs](https://cplusplus.com/reference/)

標頭戴 c 表示此庫在 C 時代就已經存在了

```cpp
#include <iostream> // I/O函式庫
#include <climits> // 整數類型的極限大小 INT_MAX, INT_MIN ...
#include <cmath> // 數學運算庫 sqrt(), abs() ...
#include <iomanip> // 設定打印精度 setprecision()
#include <cstring> // 字串處理函式庫，strlen, strcmp, strcat, strcpy, strtok
#include <cstdlib> // 字串轉 int or float 函式庫，(atoi, atof, itoa)
#include <ctime> // 時間計時
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

- unsigned 表示為純正數類型

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

### Arrays Parameter

- Array 作為 Function 參數傳遞宣告時，2維以上大小必須寫死。  

- 1維可寫可不寫，就算寫了 compiler 也會忽視掉。

```cpp
#include <iostream>
using namespace std;

const int MAX_LEN_ROW = 3;
const int MAX_LEN_COL = 3;

// array 作為參數傳遞，的二個維度開始必須寫死(C++ 語法規則)
void arrayToString(int array[][MAX_LEN_COL], int row, int col);

int main()
{
    int array[MAX_LEN_ROW][MAX_LEN_COL] =
        {{1, 2},
         {5, 4},
         {3, 7}};

    arrayToString(array, MAX_LEN_ROW, MAX_LEN_COL);
    /*
    output:
    [[1, 2, 0]
    [5, 4, 0]
    [3, 7, 0]]
    */

    return 0;
}

void arrayToString(int array[][MAX_LEN_COL], int row, int col)
{
    cout << "[";
    for (int i = 0; i < row; i++)
    {
        cout << "[";
        for (int j = 0; j < col; j++)
        {
            cout << array[i][j];
            if (j != col - 1)
            {
                cout << ", ";
            }
        }
        cout << "]";
        if (i != row - 1)
        {
            cout << "\n";
        }
    }
    cout << "]";
}
```

### Dynamic Arrays

1. 使用 int *array  = new int [5]; 的方式動態宣告陣列。

2. 使用動態宣告，<mark>不會在 function block 結束後自動釋放記憶體</mark>，需要手動加上 **delete [] array;** 來釋放記憶體 ("推薦"釋放結束後直接將指針指向空 **array = nullptr;**) 

```cpp
#include <iostream>
using namespace std;

void arrayToString(int *array, int len);

int main()
{
    int len = 0;
    cout << "Enter a number to initialization array length: ";
    cin >> len;

    // 1. 動態宣告 array
    int *array = new int[len];

    // 2. 需要自己手動初始化
    for (int i = 0; i < len; i++)
    {
        array[i] = 0;
    }
    arrayToString(array, len);
    cout << endl;

    // 3. 使用結束要使用 delete 關鍵字釋放記憶體
    delete[] array;
    array = nullptr; // 指針直接加上 nullptr 以避免，之後誤用 reference 取值

    // tip: sizeof 計算長度會錯誤，因為 array 是 8 bit 的指針記憶體大小，array[0] 則是 int 大小。
    int size = sizeof(array) / sizeof(array[0]);
    cout << "Array size is: " << size; // out put: 2

    return 0;
}

void arrayToString(int *array, int len)
{
    cout << "[";
    for (int i = 0; i < len; i++)
    {
        cout << array[i];
        if (i != len - 1)
        {
            cout << ", ";
        }
    }
    cout << "]";
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

### Arrays Parameter

- 請參考 Aarrys > Arrays Parameter

## Pointers

### Base

1. 變數取"址" : 使用 &variable 取出變數記憶體地址。

2. 指標取"值" : 使用 *pointer 取出指標指向記憶體地址的值。

3. 宣告指標
   
   1. 記憶體地址指標 int *pointer = &variable; 可存放變數記憶體地址。
   
   2. 參照指標 int &referencePointer = variable; 可直接參照變數的值，直接操作原始值。
   
   3. 空指標 int *pointer = nullptr; 宣告指標目前未指向任何地址，若不小心 使用了 *pointer 取值，則會丟出 run time error 方便 debug。

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

    swap(&a, &b);
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

## Classes

### Base : Constructor and Destructor

```cpp
#include <iostream>
using namespace std;

// 子物件
class SubObject
{
public:
    // 建構子
    SubObject() { cout << "SubObject Constructor" << endl; };
    // 解構子(在物件銷毀時自動調用)
    ~SubObject() { cout << "~SubObject Destructor" << endl; };
};

// 主物件
class MyVector
{
public:
    // 建構子
    MyVector();
    MyVector(int len, int defaultValue = 0);
    // 解構子(在物件銷毀時自動調用)
    ~MyVector();
    // 物件方法
    void print();

private:
    // 物件屬性
    int len;
    int *vector;
    SubObject subObject;
};

int main()
{
    // 創建 MyVector 物件
    MyVector myVector(10, 5);
    // 調用 MyVector 方法
    myVector.print();
    /*
        SubObject Constructor
        MyVector Constructor
        Len: 10
        [5, 5, 5, 5, 5, 5, 5, 5, 5, 5]
        ~MyVector Destructor
        ~SubObject Destructor
    */
    return 0;
}

MyVector::MyVector()
{
    cout << "MyVector Constructor" << endl;
    len = 0;
    vector = nullptr;
}

MyVector::MyVector(int len, int defaultValue)
{
    cout << "MyVector Constructor" << endl;
    MyVector::len = len;
    vector = new int[len];
    for (int i = 0; i < len; i++)
    {
        vector[i] = defaultValue;
    }
}

MyVector::~MyVector()
{
    cout << "~MyVector Destructor" << endl;
    delete[] vector;
}

void MyVector::print()
{
    cout << "Len: " << len << endl;
    cout << "[";
    for (int i = 0; i < len - 1; i++)
    {
        cout << vector[i] << ", ";
    }
    cout << vector[len - 1] << "]" << endl;
}
```

## Program Arguments

```cpp
#include <iostream>
using namespace std;

/**
 * int argc: 參數數量
 * char *argv[]: 參數字串
 */
int main(int argc, char *argv[])
{
    for (int i = 0; i < argc; i++)
    {
        cout << "argument " << i + 1 << " : " << argv[i] << endl;
    }
    /*
    Input:
        {path/Program.exe} hello, world!

    Output:
        argument 1 : C:\Users\ChiaMing\Desktop\CppCode\Basic\ProgramArguments\ProgramArguments.exe
        argument 2 : hello
        argument 3 : world!
    */
    return 0;
}
```

## Self-defined data type

### struct (Constructor)

```cpp
#include <iostream>
#include <cmath>
#include <cstring>
using namespace std;

struct Vector
{
    int x;
    int y;
    double length()
    {
        return sqrt(x * x + y * y);
    };
    char *toString(char *str);
};

int main()
{
    Vector vector = {5, 3};
    cout << "vector.x: " << vector.x << endl;
    // vector.x: 5

    cout << "vector.y: " << vector.y << endl;
    // vector.y: 3

    cout << "vector.length(): " << vector.length() << endl;
    // vector.len(): 5.83095

    char vectorString[1000];
    vector.toString(vectorString);
    cout << vectorString;
    // Vector: {x: 5, y: 3}

    return 0;
}

char *Vector::toString(char *str)
{

    strcpy(str, "Vector: {");

    char strX[50];
    itoa(x, strX, 10);
    strcat(str, "x: ");
    strcat(str, strX);
    strcat(str, ", ");

    char strY[50];
    itoa(y, strY, 10);
    strcat(str, "y: ");
    strcat(str, strY);

    strcat(str, "}\n");

    return str;
}
```

### typedef (Type definition)

```cpp
#include <iostream>
using namespace std;

typedef long int number;

int main()
{
    number num = 5;
    cout << "num: " << num << endl;
    // num: 5

    return 0;
}
```

### union

### enum

## Sort String

### Sort name with pointer

- 使用指針，交換地址指向

```cpp
#include <iostream>
#include <cstring>
using namespace std;

const int NAME_LIST_LEN = 5;
const int NAME_LEN = 10;

void strSwap(char *&str1, char *&str2);
// void strSwap2(char *str1, char *str2);

int main()
{
    char nameList[NAME_LIST_LEN][NAME_LEN] = {
        {"Ming"},
        {"Han"},
        {"Mark"},
        {"JJ"},
        {"SNinjo"}};

    char *nameListPtr[5] = {
        nameList[0],
        nameList[1],
        nameList[2],
        nameList[3],
        nameList[4]};

    for (int i = 0; i < NAME_LIST_LEN; i++)
    {
        for (int j = 0; j < NAME_LIST_LEN - i - 1; j++)
        {
            if (strcmp(nameListPtr[j], nameListPtr[j + 1]) > 0)
            {
                strSwap(nameListPtr[j], nameListPtr[j + 1]);
                // strSwap2(nameListPtr[j], nameListPtr[j + 1]);
            }
        }
    }

    for (int i = 0; i < NAME_LIST_LEN; i++)
    {
        cout << i << " : " << nameListPtr[i] << endl;
    }
    /*
        0 : Han
        1 : JJ
        2 : Mark
        3 : Ming
        4 : SNinjo
    */

    return 0;
}

void strSwap(char *&str1, char *&str2)
{
    char *temp = str1;
    str1 = str2;
    str2 = temp;
}

// void strSwap2(char *str1, char *str2)
// {
//     char temp[NAME_LEN];
//     strcpy(temp, str1);
//     strcpy(str1, str2);
//     strcpy(str2, temp);
// }
```

### Sort name with string copy

- 使用字串複製，實現字串排序

```cpp
#include <iostream>
#include <cstring>
using namespace std;

const int NAME_LIST_LEN = 5;
const int NAME_LEN = 10;

void strSwap(char *str1, char *str2);

int main()
{
    char nameList[NAME_LIST_LEN][NAME_LEN] = {
        {"Ming"},
        {"Han"},
        {"Mark"},
        {"JJ"},
        {"SNinjo"}};

    for (int i = 0; i < NAME_LIST_LEN; i++)
    {
        for (int j = 0; j < NAME_LIST_LEN - i - 1; j++)
        {
            if (strcmp(nameList[j], nameList[j + 1]) > 0)
            {
                strSwap(nameList[j], nameList[j + 1]);
            }
        }
    }

    for (int i = 0; i < NAME_LIST_LEN; i++)
    {
        cout << i <<  " : " << nameList[i] << endl;
    }
    /*
        0 : Han
        1 : JJ
        2 : Mark
        3 : Ming
        4 : SNinjo
    */

    return 0;
}

void strSwap(char *str1, char *str2)
{
    char temp[NAME_LEN];
    strcpy(temp, str1);
    strcpy(str1, str2);
    strcpy(str2, temp);
}
```

## Random Number

```cpp
#include <iostream>
#include <ctime>
#include <cstdlib>
using namespace std;

int main()
{
    // newR = (943285761 * oldR + 18763571) mod 32767
    // 設定隨機亂數的參數為時間
    srand(time(0));
    for (int i = 0; i < 10; i++)
    {
        // 100 ~ 199 的隨機數
        int randNumber = (rand() % 100) + 100;
        cout << randNumber << endl;
    }
    return 0;
}
```

## cstring Library (字串處裡庫)

### 字串長度

- size_t strlen(const char *str);

```cpp
#include <iostream>
#include <cstring>
using namespace std;

/**
 * size_t strlen(const char *str);
 * 字串長度 
*/
int main()
{
    char strValue[20] = "Hello, World!";
    int strLen = strlen(strValue);
    cout << "strLen: " << strLen << endl;
    // strLen: 13

    cout << "sizeof(strValue): " << sizeof(strValue) << endl;
    // sizeof(strValue): 20

    return 0;
}
```

### 字串中查找字元

- char *strchr(const char *str, int character);

```cpp
#include <iostream>
#include <cstring>
using namespace std;

/**
 * char *strchr(const char *str, int character);
 * 檢查字串中使否存在字元
 *
 * char *str: 被檢查的字串
 * int character: 要尋找的字元
 *
 * return char*:  字元所在的指標位置 or 若無則回傳 nullptr
 */
int main()
{
    char str[100] = "this is book";
    char *p = strchr(str, 'i');

    // 由於 cout 被 overloading 改寫，導致會連續印出整個字串
    cout << "p: " << p << endl;   // p: is is book
    cout << "*p: " << *p << endl; // *p: is is book

    // index
    cout << "(p - str): " << (p - str) << endl; // (p - str): 2

    // 未找到則回傳 nullptr
    char *p2 = strchr(str, 'A');
    cout << "(p2 == nullptr): " << (p2 == nullptr) << endl; // (p2 == nullptr): 1

    return 0;
}
```

### 字串中查找子字串

- char *strstr(const char *str1, const char *str2);

```cpp
#include <iostream>
#include <cstring>
using namespace std;

/**
 * char *strstr(const char *str1, const char *str2);
 * 在字串中尋找子字串
 *
 * const char *str1: 要查找的目標字串
 * const char *str2: 要查找的目標子字串
 *
 * return char *: 回傳找到的子字串記憶體位置起始點，若無找到則回傳 nullptr
 */
int main()
{
    char str1[100] = "this is a book";
    char *p = strstr(str1, "is");

    // 由於 cout 被 overloading 改寫，導致會連續印出整個字串
    cout << "p: " << p << endl;   // p: is is book
    cout << "*p: " << *p << endl; // *p: i

    // index
    cout << "(p - str): " << (p - str1) << endl; // (p - str): 2

    // 未找到則回傳 nullptr
    char *p2 = strstr(str1, "ABC");
    cout << "(p2 == nullptr): " << (p2 == nullptr) << endl; // (p2 == nullptr): 1

    return 0;
}
```

### 字串比較

- int strcmp(const char *str1, const char *str2);

- int strncmp(const char *str1, const char *str2, unsigned int num);

```cpp
#include <iostream>
#include <cstring>
using namespace std;

/**
 * int strcmp(const char *str1, const char *str2);
 * int strncmp(const char *str1, const char *str2, unsigned int num);
 * 字串比較，根具 ASCII 碼比較字串大小
 *
 * const char *str1: 第一個被比較的字串
 * const char *str2: 第二個被比較的字串
 * unsigned int num: 指定比較的字符個數
 *
 * return int: 若相等回傳 0 ; 若 str1 > str2 回傳 "正數" ; 若 str1 < str2 回傳 "負數"
 */
int main()
{
    char str1[10] = "them";
    char str2[10] = "they";

    int cmp1 = strcmp(str1, str2);
    cout << "cmp1: " << cmp1 << endl;
    // cmp1: -1

    int cmp2 = strcmp(str2, str1);
    cout << "cmp2: " << cmp2 << endl;
    // cmp2: 1

    int cmp3 = strncmp(str1, str2, 3);
    cout << "cmp3: " << cmp3 << endl;
    // cmp3: 0

    return 0;
}
```

### 字串複製

- char *strcpy(char *dest, const char *source); // 複製後<mark>"會"</mark>在最後加上 '\0'

- char *strncpy(char *dest, const char *source, size_t count); // 如果<mark>指定要複製的字串長度，小於要被複製的目標字串，複製後則"不會"</mark>在最後加上 '\0'

```cpp
#include <iostream>
#include <cstring>
using namespace std;

/**
 * char *strcpy(char *dest, const char *source);
 * char *strncpy(char *dest, const char *source, size_t count);
 * 字串複製
 * 注意: 
 *      strcpy() 會連同 '\0' 一起複製
 *      strncpy() 不會連同 '\0' 一起複製
 *
 * char *dest: 複製後的字串要存放的位址
 * char *source: 要被複製的目標字串
 * size_t count: 只定要複製的字串長度
 *
 * return char*: 會傳複製後的字串記憶體位置
 */
int main()
{
    char strSource[30] = "Hello, world!";

    char cpy1[30];
    char *cpyStr1 = strcpy(cpy1, strSource);
    cout << "cpyStr1: " << cpyStr1 << endl;
    // cpyStr1: Hello, world!

    char cpy2[30];
    char *cpyStr2 = strcpy(cpy2, strSource + 7);
    cout << "cpyStr2: " << cpyStr2 << endl;
    // cpyStr2: world!

    char *cpyStr3 = strncpy(cpy2, strSource, 5);
    cout << "cpyStr3: " << cpyStr3 << endl;
    // cpyStr3: Hello!


    return 0;
}
```

### 字串拼接

- char *strcat(char *dest, const char *source);

- char *strncat(char *dest, const char *source, size_t count);

```cpp
#include <iostream>
#include <cstring>
using namespace std;

/**
 * char *strcat(char *dest, const char *source);
 * char *strncat(char *dest, const char *source, size_t count);
 * 字串拼接，從 dest 字串第一個 '\0' 開始拼接
 *
 * char *dest: 主字串
 * char *source: 要拼接在主字串後的字串
 * size_t count: 指定要拼接的字串長度
 *
 * return char*: 主字串記憶體位置
 */
int main()
{
    char strDest[20] = "Hello";
    char *strCat1 = strcat(strDest, ", World!");
    cout << "strCat1: " << strCat1 << endl;
    // strCat1: Hello, World!

    char *strCat2 = strncat(strDest, "ABCDEFG", 3);
    cout << "strCat2: " << strCat2 << endl;
    // strCat2: Hello, World!ABC

    // 確保指標不會溢位
    char *strCat3 = strncat(strDest, "abcdefghijklmnopqrstuvwxyz", sizeof(strDest) - strlen(strDest) - 1);
    cout << "strCat3: " << strCat3 << endl;
    // strCat3: Hello, World!ABCabc

    return 0;
}
```

### 字串分割

- char *strtok(char *str, const char *delim);

```cpp
#include <iostream>
#include <cstring>
using namespace std;

const int INPUT_STRING_LEN = 1000;
const int WORD_LIST_LEN = 100;
const int WORD_LEN = 50;


/**
 * char *strtok(char *str, const char *delim);
 * 字串分割
 * 在給定一個字串後，若要繼續分割，就給 str 參數傳入 nullptr 這將會返回，下一個分割字串的記憶體位置。
 * 
 * char *str: 要分割的字串
 * const char *delim: 作為分割字符的字符集
 * 
 * return char*: 分割後的第一個字串記憶體地址
*/
int main()
{
    // www.chaiming.com/han\\JJ/mark.sninjo]
    char inputString[INPUT_STRING_LEN];
    cin >> inputString;
    char delim[] = "./\\";

    char wordList[WORD_LIST_LEN][WORD_LEN];

    int wordCount = 0;
    char *p = strtok(inputString, delim);
    while (p != nullptr)
    {
        strcpy(wordList[wordCount], p);
        wordCount++;
        p = strtok(nullptr, delim);
    }

    for (int i = 0; i < wordCount; i++)
    {
        cout << i << " : " << wordList[i] << endl;
    }
    /*
        0 : www
        1 : chaiming
        2 : com
        3 : han
        4 : JJ
        5 : mark
        6 : sninjo]
    */

    return 0;
}
```

## cstdlib Library (字串與數字轉譯庫)

### 字串轉文字

- int atoi(const char *str);

- double atof(const char *str);

```cpp
#include <iostream>
#include <cstdlib>
using namespace std;

/**
 * int atoi(const char *str);
 * 字串轉 int
 *
 * double atof(const char *str);
 * 字串轉 double
 */
int main()
{
    char intStr[10] = "-123";
    int intValue = atoi(intStr);
    cout << "intValue * 2: " << intValue * 2 << endl;
    // intValue * 2: -246

    char floatStr[10] = "3.14";
    double floatValue = atof(floatStr);
    cout << "floatValue / 2: " << floatValue / 2 << endl;
    // floatValue / 2: 1.57

    return 0;
}
```

### 數字轉字串 (Integer)

- char *itoa(int value, char *str, int base);

```cpp
#include <iostream>
#include <cstdlib>
using namespace std;

/**
 * char *itoa(int value, char *str, int base);
 * 數字轉字串
 *
 * int value: 要轉為字串的數字
 * char *str: 轉換後的字串要存放的位置
 * int base: 要轉換成多少進制
 *
 * return char*: 字串的記憶體位置
 */
int main()
{
    char str[50];
    int num = 15;
    char *numStrOf10 = itoa(num, str, 10);
    cout << "numStrOf10: " << numStrOf10 << endl;
    // numStrOf10: 15

    char *numStrOf2 = itoa(num, str, 2);
    cout << "numStrOf2: " << numStrOf2 << endl;
    // numStrOf2: 1111

    return 0;
}
```

## ctime Library (時間計算)

### 程式計時

```cpp
#include <iostream>
#include <ctime>
using namespace std;

int main()
{

    clock_t startClock = clock();
    for (int i = 0; i < 999999999; i++)
    {
        ;
    }
    clock_t finishClock = clock();

    cout << "startClock: " << startClock << endl;
    cout << "finishClock: " << finishClock << endl;
    cout << "Program running seconds: " << static_cast<double>(finishClock - startClock) / CLOCKS_PER_SEC << endl;
    /*
    startClock: 0
    finishClock: 1430
    Program running seconds: 1.43
    */

    return 0;
}
```
