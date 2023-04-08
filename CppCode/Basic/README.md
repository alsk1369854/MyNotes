# C++ Basic

## Libs

[C++ libs docs](https://cplusplus.com/reference/)

標頭戴 c 表示此庫在 C 時代就已經存在了

```cpp
#include <iostream> // I/O函式庫
#include <cmath> // 數學運算庫 sqrt(), abs() ...
#include <string> // 字串處裡函式庫
#include <vector> // 陣列函式庫
#include <queue> // queue
#include <stack> // stack
#include <set> // set
#include <map> // map
#include <algorithm> // sort、find_if
#include <stdexcept> // 另外處裡函式庫
#include <climits> // 整數類型的極限大小 INT_MAX, INT_MIN ...
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

## 資料結構 (Data Structure)

### Array List

```cpp
#include <iostream>
#include <vector>
using namespace std;

int main()
{
    // create Array List
    vector<int> arrayList = vector<int>();

    // add values
    for (int i = 0; i < 10; i++)
    {
        arrayList.push_back(i);
    }

    // print values
    // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
    for (int i = 0; i < arrayList.size(); i++)
    {
        int &value = arrayList.at(i);
        cout << value << ", ";
    }

    return 0;
}
```

### Queue

```cpp
#include <iostream>
#include <queue>
using namespace std;

int main()
{
    // create queue
    queue<int> qu;

    // add values
    for (int i = 0; i < 10; i++)
    {
        qu.push(i);
    }

    // print values
    // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
    while (!qu.empty())
    {
        int &value = qu.front(); // 讀最前項
        cout << value << ", ";
        qu.pop(); // 吐出最前項
    }
    cout << endl;
    cout << "qu.size(): " << qu.size();

    return 0;
}
```

### Double Ended Queue

```cpp
#include <iostream>
#include <deque>

using namespace std;

int main() {
    deque<int> d = {1, 2, 3, 4};  // [1, 2, 3, 4]

    d.push_back(5); // [1, 2, 3, 4, 5]
    d.pop_front(); // [2, 3, 4, 5]
    d.push_front(0); // [0, 2, 3, 4, 5]
    d.pop_back(); // [0, 2, 3, 4]

    // 印出 deque 內所有內容, c++11 才支援
    // 0 2 3 4
    for (int &i : d) {
        cout << i << " ";
    }
    cout << "\n";

    // 0 2 3
    cout << d[0] << " " << d[1] << " " << d[2] << "\n";

    return 0;
}
```

### Stack

```cpp
#include <iostream>
#include <stack>
using namespace std;

int main(){
    // create stack
    stack<int> myStack;

    // push values to stack
    for(int i=0; i<10; i++){
        myStack.push(i);
    }

    // pop stack values
    // 10, 8, 7, 6, 5, 4, 3, 2, 1, 0,
    while(!myStack.empty()){
        int &value = myStack.top();
        cout << value << ", ";
        myStack.pop();
    }
    return 0;
}
```

### Set

```cpp
#include <iostream>
#include <set>
using namespace std;

int main()
{
    // create set
    set<int> mySet = {3, 1};

    // add values to set
    mySet.insert(2);
    mySet.insert(5);
    mySet.insert(4);
    mySet.insert(5);
    mySet.insert(4);

    // delete value 2
    mySet.erase(2);

    // print set values
    // 1 3 4 5
    for (const int &s : mySet)
    {
        cout << s << " ";
    }
    cout << "\n";

    return 0;
}
```

### Unordered Set

```cpp
#include <iostream>
#include <unordered_set>
using namespace std;

int main() {
    // create unordered set
    unordered_set<int> myUnorderedSet{2, 4, 6, 8};

    // add value to unordered set
    myUnorderedSet.insert(4);

    // delete value 2 
    myUnorderedSet.erase(2);

    // print set values
    // 8 6 4
    for (const auto &s : myUnorderedSet) {
        cout << s << " ";
    }
    cout << "\n";

    return 0;
}
```

### Map

```cpp
#include <iostream>
#include <map>
#include <string>
using namespace std;

int main()
{
    // creat map
    map<string, int> myMap = {
        {"one", 1}};

    // add map key value
    myMap["two"] = 2;
    myMap["three"] = 3;

    // get value by key
    // myMap["three"]: 3
    cout << "myMap[\"three\"]: " << myMap["three"] << endl;

    // check key exists
    // myMap.find("four"): 0
    cout << "myMap.find(\"four\"): " << (myMap.find("four") != myMap.end()) << endl;

    // foreach map
    /*
    key: one, value: 1
    key: three, value: 3
    key: two, value: 2
    */
    for (pair<string, int> entry : myMap)
    {
        cout << "key: " << entry.first << ", value: " << entry.second << endl;
    }

    // clear map
    myMap.clear();
    return 0;
}
```

### Unordered Map

```cpp
#include <iostream>
#include <unordered_map>
#include <string>
using namespace std;

int main()
{
    // creat map
    unordered_map<string, int> myMap = {
        {"one", 1}};

    // add map key value
    myMap["two"] = 2;
    myMap["three"] = 3;

    // get value by key
    // myMap["three"]: 3
    cout << "myMap[\"three\"]: " << myMap["three"] << endl;

    // check key exists
    // myMap.find("four"): 0
    cout << "myMap.find(\"four\"): " << (myMap.find("four") != myMap.end()) << endl;

    // foreach map
    /*
    key: three, value: 3
    key: two, value: 2
    key: one, value: 1
    */
    for (auto [key, value] : myMap)
    {
        cout << "key: " << key << ", value: " << value << endl;
    }

    // clear map
    myMap.clear();
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

### Vector library

- 導入: #include<vector>

- 選告: vector<int> intList;

- 方法:
  
  - 長度\: intList.size();
  
  - 是否為空: intList.empty();
  
  - 取值: 
    
    1. intList[1];
    
    2. intList.at(1);
  
  - 第一個: intList.front();
  
  - 最後一個: intList.back();
  
  - 添加到最後: intList.push_back(1);
  
  - 吐出最後一個: intList.pop_back();
  
  - 插入: intList.insert(intList.begin() + 1, 8);
  
  - 刪除: intList.erase(intList.begin() + 2);
  
  - 兩個vector交換: intList.swap(tempVector);
  
  - 清空: intList.clear();

```cpp
#include <iostream>
#include <vector>
#include <string>
using namespace std;

class MyObject
{
public:
    int v;
    MyObject(int v) : v(v){};
};

int main()
{
    // 一般 vector
    vector<int> intList;
    intList.push_back(1);
    intList.push_back(2);
    intList.push_back(3);
    for (int i = 0; i < intList.size(); i++)
    {
        cout << intList[i] << ", ";
    }
    cout << endl;

    // Object vector
    vector<MyObject *> myObjectList;
    myObjectList.push_back(new MyObject(5));
    myObjectList.push_back(new MyObject(4));
    myObjectList.push_back(new MyObject(3));
    for (int i = 0; i < myObjectList.size(); i++)
    {
        cout << myObjectList[i]->v << ", ";
    }

    // 釋放指針 vector 記憶體
    while (myObjectList.size() > 0)
    {
        delete myObjectList.back();
        myObjectList.pop_back();
    }

    return 0;
}
```

### Sort

- `#include <algorithm>`

- sort(iterator begin, iterator end, sort method);

```cpp
#include <iostream> 
#include <algorithm>  // sort libaray
#include <vector>

using namespace std;

bool myCompare(int a, int b)
{
    return a > b; // 降序排列
}

int main()
{
    int arr[] = {5, 4, 1, 7, 3, 8, 9, 10, 6, 2};
    vector<int> v(arr, arr + 10);

    // 升序 (default)
    cout << "ascending order: ";
    sort(v.begin(), v.begin() + 5);
    for (int i : v)
    {
        cout << i << " ";
    }
    cout << endl;

    // 降序
    cout << "descending order:";
    sort(v.begin(), v.end(), [](int x, int y)
         { return x > y; });
    for (int i : v)
    {
        cout << i << " ";
    }
    cout << endl;

    return 0;
}
```

### Find item

- `#include <algorithm>`

- find_if(iterator begin, iterator end, find method);

```cpp
#include <iostream> 
#include <algorithm>  // sort libaray
#include <vector>

using namespace std;

bool myCompare(int a, int b)
{
    return a > b; // 降序排列
}

int main()
{
    int arr[] = {5, 4, 1, 7, 3, 8, 9, 10, 6, 2};
    vector<int> v(arr, arr + 10);

    // 升序 (default)
    cout << "ascending order: ";
    sort(v.begin(), v.begin() + 5);
    for (int i : v)
    {
        cout << i << " ";
    }
    cout << endl;

    // 降序
    cout << "descending order:";
    sort(v.begin(), v.end(), [](int x, int y)
         { return x > y; });
    for (int i : v)
    {
        cout << i << " ";
    }
    cout << endl;

    return 0;
}
```

## String

### Base

- 宣告: string myStr = "Hello";

- 取 char: char myChar = myStr[0];

- 讀取輸入字串:
  
  - 讀取一行: getline(cin, myStr);
  
  - 自訂義段行: getline(cim, myStr, '#');

- 擷取字串: 
  
  - 指定地方開始擷取指定長度: string subStr1 = myStr.substr(0, 5);
  
  - 指定地方開始擷取至尾: string subStr1 = myStr.substr(0, 5);

- 尋找文字: 
  
  - 尋找文字: size_t strIndex = myStr.find("Hello");
  
  - 落未找到文字則會回傳 size_t string::npos

- 插入文字: string insertStr = myStr.insert(2, "ABC");

- 替換文字: string replaceStr = myStr.replace(3, 2, "AB");

- 刪除文字: string eraseStr = myStr.erase(2, 3);

- string 轉 cstring: const char *cStr = myStr.c_str();

- 字串轉數字:
  
  - int: int myInt = stoi(intStr);
  
  - float: float myFloat = stof(floatStr);
  
  - double: double myDouble = stod(doubleStr);

- 數字轉字串:
  
  - string numStr = to_string(12345);

```cpp
#include <iostream>
#include <string>
using namespace std;

int main()
{

    // 讀入一行文字
    string myInputStr;
    getline(cin, myInputStr); 
    // getline(cin, myInputStr, '#'); // # 為自訂義段字符
    cout << myInputStr << endl;

    // 宣告 string 物件
    string myStr = "Hello, World!";
    cout << myStr << endl;
    char myChar = myStr[0];
    cout << myChar << endl; // H

    // 擷取子字串
    string subStr1 = myStr.substr(0, 5);
    cout << subStr1 << endl; // Hello
    string subStr2 = myStr.substr(7);
    cout << subStr2 << endl; // World!

    // 尋找文字，未找到則會回傳 npos
    size_t strIndex = myStr.find("lo");
    if (strIndex != string::npos)
    {
        cout << strIndex << endl; // 3
    }

    // 插入文字
    string insertStr = myStr.insert(5, "ABC");
    cout << insertStr << endl; // HelloABC, World!

    // 替換文字
    string replaceStr = myStr.replace(2, 3, "CBA");
    cout << replaceStr << endl; // HeCBAABC, World!

    // 刪除文字
    string eraseStr = myStr.erase(2, 2);
    cout << eraseStr << endl; // HeAABC, World!

    // string to cstring
    const char *cStr = myStr.c_str();
    cout << cStr << endl; // HeAABC, World!

    // 字串轉數字
    string intStr = "123";
    int myInt = stoi(intStr);
    cout << myInt << endl; // 123
    string floatStr = "3.14";
    float myFloat = stof(floatStr);
    cout << myFloat << endl; // 3.14
    string doubleStr = "3.1415926";
    double myDouble = stod(doubleStr);
    cout << myDouble << endl; // 3.14159

    // 數字轉字串
    double myNum = 45678.123;
    string numStr = to_string(myNum);
    cout << numStr << endl; // 45678.123000

    return 0;
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

### friend

- 指定特定方法或物件，使其能夠訪問自己的私有參數與方法。

```cpp
class MyVector
{
private:
    int n;
    friend void test();
    friend class Test;
};

class Test
{
public:
    void test(MyVector v)
    {
        v.n = 200;
        cout << v.n << endl;
    }
};

void test()
{
    MyVector v;
    cout << v.n << endl;
}
```

### Static

- 訪問 static 變數或方法，使用 className::{variableName|metherName()} 

```cpp
#include <iostream>
using namespace std;


class Rabbit
{

private:
    static int count;

public:
    Rabbit() { Rabbit::count++; };
    static int publicVariable;
    static int getCount() { return Rabbit::count; };
};
// 初始化 Rabbit static variable
int Rabbit::count = 0;
int Rabbit::publicVariable = 0;

int main()
{

    //call static mether
    cout << Rabbit::getCount() << endl;
    // 0

    //class level variable
    Rabbit r1, r2, r3;
    cout << Rabbit::getCount() << endl;
    // 3

    // modify static variable
    Rabbit::publicVariable = 100;
    cout << Rabbit::publicVariable << endl;
    // 100

    return 0;
}
```

### Object Pointer

- 未用指針選告的 Object[] 都會預設調用了空參的 constructor 來建構 Object

- 動態的建立多個 Object 並使用不同 constructor

- Object 指針可使用 -> 來訪問物件參數
  
  - (*ptrMyVector)->value
  
  - this->value

```cpp
#include <iostream>
using namespace std;

class MyVector
{
public:
    int len;
    int *vector;
    MyVector()
    {
        this->len = 0;
        this->vector = nullptr;
    };
    MyVector(int len, int defaultValue = 0)
    {
        this->len = len;
        this->vector = new int[len];
        for (int i = 0; i < len; i++)
        {
            this->vector[i] = defaultValue;
        }
    }
    ~MyVector()
    {
        delete[] this->vector;
    }
};

int main()
{
    int len = 10;
    MyVector *ptrMyVectorList[len];
    for (int i = 0; i < len; i++)
    {
        ptrMyVectorList[i] = new MyVector(i + 1, i + 1);
    }

    for (int i = 0; i < len; i++)
    {
        MyVector *ptrMyVector = ptrMyVectorList[i];
        for (int j = 0; j < ptrMyVector->len; j++)
        {
            cout << ptrMyVector->vector[j] << ", ";
        }
        cout << endl;
    }

    for (int i = 0; i < len; i++)
    {
        delete[] ptrMyVectorList[i];
    }

    return 0;
}
```

### Object Copy

- 若沒有改寫 Copy 方法，C++ 會給一個預設的 Copy 方法為"淺層拷貝"

- 使用選告 MyVector(MyVector &myVector) 構造器來改寫 Copy 方法

```cpp
#include <iostream>
using namespace std;

/**
 * Object 若沒有寫 Copy 方法的話，則會給一個預設的 Copy 方法，但是只作用於淺層 Copy
 * 使用 MyVector(MyVector &myVector) 構造器改寫 Copy 方法
 */

class MyVector
{
public:
    int len;
    int *vector;
    MyVector(int len, int defaultValue = 0)
    {
        cout << "MyVector Constructor" << endl;
        this->len = len;
        this->vector = new int[len];
        for (int i = 0; i < len; i++)
        {
            this->vector[i] = defaultValue;
        }
    }
    ~MyVector()
    {
        cout << "MyVector Destructor" << endl;
        delete[] vector;
    }
    // 此為 MyVector Object 的 Copy constructor
    MyVector(const MyVector &myVector)
    {
        cout << "MyVector Copy" << endl;
        this->len = myVector.len;
        this->vector = new int[this->len];
        for (int i = 0; i < this->len; i++)
        {
            this->vector[i] = myVector.vector[i];
        }
    }
    void print()
    {
        cout << "[";
        for (int i = 0; i < this->len - 1; i++)
        {
            cout << this->vector[i] << ", ";
        }
        cout << "]\n";
    }
};

void func(MyVector v1)
{
    v1.vector[0] = 6;
    v1.print();
}

int main()
{
    MyVector myVector = MyVector(5, 0);
    myVector.print();
    myVector.vector[0] = 3;
    func(myVector);
    myVector.print();

    return 0;
}
```

### Constant

- 選告物件: const MyVector myVector = MyVector();
  
  - 被宣告為 const 的物件，值能夠調用自己的 const 方法

- 物件屬性: const int len;
  
  - const 附值必須在建構子方法名後使用 :len(len) 的方式附值

- 物件方法: void myMethod() const { .... }

```cpp
#include <iostream>
using namespace std;

class MyVector
{
public:
    const int len;
    double *vector;
    // 使用 :len() 來給 const 參數初始值
    MyVector() : len(0), vector(nullptr){};
    MyVector(int len, double defaultValue) : len(len)
    {
        this->vector = new double[len];
        for (int i = 0; i < len; i++)
        {
            this->vector[i] = defaultValue;
        }
    };
    ~MyVector() { delete[] vector; };
    // 選告 const 方法， const 物件只能夠調用 const 方法
    void print() const
    {
        cout << "[";
        for (int i = 0; i < this->len; i++)
        {
            cout << this->vector[i] << ", ";
        }
        cout << "]" << endl;
    };
    void test(){};
};

int main()
{
    // const 物件只能夠調用物件的 const 方法
    const MyVector myVector = MyVector(2, 0.0);
    myVector.print();
    // myVector.test(); // const 物件，不可調用非 const 方法
    return 0;
}
```

### Assignments

- 透過宣告 operator 方法來實現 assignments 功能
  
  - 改寫 operator=() 方法來使 myVector2 = myVector1 為深度拷貝
  
  - 實作 operator==() 方法來實現兩個物件的比較

```cpp
#include <iostream>
using namespace std;

class MyVector
{
public:
    int len;
    int *vector;
    MyVector(int len)
    {
        this->len = len;
        this->vector = new int[len];
        for (int i = 0; i < len; i++)
        {
            this->vector[i] = 0;
        }
    };
    MyVector &operator=(const MyVector &v)
    {
        cout << "call operator=" << endl;
        if (this != &v)
        {
            this->len = v.len;
            delete[] this->vector;
            this->vector = new int[v.len];
            for (int i = 0; i < v.len; i++)
            {
                this->vector[i] = v.vector[i];
            }
        }
        return *this;
    };
    bool operator==(const MyVector &v)
    {
        cout << "call isEqual" << endl;
        bool isEqual = true;
        if (this->len != v.len)
        {
            isEqual = false;
        }
        if (isEqual)
        {
            for (int i = 0; i < this->len; i++)
            {
                if (this->vector[i] != v.vector[i])
                {
                    isEqual = false;
                    break;
                }
            }
        }
        return isEqual;
    }
    void print() const
    {
        cout << "len: " << this->len << ", ";
        cout << "vector: [";
        for (int i = 0; i < this->len - 1; i++)
        {
            cout << this->vector[i] << ", ";
        }
        cout << this->vector[this->len - 1] << "]" << endl;
    }
};

int main()
{
    MyVector myVector1 = MyVector(10);
    MyVector myVector2 = MyVector(2);

    myVector1.print(); // len: 10, vector: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

    cout << (myVector1 == myVector2) << endl; // call isEqual
    // 0

    myVector2 = myVector1; // call operator=

    cout << (myVector1 == myVector2) << endl; // call isEqual
    // 1

    myVector2.len = 5;
    myVector2.vector[0] = 5;

    myVector1.print(); // len: 10, vector: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    myVector2.print(); // len: 5, vector: [5, 0, 0, 0, 0]
    return 0;
}
```

## Inheritance And Polymorphism

### inheritance 繼承

- 使用 : public 繼承 super class

- 在 sub class 的 constructor 中使用 : UperClassName() 調用 super class 的 constructor

- 在 sub class 的 Copy constructor 中，需要手動去呼叫 super class 的 Copy Constructor

```cpp
#include <iostream>
using namespace std;

class Animal
{
protected:
    string name;

public:
    Animal()
    {
        cout << "Animal no argument constructor" << endl;
        this->name = "Animal";
    };
    Animal(string name)
    {

        cout << "Animal single argument constructor" << endl;
        this->name = name;
    };
    Animal(const Animal &animal)
    {
        cout << "Animal Copy..." << endl;
        this->name = animal.name;
    }
    ~Animal()
    {
        cout << "Animal destructor" << endl;
    }
    void sleep()
    {
        cout << name << " do sleep..." << endl;
    };
    void print() const
    {
        cout << "Animal: " << name << endl;
    }
};

class Dog : public Animal
{
public:
    Dog()
    {
        cout << "Dog no argument constructor" << endl;
    };
    Dog(string name) : Animal(name)
    {
        cout << "Dog single argument constructor" << endl;
    };
    Dog(const Dog &dog) : Animal(dog)
    {
        cout << "Dog Copy..." << endl;
    };
    ~Dog()
    {
        cout << "Dog destructor" << endl;
    }
    void run()
    {
        cout << name << " do run..." << endl;
    }
    void print() { Animal::print(); };
    void print() const
    {
        cout << "Dog: ";
        Animal::print();
    };
};

void testFunc(Dog dog) {}

int main()
{
    // Dog dog1 = Dog();
    /*
    Animal no argument constructor
    Dog no argument constructor
    Dog destructor
    Animal destructor
    */

    // Dog dog2 = Dog("bemo");
    /*
    Animal single argument constructor
    Dog single argument constructor
    Dog destructor
    Animal destructor
    */

    // Dog dog3 = Dog("marley");
    // dog3.sleep();
    // dog3.run();
    /*
    Animal single argument constructor
    Dog single argument constructor
    marley do sleep...
    marley do run...
    Dog destructor
    Animal destructor
    */

    // Dog dog4 = Dog();
    // testFunc(dog4);
    /*
    Animal no argument constructor
    Dog no argument constructor
    Animal Copy...
    Dog Copy...
    Dog destructor
    Animal destructor
    Dog destructor
    Animal destructor
    */


    const Dog dog5 = Dog("dog5");
    Dog dog6 = Dog("dog6");
    dog5.print();
    dog6.print();
    /*
    Animal single argument constructor
    Dog single argument constructor
    Animal single argument constructor
    Dog single argument constructor
    Dog: Animal: dog5
    Animal: dog6
    Dog destructor
    Animal destructor
    Dog destructor
    Animal destructor
    */


    return 0;
}
```

### polymorphism 多形

- 父物件使用 virtual 關鍵字來修飾方法，在動態記憶體的作用下調用時，會優先調用子物件的 overwrite 過的方法

- 使用父物件指針陣列來存放多個子物件。
  
  - Parent *parentList[2];
  
  - parentList[0] = new Child(1, 2, 3);
  
  - parentList[1] = new Child(4, 5,

```cpp
#include <iostream>

using namespace std;

class Parent
{
protected:
    int x;
    int y;

public:
    Parent(int x, int y) : x(x), y(y){};
    // virtual 方法會延遲處理，若有被繼承的子物件改寫此方法，則會優先使用子物件的方法。
    virtual void print()
    {
        cout << "x:" << this->x << ", y:" << this->y;
    }
};

class Child : public Parent
{
protected:
    int z;

public:
    Child(int x, int y, int z) : Parent(x, y)
    {
        this->z = z;
    };
    void print()
    {
        Parent::print();
        cout << ", z:" << this->z;
    }
};

main()
{

    const int PARENT_LIST_LEN = 3;
    // 使用物件指標來實現 Polymorphism
    Parent *parentList[PARENT_LIST_LEN];
    parentList[0] = new Child(1, 2, 3);
    parentList[1] = new Child(4, 5, 6);
    parentList[2] = new Child(7, 8, 9);

    // 調用子物件所改寫的 print
    for (int i = 0; i < PARENT_LIST_LEN; i++)
    {
        parentList[i]->print();
        cout << endl;
    }

    // 釋放動態記憶體
    for (int i = 0; i < PARENT_LIST_LEN; i++)
    {
        delete parentList[i];
    }

    return 0;
}
```

### Generic 泛型

- 在 class 上方宣告 template <typename T> 來使用泛型
  
  - 在 class 外實作方法實也需要加上 template 宣告

- 在物件使用時才決定型態 Object<int>  object = Object(5); 

```cpp
#include <iostream>
#include <string>
using namespace std;

template <typename T>
class Parent
{
protected:
    T x;
    T y;

public:
    Parent(T x, T y) : x(x), y(y){};
    virtual void print()
    {
        cout << "x:" << this->x;
        cout << ", y:" << this->y;
    };
};

template <typename T>
class Child : public Parent<T>
{
protected:
    T z;

public:
    Child(T x, T y, T z) : Parent<T>(x, y)
    {
        this->z = z;
    };
    void print();
};

int main()
{

    Child<double> doubleChild(0.5, 8, 64);
    cout << "doubleChild: ";
    doubleChild.print();
    cout << endl;

    Child<string> stringChild = Child<string>("A", "B", "C");
    cout << "stringChild: ";
    stringChild.print();

    return 0;
}

template <typename T>
void Child<T>::print()
{
    Parent<T>::print();
    cout << ", z:" << this->z;
}
```

## Exception

### 繼承結構

- 導入 #incldue<stdexcept>包

- exception
  
  - logic_error
    
    - domain_error
    
    - invalid_argument
    
    - length_error
    
    - out_of_range
  
  - runtime_error
    
    - range_error
    
    - overflow_error
    
    - underflow_error

```cpp
#include <iostream>
#include <stdexcept>
#include <string>
using namespace std;

// 自定義 exception
class MyException : public logic_error
{
public:
    MyException(const string &msg) : logic_error(msg.c_str()){};
};

void fun(int array[], int len) 
{
    int b;
    cin >> b;
    if (b < 0 || b >= len)
    {
        // 使用自定義 exception
        // throw MyException("input index out of range.");

        throw logic_error("input index out of range.");
    }
    cout << "index: " << b << ", data: " << array[b];
}

int main()
{
    int arrayLen = 5;
    int array[arrayLen] = {1, 2, 3, 4, 5};

    try
    {
        fun(array, arrayLen);
    }
    catch (logic_error e)
    {
        cout << "catch error here!!!" << endl;
        // 錯誤訊息
        cout << e.what();
    }

    return 0;
}
```

## C++ 11 新特性 (New feature)

### 成員變數初始化 (Initialize member variables)

- 直接在宣告成員變數時給予初始值

```cpp
#include <iostream>
using namespace std;

class MyObject
{
public:
    int x = 1;
    int y = 2;
};

int main()
{
    MyObject obj = MyObject();
    cout << obj.x << endl;
    return 0;
}
```

### 枚舉類 (Enum class)

- 使用 enum class 來宣告枚舉類

```cpp
#include <iostream>
using namespace std;

enum class MyEnum
{
    One,
    Two,
    Three
};

int main()
{
    MyEnum myEnum = MyEnum::Two;
    return 0;
}
```

### 自動斷型 (Auto inference)

- 宣告 auto 類型讓編譯器自動做類型判斷

```cpp
#include <iostream>
#include <vector>
using namespace std;

// 自動推斷返回值 (老版本寫法)
// template <typename T, typename U>
// auto add2(T x, U y) -> decltype(x + y)
// {
//     return x + y;
// }

// 自動推斷返回值
template <typename T, typename U>
auto add2(T x, U y)
{
    return x + y;
}


int main()
{
    vector<pair<int, int>> numberList = {{1, 2}, {3, 4}, {5, 6}};
    /*
    cbegin：返回指向容器中第一個元素的 const_iterator。
    begin：返回指向序列中第一個元素的 iterator。
    cend：返回一個指向容器中尾後元素的 const_iterator。
    end：返回指向序列中尾後元素的 iterator。
    */

    // 原寫法
    cout << "general: " << endl;
    vector<pair<int, int>>::iterator it = numberList.begin();
    for (; it != numberList.cend(); it++)
    {
        cout << it->first << ", " << it->second << endl;
    }

    // 使用 auto 自動推斷類型
    cout << "auto inference: " << endl;
    for (auto it = numberList.begin(); it != numberList.cend(); it++)
    {
        cout << it->first << ", " << it->second << endl;
    }
    cout << endl;

    // 自動推斷返回值
    cout << add2(1, 0.3) << endl;
    cout << add2(1, 2) << endl;

    return 0;
}
```

### For迴圈 (For loops)

```cpp
#include <iostream>
#include <vector>
#include <map>
using namespace std;

int main()
{
    vector<int> nums = {1, 2, 3};
    for (int num : nums)
    {
        cout << num << ", ";
    }
    cout << endl;

    map<int, string> numMap = {{1, "one"}, {2, "two"}, {3, "three"}};
    for (auto [key, value] : numMap)
    {
        cout << key << ":" << value << endl;
    }

    return 0;
}
```

### 構造函數

- 使用 constexpr 修飾詞來選告，將常量計算提前至編譯階段完成

```cpp
#include <iostream>
using namespace std;

/**
 * 使用 constexpr 修飾詞，來將常量的計算值，提前至編譯階段計算，提升程式效率
 * 
*/
constexpr int pow(int x, int y)
{ // e.g. x^4 = 16
    int result = 1;
    while (y != 0)
    {
        result *= x;
        y--;
    }
    return result;
}

int main()
{
    int numberList[pow(2, 4)] = {pow(2, 2)};
    cout << sizeof(numberList) / sizeof(numberList[0]) << endl;
    cout << numberList[0] << endl;

    constexpr int x = 3 * 2 + 5;
    cout << x << endl;
    return 0;
}
```

### Unique 指針 (unique_ptr)

- 導入 #include <memory> 包

- 使用 make_unique<>(); 來宣告動態記憶體指針
  
  - 在{}作用域範圍消失後，動態分配的記憶體會自動釋放掉

```cpp
#include <iostream>
#include <memory>
using namespace std;

class MyObject
{
public:
    int x = 0;
    int y = 0;
    MyObject();
    MyObject(int x, int y) : x(x), y(y){};
    void print()
    {
        cout << "x:" << x << ", y:" << y << endl;
    }
};

int main()
{
    // 舊寫法，需要自己手動 delete 釋放記憶體空間
    MyObject *obj1 = new MyObject(1, 2);
    delete obj1;

    // 使用 unique_ptr 在{} 結束後會自動的釋放動態記憶體空間
    unique_ptr<MyObject> obj2 = make_unique<MyObject>(3, 4);
    obj2->print();

    // 使用 unique_ptr 宣告陣列需要手動初始化 
    int numListLen = 5;
    unique_ptr<int[]> numList = make_unique<int[]>(numListLen);
    for (int i = 0; i < numListLen; i++)
    {
        numList[i] = 0;
    }
     for (int i = 0; i < numListLen; i++)
    {
        cout << numList[i] << ", ";
    }
    return 0;
}
```

### Lambda 表達式

- 簡化匿名韓式使用發法
  
  - `[](int x){return x};`

```cpp
#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int main()
{
    vector<int> nums = {1, 2, 3, 4, 5};
    vector<int> evenNums;

    vector<int>::iterator evenNumIt = find_if(nums.begin(), nums.end(), [](int x)
                                              { return x % 2 == 0; });

    while (evenNumIt != nums.end())
    {
        evenNums.push_back(*evenNumIt);
        evenNumIt = find_if(evenNumIt+1, nums.end(), [](int x)
                            { return x % 2 == 0; });
    }

    for (int i : evenNums)
    {
        cout << i << ", ";
    }
    // 2, 4,

    return 0;
}
```

## Header File

- 當案結構
  
  - src
    
    - MyVector
      
      - MyVector.h
      
      - MyVector.cpp
    
    - Main.cpp

- 在 .h  中定義方法
  
  - 加上 #pragma 宣告，讓編譯器知道他是需要被群組編譯的

- 在 .cpp 中實現方法

### Main.cpp

```cpp
#include <iostream>
#include <string>
#include "MyVector/MyVector.cpp"
// #include "MyVector/MyVector.h" // using command line: g++ -o main.exe HeaderFile.cpp MyVector/MyVector.cpp
using namespace std;

int main()
{
    MyVector myVector = MyVector(5, 3);
    cout << "length: " << myVector.length << endl;

    cout << "myVector[]: " << myVector[0] << endl;

    cout << "myVector.toString: " << myVector.toString() << endl;

    return 0;
}
```

### MyVector.h

```cpp
#pragma
#include <string>
using namespace std;

class MyVector
{
public:
    const int length;
    double *vector;
    MyVector();
    MyVector(int length, double defaultValue = 0);
    MyVector(const MyVector &myVector);
    ~MyVector();
    double &operator[](int index);
    string toString();
};
```

### MyVector.cpp

```cpp
#include "MyVector.h"
#include <string>
using namespace std;

MyVector::MyVector() : length(0)
{
    this->vector = nullptr;
};

MyVector::MyVector(int length, double defaultValue) : length(length)
{
    this->vector = new double[length];
    for (int i = 0; i < length; i++)
    {
        this->vector[i] = defaultValue;
    }
}

MyVector::MyVector(const MyVector &myVector) : length(myVector.length)
{
    int len = myVector.length;
    this->vector = new double[len];
    for (int i = 0; i < len; i++)
    {
        this->vector[i] = myVector.vector[i];
    }
}

MyVector::~MyVector()
{
    delete[] this->vector;
}

double &MyVector::operator[](int index)
{
    return this->vector[index];
}

string MyVector::toString()
{
    string str = "MyVector:{";
    str += "length:" + to_string(this->length) + ", ";
    str += "vector:[";
    for (int i = 0; i < this->length - 1; i++)
    {
        str += to_string(this->vector[i]) + ", ";
    }
    str += to_string(this->vector[this->length - 1]) + "]";
    str += "}";
    return str;
}
```

## File I/O

### Write File

- 開啟文件 ofstream myFile = ofstream("temp.txt", ios::out);
  
  - ios:out 從頭開始寫，刪除全部舊資料，寫入新資料
  
  - ios::app 從尾開始寫，不能更改原存在資料
  
  - ios::ats 從尾開始寫，能更改原存在資料

- 執行寫入 myFile << "Wirte Text" << endl; 

- 關閉寫入流: myFile.close();

```cpp
#include <iostream>
#include <fstream>
#include <cstdlib>
using namespace std;

int main(){
    /*
    Write file options
    ios::out (default) The window starts at location(); remove existing data.
    ios::app The window starts at the end; never modify existing data.
    ios::ate The window starts at the end; can modify existing data.
    */
    ofstream myFile = ofstream("Basic/File_IO/temp.txt", ios::out);
    cout << static_cast<bool>(myFile) << endl;
    if(!myFile){
        exit(1);
    }
    myFile << "Hello, world" << endl;
    myFile << "Pi: " << 3.14 << endl;
    myFile.close();
    return 0;
}
```

### File Read

- 開啟文件 ifstream myFile = ifstream("score.txt");

- 執行讀取 myFile >> name >> score;

- 關閉讀取流: myFile.close();

- 檢查是否讀到了最尾 myFile.eof(); 讀到沒有資料了就會回傳 true

- 一次讀取一行，使用 string library getLine()

- 忽視讀寫頭指向的字元 myFile.ignore();

#### Example 1:

```cpp
#include <iostream>
#include <fstream>
#include <cstdlib>
using namespace std;

int main()
{
    ifstream myFile = ifstream("Basic/File_IO/score.txt");
    if (myFile)
    {
        string name = "";
        int score = 0;
        int totalScore = 0;
        int count = 0;
        while (myFile >> name >> score)
        {
            cout << name << " : " <<score << endl;
            totalScore += score;
            count++;
        }
        cout << "Average score: " << static_cast<double>(totalScore) / count << endl;
    }
    else
    {
        cout << "File not exist !!" << endl;
        exit(1);
    }
    myFile.close();

    return 0;
}
```

#### Example 2:

```cpp
#include <iostream>
#include <fstream>
#include <cstdlib>
#include <string>
using namespace std;

int main()
{
    ifstream myFile = ifstream("Basic/File_IO/score.txt");
    if (myFile)
    {
        while (!myFile.eof())
        {
            string lineStr;
            getline(myFile, lineStr);
            cout << lineStr << endl;
        }
    }
    else
    {
        cout << "File not exist !!" << endl;
        exit(1);
    }
    myFile.close();

    return 0;
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

- char *strncpy(char *dest, const char *source, size_t count); // 如果<mark>指定要複製的字串長度，小於要被複製的目標字串，複製後則"不會"</mark>在最後加上 '\0'********

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
