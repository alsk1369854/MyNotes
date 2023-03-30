#include <iostream>
#include <vector>
#include <string>
using namespace std;

class MyObject
{
public:
    int v;
    MyObject(int v) : v(v){};
};

int main()
{
    // 一般 vector
    vector<int> intList;
    intList.push_back(1);
    intList.push_back(2);
    intList.push_back(3);

    intList.insert(intList.begin() + 1, 8);
    intList.erase(intList.begin() + 2);
    vector<int> tempVector;
    tempVector.push_back(10);
    tempVector.push_back(9);
    // tempVector.push_back(8);
    intList.swap(tempVector);
    // intList.clear();
    for (int i = 0; i < intList.size(); i++)
    {
        cout << intList[i] << ", ";
    }
    cout << endl;

    // Object vector
    vector<MyObject *> myObjectList;
    myObjectList.push_back(new MyObject(5));
    myObjectList.push_back(new MyObject(4));
    myObjectList.push_back(new MyObject(3));
    for (int i = 0; i < myObjectList.size(); i++)
    {
        cout << myObjectList[i]->v << ", ";
    }

    // 釋放指針 vector 記憶體
    while (!myObjectList.empty())
    {
        delete myObjectList.back();
        myObjectList.pop_back();
    }

    return 0;
}