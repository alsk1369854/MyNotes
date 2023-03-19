#include <iostream>
#include <climits>
using namespace std;

/*
    | Category           | Type           | Bytes |
    | ------------------ | -------------- | ----- |
    | Integer            | bool           | 1     |
    | Integer            | char           | 1     |
    | Integer            | int            | 4     |
    | Integer            | short          | 2     |
    | Integer            | long           | 4     |
    | Integer            | unsigned int   | 4     |
    | Integer            | unsigned short | 2     |
    | Integer            | unsigned long  | 4     |
    | Fractional numbers | float          | 4     |
    | Fractional numbers | double         | 8     |
*/
int main()
{
    // int 最大最小值
    cout << "max int: " << INT_MAX << "\n";
    cout << "min int: " << INT_MIN << "\n";
    cout << "\n";

    // int 與 char 轉換
    int num = 65;
    char ascii = num;
    cout << "num: " << num << "\n";
    cout << "ascii: " << ascii << "\n";

    // 常量宣告
    const int MAX_LEVEL = 5;

    return 0;
}