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