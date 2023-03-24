#include <iostream>
using namespace std;



class MyVector
{
private:
    int n;
    friend void test();
    friend class Test;
};

class Test
{
public:
    void test(MyVector v)
    {
        v.n = 200;
        cout << v.n << endl;
    }
};

void test()
{
    MyVector v;
    cout << v.n << endl;
}

int main()
{
    return 0;
}