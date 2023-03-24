#include <iostream>
using namespace std;

/**
 * Object 若沒有寫 Copy 方法的話，則會給一個預設的 Copy 方法，但是只作用於淺層 Copy
 * 使用 MyVector(MyVector &myVector) 構造器改寫 Copy 方法
 */

class MyVector
{
public:
    int len;
    int *vector;
    MyVector(int len, int defaultValue = 0)
    {
        cout << "MyVector Constructor" << endl;
        this->len = len;
        this->vector = new int[len];
        for (int i = 0; i < len; i++)
        {
            this->vector[i] = defaultValue;
        }
    }
    ~MyVector()
    {
        cout << "MyVector Destructor" << endl;
        delete[] vector;
    }
    // 此為 MyVector Object 的 Copy constructor
    MyVector(const MyVector &myVector)
    {
        cout << "MyVector Copy" << endl;
        this->len = myVector.len;
        this->vector = new int[this->len];
        for (int i = 0; i < this->len; i++)
        {
            this->vector[i] = myVector.vector[i];
        }
    }
    void print()
    {
        cout << "[";
        for (int i = 0; i < this->len - 1; i++)
        {
            cout << this->vector[i] << ", ";
        }
        cout << "]\n";
    }
};

void func(MyVector v1)
{
    v1.vector[0] = 6;
    v1.print();
}

int main()
{
    MyVector myVector = MyVector(5, 0);
    myVector.print();
    myVector.vector[0] = 3;
    func(myVector);
    myVector.print();

    return 0;
}