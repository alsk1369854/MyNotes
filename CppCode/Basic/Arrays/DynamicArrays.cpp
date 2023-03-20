#include <iostream>
using namespace std;

void arrayToString(int *array, int len);

int main()
{
    int len = 0;
    cout << "Enter a number to initialization array length: ";
    cin >> len;

    // 1. 動態宣告 array
    int *array = new int[len];

    // 2. 需要自己手動初始化
    for (int i = 0; i < len; i++)
    {
        array[i] = 0;
    }
    arrayToString(array, len);
    cout << endl;

    // 3. 使用結束要使用 delete 關鍵字釋放記憶體
    delete[] array;
    array = nullptr; // 指針直接加上 nullptr 以避免，之後誤用 reference 取值

    // tip: sizeof 計算長度會錯誤，因為 array 是 8 bit 的指針記憶體大小，array[0] 則是 int 大小。
    int size = sizeof(array) / sizeof(array[0]);
    cout << "Array size is: " << size; // out put: 2

    return 0;
}

void arrayToString(int *array, int len)
{
    cout << "[";
    for (int i = 0; i < len; i++)
    {
        cout << array[i];
        if (i != len - 1)
        {
            cout << ", ";
        }
    }
    cout << "]";
}
