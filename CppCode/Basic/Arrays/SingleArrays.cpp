#include <iostream>
using namespace std;

int main()
{
    /*
        1. C++ 不會自動幫忙初始化陣列的值
        2. C++ 指標越位不會有提示，需要自己小心(若讀取到其它應用程序的進程則會報 Run time error)
    */

    // Uninitialized Arrays
    cout << "@Uninitialized Arrays:" << endl;
    int array1[100];
    for (int i = 0; i < sizeof(array1) / sizeof(array1[0]); i++)
    {
        cout << array1[i] << " ";
        if (i % 10 == 9)
        {
            cout << endl;
        }
    }
    cout << endl;

    // Initialization Arrays
    cout << "@Initialization Arrays:" << endl;
    int array2[100] = {0};
    for (int i = 0; i < sizeof(array2) / sizeof(array2[0]); i++)
    {
        cout << array2[i] << " ";
        if (i % 10 == 9)
        {
            cout << endl;
        }
    }

    return 0;
}