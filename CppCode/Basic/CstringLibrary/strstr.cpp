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
