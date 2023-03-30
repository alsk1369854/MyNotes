#include "MyVector.h"
#include <string>
using namespace std;

MyVector::MyVector() : length(0)
{
    this->vector = nullptr;
};

MyVector::MyVector(int length, double defaultValue) : length(length)
{
    this->vector = new double[length];
    for (int i = 0; i < length; i++)
    {
        this->vector[i] = defaultValue;
    }
}

MyVector::MyVector(const MyVector &myVector) : length(myVector.length)
{
    int len = myVector.length;
    this->vector = new double[len];
    for (int i = 0; i < len; i++)
    {
        this->vector[i] = myVector.vector[i];
    }
}

MyVector::~MyVector()
{
    delete[] this->vector;
}

double &MyVector::operator[](int index)
{
    return this->vector[index];
}

string MyVector::toString()
{
    string str = "MyVector:{";
    str += "length:" + to_string(this->length) + ", ";
    str += "vector:[";
    for (int i = 0; i < this->length - 1; i++)
    {
        str += to_string(this->vector[i]) + ", ";
    }
    str += to_string(this->vector[this->length - 1]) + "]";
    str += "}";
    return str;
}