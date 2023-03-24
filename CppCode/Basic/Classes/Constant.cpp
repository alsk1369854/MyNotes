#include <iostream>
using namespace std;

class MyVector
{
public:
    const int len;
    double *vector;
    // 使用 :len() 來給 const 參數初始值
    MyVector() : len(0), vector(nullptr){};
    MyVector(int len, double defaultValue) : len(len)
    {
        this->vector = new double[len];
        for (int i = 0; i < len; i++)
        {
            this->vector[i] = defaultValue;
        }
    };
    ~MyVector() { delete[] vector; };
    // 選告 const 方法， const 物件只能夠調用 const 方法
    void print() const
    {
        cout << "[";
        for (int i = 0; i < this->len; i++)
        {
            cout << this->vector[i] << ", ";
        }
        cout << "]" << endl;
    };
    void test(){};
};

int main()
{
    // const 物件只能夠調用物件的 const 方法
    const MyVector myVector = MyVector(2, 0.0);
    myVector.print();
    // myVector.test(); // const 物件，不可調用非 const 方法
    return 0;
}