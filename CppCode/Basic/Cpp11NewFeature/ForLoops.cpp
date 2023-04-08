#include <iostream>
#include <vector>
#include <map>
using namespace std;

int main()
{
    vector<int> nums = {1, 2, 3};
    for (int num : nums)
    {
        cout << num << ", ";
    }
    cout << endl;

    map<int, string> numMap = {{1, "one"}, {2, "two"}, {3, "three"}};
    for (auto [key, value] : numMap)
    {
        cout << key << ":" << value << endl;
    }

    return 0;
}