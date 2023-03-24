#include <iostream>
using namespace std;


class Rabbit
{

private:
    static int count;

public:
    Rabbit() { Rabbit::count++; };
    static int publicVariable;
    static int getCount() { return Rabbit::count; };
};
// 初始化 Rabbit static variable
int Rabbit::count = 0;
int Rabbit::publicVariable = 0;

int main()
{

    //call static mether
    cout << Rabbit::getCount() << endl;
    // 0

    //class level variable
    Rabbit r1, r2, r3;
    cout << Rabbit::getCount() << endl;
    // 3

    // modify static variable
    Rabbit::publicVariable = 100;
    cout << Rabbit::publicVariable << endl;
    // 100
    
    return 0;
}