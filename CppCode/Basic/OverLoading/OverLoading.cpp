#include <iostream>
using namespace std;

// 函數多載宣告，同樣的方法名，不同的參數型態
void print(int intData);
int print(char charData);
void print(bool boolData);

int main()
{
    // 根據傳參決定要調用哪一個 function
    print(10);
    print('A');
    print(false);
    return 0;
}

void print(int intData)
{
    cout << "Input is integer type: " << intData << endl;
}
int print(char charData)
{
    cout << "Input is character type: " << charData << endl;
    return 0;
}
void print(bool boolData)
{
    cout << "Input is boolean type: " << boolData << endl;
}