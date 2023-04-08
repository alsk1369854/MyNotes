#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int main()
{
    vector<int> nums = {1, 2, 3, 4, 5};
    vector<int> evenNums;

    vector<int>::iterator evenNumIt = find_if(nums.begin(), nums.end(), [](int x)
                                              { return x % 2 == 0; });

    while (evenNumIt != nums.end())
    {
        evenNums.push_back(*evenNumIt);
        evenNumIt = find_if(evenNumIt+1, nums.end(), [](int x)
                            { return x % 2 == 0; });
    }

    for (int i : evenNums)
    {
        cout << i << ", ";
    }
    // 2, 4,

    return 0;
}