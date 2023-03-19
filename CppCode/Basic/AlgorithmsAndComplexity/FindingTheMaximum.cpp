#include <iostream>
using namespace std;

double max(double array[], int len);

int main()
{
    double numArray[10] = {1, -2, 8, 99, 2.5, 7.5, 10.1, -5, 3, 4};
    double maxNum = max(numArray, sizeof(numArray) / sizeof(numArray[0]));
    cout << "maximum number is: " << maxNum << endl;
    return 0;
}

double max(double array[], int len)
{
    if (len == 1)
    {
        return array[0];
    }

    double subMax = max(array, len - 1);
    return (array[len - 1] > subMax) ? array[len - 1] : subMax;
}
