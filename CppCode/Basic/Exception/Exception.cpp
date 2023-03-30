#include <iostream>
#include <stdexcept>
#include <string>
using namespace std;

// 自定義 exception
class MyException : public logic_error
{
public:
    MyException(const string &msg) : logic_error(msg.c_str()){};
};

void fun(int array[], int len) 
{
    int b;
    cin >> b;
    if (b < 0 || b >= len)
    {
        // 使用自定義 exception
        // throw MyException("input index out of range.");
        
        throw logic_error("input index out of range.");
    }
    cout << "index: " << b << ", data: " << array[b];
}

int main()
{
    int arrayLen = 5;
    int array[arrayLen] = {1, 2, 3, 4, 5};

    try
    {
        fun(array, arrayLen);
    }
    catch (logic_error e)
    {
        cout << "catch error here!!!" << endl;
        // 錯誤訊息
        cout << e.what();
    }

    return 0;
}