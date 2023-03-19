#include <iostream>
#include <cmath>
using namespace std;

int binarySearch(int target, int sortedArray[], int from, int to);

int main()
{
    int target = 5;

    // =================== 0  1  2  3  4  5  6   7
    int sortedNumbers[] = {1, 5, 6, 7, 8, 9, 10, 20};
    int targetIndex = binarySearch(target, sortedNumbers, 0, (sizeof(sortedNumbers) / sizeof(sortedNumbers[0]) - 1));
    
    cout << "target \""<< target << "\" array index is: " << targetIndex << endl;

    return 0;
}

int binarySearch(int target, int sortedArray[], int from, int to)
{
    int medianIndex = (from + to) / 2;
    int medianValue = sortedArray[medianIndex];
    if (medianValue == target)
    {
        return medianIndex;
    }
    else
    {
        if (medianValue > target)
        {
            return binarySearch(target, sortedArray, from, medianIndex);
        }
        else
        {
            return binarySearch(target, sortedArray, medianIndex + 1, to);
        }
    }
    return -1;
}