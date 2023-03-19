#include <iostream>
using namespace std;

void printArray(const int array[], int len);

int main()
{
    int nums[5] = {1, 2, 3, 4, 5};
    int len = sizeof(nums) / sizeof(nums[0]);
    printArray(nums, len);
    return 0;
}

void printArray(const int array[], int len)
{
    cout << "[";
    for (int i = 0; i < len; i++)
    {
        cout << array[i];
        if (i < len - 1)
        {
            cout << ", ";
        }
    }
    cout << "]";
    return;
}