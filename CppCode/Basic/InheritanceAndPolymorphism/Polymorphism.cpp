#include <iostream>

using namespace std;

class Parent
{
protected:
    int x;
    int y;

public:
    Parent(int x, int y) : x(x), y(y){};
    // virtual 方法會延遲處理，若有被繼承的子物件改寫此方法，則會優先使用子物件的方法。
    virtual void print()
    {
        cout << "x:" << this->x << ", y:" << this->y;
    }
};

class Child : public Parent
{
protected:
    int z;

public:
    Child(int x, int y, int z) : Parent(x, y)
    {
        this->z = z;
    };
    void print()
    {
        Parent::print();
        cout << ", z:" << this->z;
    }
};

main()
{

    const int PARENT_LIST_LEN = 3;
    // 使用物件指標來實現 Polymorphism
    Parent *parentList[PARENT_LIST_LEN];
    parentList[0] = new Child(1, 2, 3);
    parentList[1] = new Child(4, 5, 6);
    parentList[2] = new Child(7, 8, 9);

    // 調用子物件所改寫的 print
    for (int i = 0; i < PARENT_LIST_LEN; i++)
    {
        parentList[i]->print();
        cout << endl;
    }

    // 釋放動態記憶體
    for (int i = 0; i < PARENT_LIST_LEN; i++)
    {
        delete parentList[i];
    }

    return 0;
}