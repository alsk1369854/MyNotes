#include <iostream>
#include <cstring>
using namespace std;

/**
 * char *strcpy(char *dest, const char *source);
 * char *strncpy(char *dest, const char *source, size_t count);
 * 字串複製
 * 注意: 
 *      strcpy() 複製後"會"在最後加上 '\0'
 *      strncpy() 如果指定要複製的字串長度，小於要被複製的目標字串，複製後則"不會"在最後加上 '\0'
 *
 * char *dest: 複製後的字串要存放的位址
 * char *source: 要被複製的目標字串
 * size_t count: 指定要複製的字串長度
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