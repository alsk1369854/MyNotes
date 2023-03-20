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
    cout << "*p: " << *p << endl; // *p: i

    // index
    cout << "(p - str): " << (p - str) << endl; // (p - str): 2

    // 未找到則回傳 nullptr
    char *p2 = strchr(str, 'A');
    cout << "(p2 == nullptr): " << (p2 == nullptr) << endl; // (p2 == nullptr): 1

    return 0;
}