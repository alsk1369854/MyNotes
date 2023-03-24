#include <iostream>
using namespace std;

class MyVector
{
public:
    int len;
    int *vector;
    MyVector()
    {
        this->len = 0;
        this->vector = nullptr;
    };
    MyVector(int len, int defaultValue = 0)
    {
        this->len = len;
        this->vector = new int[len];
        for (int i = 0; i < len; i++)
        {
            this->vector[i] = defaultValue;
        }
    }
    ~MyVector()
    {
        delete[] this->vector;
    }
};

int main()
{
    int len = 10;
    MyVector *ptrMyVectorList[len];
    for (int i = 0; i < len; i++)
    {
        ptrMyVectorList[i] = new MyVector(i + 1, i + 1);
    }

    for (int i = 0; i < len; i++)
    {
        MyVector *ptrMyVector = ptrMyVectorList[i];
        for (int j = 0; j < ptrMyVector->len; j++)
        {
            cout << ptrMyVector->vector[j] << ", ";
        }
        cout << endl;
    }

    for (int i = 0; i < len; i++)
    {
        delete[] ptrMyVectorList[i];
    }

    return 0;
}