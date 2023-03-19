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