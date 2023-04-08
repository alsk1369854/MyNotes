#include <iostream>
#include <unordered_map>
#include <string>
using namespace std;

int main()
{
    // creat map
    unordered_map<string, int> myMap = {
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
    key: three, value: 3
    key: two, value: 2
    key: one, value: 1
    */
    for (auto [key, value] : myMap)
    {
        cout << "key: " << key << ", value: " << value << endl;
    }

    // clear map
    myMap.clear();
    return 0;
}