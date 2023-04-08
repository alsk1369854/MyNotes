#include <iostream>
using namespace std;

class MyObject
{
public:
    int x = 1;
    int y = 2;
};

int main()
{
    MyObject obj = MyObject();
    cout << obj.x << endl;
    return 0;
}