#include <iostream>
using namespace std;

void swap(int &num1, int &num2);

int main()
{
    int a = 2, b = 5;
    cout << "a: " << a << ", b: " << b << endl;
    // output: a: 2, b: 5

    swap(a, b);
    cout << "a: " << a << ", b: " << b << endl;
    // output: a: 5, b: 2
    return 0;
}

void swap(int &num1, int &num2)
{
    int temp = num1;
    num1 = num2;
    num2 = temp;
}