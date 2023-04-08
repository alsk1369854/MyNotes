#include <iostream>
#include <memory>
using namespace std;

class MyObject
{
public:
    int x = 0;
    int y = 0;
    MyObject();
    MyObject(int x, int y) : x(x), y(y){};
    void print()
    {
        cout << "x:" << x << ", y:" << y << endl;
    }
};

int main()
{
    // 舊寫法，需要自己手動 delete 釋放記憶體空間
    MyObject *obj1 = new MyObject(1, 2);
    delete obj1;

    // 使用 unique_ptr 在{} 結束後會自動的釋放動態記憶體空間
    unique_ptr<MyObject> obj2 = make_unique<MyObject>(3, 4);
    obj2->print();

    // 使用 unique_ptr 宣告陣列需要手動初始化 
    int numListLen = 5;
    unique_ptr<int[]> numList = make_unique<int[]>(numListLen);
    for (int i = 0; i < numListLen; i++)
    {
        numList[i] = 0;
    }
     for (int i = 0; i < numListLen; i++)
    {
        cout << numList[i] << ", ";
    }
    return 0;
}