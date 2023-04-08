#include <iostream>
#include <set>
using namespace std;

int main()
{
    // create set
    set<int> mySet = {3, 1};

    // add values to set
    mySet.insert(2);
    mySet.insert(5);
    mySet.insert(4);
    mySet.insert(5);
    mySet.insert(4);

    // delete value 2
    mySet.erase(2);

    // print set values
    // 1 3 4 5
    for (const int &s : mySet)
    {
        cout << s << " ";
    }
    cout << "\n";

    return 0;
}