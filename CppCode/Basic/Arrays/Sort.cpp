#include <iostream> 
#include <algorithm>  // sort libaray
#include <vector>

using namespace std;

bool myCompare(int a, int b)
{
    return a > b; // 降序排列
}

int main()
{
    int arr[] = {5, 4, 1, 7, 3, 8, 9, 10, 6, 2};
    vector<int> v(arr, arr + 10);

    // 升序 (default)
    cout << "ascending order: ";
    sort(v.begin(), v.begin() + 5);
    for (int i : v)
    {
        cout << i << " ";
    }
    cout << endl;

    // 降序
    cout << "descending order:";
    sort(v.begin(), v.end(), [](int x, int y)
         { return x > y; });
    for (int i : v)
    {
        cout << i << " ";
    }
    cout << endl;

    return 0;
}