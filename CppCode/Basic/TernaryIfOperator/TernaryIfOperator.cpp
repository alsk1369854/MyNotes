#include <iostream>
using namespace std;

int main()
{

    int num1 = 0, num2 = 0, num3 = 0;
    cout << "Enter three number : ";
    cin >> num1 >> num2 >> num3;

    int min = 0;
    if (num1 <= num2)
    {
        (num3 <= num1) ? min = num3 : min = num1;
    }
    else
    {
        min = (num2 <= num3) ? num2 : num3;
    }

    cout << "Minimum number is: " << min;
    return 0;
}