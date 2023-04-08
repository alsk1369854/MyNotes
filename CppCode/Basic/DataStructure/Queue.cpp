#include <iostream>
#include <queue>
using namespace std;

int main()
{
    // create queue
    queue<int> qu;

    // add values
    for (int i = 0; i < 10; i++)
    {
        qu.push(i);
    }

    // print values
    // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
    while (!qu.empty())
    {
        int &value = qu.front(); // 讀最前項
        cout << value << ", ";
        qu.pop(); // 吐出最前項
    }
    cout << endl;
    cout << "qu.size(): " << qu.size();

    return 0;
}