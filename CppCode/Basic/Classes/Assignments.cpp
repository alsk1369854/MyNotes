#include <iostream>
using namespace std;

class MyVector
{
public:
    int len;
    int *vector;
    MyVector(int len)
    {
        this->len = len;
        this->vector = new int[len];
        for (int i = 0; i < len; i++)
        {
            this->vector[i] = 0;
        }
    };
    MyVector &operator=(const MyVector &v)
    {
        cout << "call operator=" << endl;
        if (this != &v)
        {
            this->len = v.len;
            delete[] this->vector;
            this->vector = new int[v.len];
            for (int i = 0; i < v.len; i++)
            {
                this->vector[i] = v.vector[i];
            }
        }
        return *this;
    };
    bool operator==(const MyVector &v)
    {
        cout << "call isEqual" << endl;
        bool isEqual = true;
        if (this->len != v.len)
        {
            isEqual = false;
        }
        if (isEqual)
        {
            for (int i = 0; i < this->len; i++)
            {
                if (this->vector[i] != v.vector[i])
                {
                    isEqual = false;
                    break;
                }
            }
        }
        return isEqual;
    }
    void print() const
    {
        cout << "len: " << this->len << ", ";
        cout << "vector: [";
        for (int i = 0; i < this->len - 1; i++)
        {
            cout << this->vector[i] << ", ";
        }
        cout << this->vector[this->len - 1] << "]" << endl;
    }
};

int main()
{
    MyVector myVector1 = MyVector(10);
    MyVector myVector2 = MyVector(2);

    myVector1.print(); // len: 10, vector: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

    cout << (myVector1 == myVector2) << endl; // call isEqual
    // 0

    myVector2 = myVector1; // call operator=

    cout << (myVector1 == myVector2) << endl; // call isEqual
    // 1
    
    myVector2.len = 5;
    myVector2.vector[0] = 5;

    myVector1.print(); // len: 10, vector: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    myVector2.print(); // len: 5, vector: [5, 0, 0, 0, 0]
    return 0;
}
