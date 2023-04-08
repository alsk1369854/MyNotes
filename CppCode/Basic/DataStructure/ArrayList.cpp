#include <iostream>
#include <vector>
using namespace std;

int main()
{
    // create Array List
    vector<int> arrayList = vector<int>();

    // add values
    for (int i = 0; i < 10; i++)
    {
        arrayList.push_back(i);
    }

    // print values
    // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
    for (int i = 0; i < arrayList.size(); i++)
    {
        int &value = arrayList.at(i);
        cout << value << ", ";
    }

    return 0;
}