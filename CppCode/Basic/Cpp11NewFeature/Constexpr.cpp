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