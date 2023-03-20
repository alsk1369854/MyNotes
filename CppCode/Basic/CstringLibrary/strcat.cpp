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