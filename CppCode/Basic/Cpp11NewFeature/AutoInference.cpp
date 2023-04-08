#include <iostream>
#include <vector>
using namespace std;

// 自動推斷返回值 (老版本寫法)
// template <typename T, typename U>
// auto add2(T x, U y) -> decltype(x + y)
// {
//     return x + y;
// }

// 自動推斷返回值
template <typename T, typename U>
auto add2(T x, U y)
{
    return x + y;
}


int main()
{
    vector<pair<int, int>> numberList = {{1, 2}, {3, 4}, {5, 6}};
    /*
    cbegin：返回指向容器中第一個元素的 const_iterator。
    begin：返回指向序列中第一個元素的 iterator。
    cend：返回一個指向容器中尾後元素的 const_iterator。
    end：返回指向序列中尾後元素的 iterator。
    */

    // 原寫法
    cout << "general: " << endl;
    vector<pair<int, int>>::iterator it = numberList.begin();
    for (; it != numberList.cend(); it++)
    {
        cout << it->first << ", " << it->second << endl;
    }

    // 使用 auto 自動推斷類型
    cout << "auto inference: " << endl;
    for (auto it = numberList.begin(); it != numberList.cend(); it++)
    {
        cout << it->first << ", " << it->second << endl;
    }
    cout << endl;

    // 自動推斷返回值
    cout << add2(1, 0.3) << endl;
    cout << add2(1, 2) << endl;

    return 0;
}