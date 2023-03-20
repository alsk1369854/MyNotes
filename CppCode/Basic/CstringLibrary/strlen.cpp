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