#include <iostream>
using namespace std;

const int MAX_LEN_ROW = 3;
const int MAX_LEN_COL = 3;

// array 作為參數傳遞，的二個維度開始必須寫死(C++ 語法規則)
void arrayToString(int array[][MAX_LEN_COL], int row, int col);

int main()
{
    int array[MAX_LEN_ROW][MAX_LEN_COL] =
        {{1, 2},
         {5, 4},
         {3, 7}};

    arrayToString(array, MAX_LEN_ROW, MAX_LEN_COL);
    /*
    output:
    [[1, 2, 0]
    [5, 4, 0]
    [3, 7, 0]]
    */

    return 0;
}

void arrayToString(int array[][MAX_LEN_COL], int row, int col)
{
    cout << "[";
    for (int i = 0; i < row; i++)
    {
        cout << "[";
        for (int j = 0; j < col; j++)
        {
            cout << array[i][j];
            if (j != col - 1)
            {
                cout << ", ";
            }
        }
        cout << "]";
        if (i != row - 1)
        {
            cout << "\n";
        }
    }
    cout << "]";
}
