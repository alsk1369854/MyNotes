#include <iostream>
#include <map>
#include <string>
using namespace std;

int main()
{
    // creat map
    map<string, int> myMap = {
        {"one", 1}};

    // add map key value
    myMap["two"] = 2;
    myMap["three"] = 3;

    // get value by key
    // myMap["three"]: 3
    cout << "myMap[\"three\"]: " << myMap["three"] << endl;

    // check key exists
    // myMap.find("four"): 0
    cout << "myMap.find(\"four\"): " << (myMap.find("four") != myMap.end()) << endl;

    // foreach map
    /*
    key: one, value: 1
    key: three, value: 3
    key: two, value: 2
    */
    for (pair<string, int> entry : myMap)
    {
        cout << "key: " << entry.first << ", value: " << entry.second << endl;
    }

    // clear map
    myMap.clear();
    return 0;
}