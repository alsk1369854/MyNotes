#include <iostream>
using namespace std;

void insertionSort(int array[], int len, int cutOff = 0);
void printArray(const int array[], int len);

int main()
{
    int array[] = {2, 5, 7, 7, 3, 4, 6};
    int len = (sizeof(array) / sizeof(array[0]));

    cout << "unsorted array: ";
    printArray(array, len);
    cout << endl;

    insertionSort(array, len);

    cout << "sorted array: ";
    printArray(array, len);

    return 0;
}

void insertionSort(int array[], int len, int cutOff)
{
    if (len == cutOff)
    {
        return;
    }
    else
    {
        int temp = array[cutOff];

        for (int i = cutOff; i > 0; i--)
        {
            if (array[i - 1] > temp)
            {
                array[i] = array[i - 1];
            }
            else
            {
                array[i] = temp;
                break;
            }
        }

        insertionSort(array, len, cutOff + 1);
    }
}

void printArray(const int array[], int len)
{
    cout << "[";
    for (int i = 0; i < len; i++)
    {
        cout << array[i];
        if (i != len - 1)
        {
            cout << ", ";
        }
    }
    cout << "]";
}
