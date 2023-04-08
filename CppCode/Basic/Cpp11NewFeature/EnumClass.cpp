#include <iostream>
using namespace std;

enum class MyEnum
{
    One,
    Two,
    Three
};

int main()
{
    MyEnum myEnum = MyEnum::Two;
    return 0;
}