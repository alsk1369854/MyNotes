#include <iostream>
#include <string>
#include "MyVector/MyVector.cpp"
// #include "MyVector/MyVector.h" // using command line: g++ -o main.exe HeaderFile.cpp MyVector/MyVector.cpp
using namespace std;

int main()
{
    MyVector myVector = MyVector(5, 3);
    cout << "length: " << myVector.length << endl;

    cout << "myVector[]: " << myVector[0] << endl;

    cout << "myVector.toString: " << myVector.toString() << endl;

    return 0;
}