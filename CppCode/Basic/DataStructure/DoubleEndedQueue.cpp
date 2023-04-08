#include <iostream>
#include <deque>

using namespace std;

int main() {
    deque<int> d = {1, 2, 3, 4};  // [1, 2, 3, 4]

    d.push_back(5); // [1, 2, 3, 4, 5]
    d.pop_front(); // [2, 3, 4, 5]
    d.push_front(0); // [0, 2, 3, 4, 5]
    d.pop_back(); // [0, 2, 3, 4]

    // 印出 deque 內所有內容, c++11 才支援
    // 0 2 3 4
    for (int &i : d) {
        cout << i << " ";
    }
    cout << "\n";

    // 0 2 3
    cout << d[0] << " " << d[1] << " " << d[2] << "\n";

    return 0;
}