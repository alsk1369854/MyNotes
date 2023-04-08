#include <iostream>
#include <vector>
#include <algorithm> // find libaray

using namespace std;

int main()
{
    vector<int> v = {2, 4, 6, 8, 10, 12, 14, 16, 18};

    // find 找定值
    vector<int>::iterator it1 = find(v.begin(), v.end(), 10); // find 10
    if (it1 != v.end())
        cout << "found " << *it1 << ", index: " << distance(v.begin(), it1) << "\n";
    else
        cout << "not find\n";

    // find_if 根據函式尋找
    vector<int>::iterator it2 = find_if(v.begin(), v.end(), [](int x)
                                        { return x == 12; }); // find 10
    if (it2 != v.end())
        cout << "found " << *it2 << ", index: " << distance(v.begin(), it2) << "\n";
    else
        cout << "not find\n";

    return 0;
}