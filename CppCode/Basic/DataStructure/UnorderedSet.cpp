#include <iostream>
#include <unordered_set>
using namespace std;

int main() {
    // create unordered set
    unordered_set<int> myUnorderedSet{2, 4, 6, 8};
    
    // add value to unordered set
    myUnorderedSet.insert(4);

    // delete value 2 
    myUnorderedSet.erase(2);

    // print set values
    // 8 6 4
    for (const auto &s : myUnorderedSet) {
        cout << s << " ";
    }
    cout << "\n";

    return 0;
}