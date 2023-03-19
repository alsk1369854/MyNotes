#include <iostream>
using namespace std;

int testLocalVariable();
int testStaticVariable();

int main()
{
    // 使用 local variable 變數在離開 block 就會被銷毀
    for (int i = 0; i < 10; i++)
    {
        cout << testLocalVariable() << " ";
    }
    cout << endl;

    // 使用 static variable 變數會一直存在，不會被銷毀，直到程式結束
    for (int i = 0; i < 10; i++)
    {
        cout << testStaticVariable() << " ";
    }

    return 0;
}

int testLocalVariable()
{
    int count = 0;
    return ++count;
}

int testStaticVariable()
{
    static int count = 0;
    return ++count;
}