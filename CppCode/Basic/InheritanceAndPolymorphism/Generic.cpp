#include <iostream>
#include <string>
using namespace std;

template <typename T>
class Parent
{
protected:
    T x;
    T y;

public:
    Parent(T x, T y) : x(x), y(y){};
    virtual void print()
    {
        cout << "x:" << this->x;
        cout << ", y:" << this->y;
    };
};

template <typename T>
class Child : public Parent<T>
{
protected:
    T z;

public:
    Child(T x, T y, T z) : Parent<T>(x, y)
    {
        this->z = z;
    };
    void print();
};

int main()
{

    Child<double> doubleChild(0.5, 8, 64);
    cout << "doubleChild: ";
    doubleChild.print();
    cout << endl;

    Child<string> stringChild = Child<string>("A", "B", "C");
    cout << "stringChild: ";
    stringChild.print();

    return 0;
}

template <typename T>
void Child<T>::print()
{
    Parent<T>::print();
    cout << ", z:" << this->z;
}