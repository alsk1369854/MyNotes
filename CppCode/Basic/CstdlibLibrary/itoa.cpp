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