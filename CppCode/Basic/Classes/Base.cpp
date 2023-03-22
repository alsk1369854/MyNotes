#include <iostream>
using namespace std;

// 子物件
class SubObject
{
public:
    // 建構子
    SubObject() { cout << "SubObject Constructor" << endl; };
    // 解構子(在物件銷毀時自動調用)
    ~SubObject() { cout << "~SubObject Destructor" << endl; };
};

// 主物件
class MyVector
{
public:
    // 建構子
    MyVector();
    MyVector(int len, int defaultValue = 0);
    // 解構子(在物件銷毀時自動調用)
    ~MyVector();
    // 物件方法
    void print();

private:
    // 物件屬性
    int len;
    int *vector;
    SubObject subObject;
};

int main()
{
    // 創建 MyVector 物件
    MyVector myVector(10, 5);
    // 調用 MyVector 方法
    myVector.print();
    /*
        SubObject Constructor
        MyVector Constructor
        Len: 10
        [5, 5, 5, 5, 5, 5, 5, 5, 5, 5]
        ~MyVector Destructor
        ~SubObject Destructor
    */
    return 0;
}

MyVector::MyVector()
{
    cout << "MyVector Constructor" << endl;
    len = 0;
    vector = nullptr;
}

MyVector::MyVector(int len, int defaultValue)
{
    cout << "MyVector Constructor" << endl;
    MyVector::len = len;
    vector = new int[len];
    for (int i = 0; i < len; i++)
    {
        vector[i] = defaultValue;
    }
}

MyVector::~MyVector()
{
    cout << "~MyVector Destructor" << endl;
    delete[] vector;
}

void MyVector::print()
{
    cout << "Len: " << len << endl;
    cout << "[";
    for (int i = 0; i < len - 1; i++)
    {
        cout << vector[i] << ", ";
    }
    cout << vector[len - 1] << "]" << endl;
}