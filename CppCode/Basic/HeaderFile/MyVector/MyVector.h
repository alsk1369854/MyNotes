#pragma
#include <string>
using namespace std;

class MyVector
{
public:
    const int length;
    double *vector;
    MyVector();
    MyVector(int length, double defaultValue = 0);
    MyVector(const MyVector &myVector);
    ~MyVector();
    double &operator[](int index);
    string toString();
};