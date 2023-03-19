#include <iostream>
using namespace std;

int main()
{

    // 1. 宣告一個 num 變數，初始值為 10
    int num = 10;
    cout << "1. define a int value 'num'" << endl;
    cout << "(num) num value: " << num << endl;
    cout << "(&num) num address: " << &num << endl;
    cout << endl;

    // 2. 使用 <type> *{valuable name} 宣告一個指標，指向某個 int 的地址
    int *ptrNum = nullptr;

    // 3. 使用 '&' 取出 num 變數的地址，付給 ptrNum
    ptrNum = &num;
    cout << "3. declaring 'int *ptrNum' stores the address of 'num'" << endl;
    cout << "(ptrNum) ptrNum value: " << ptrNum << endl;
    cout << "(&ptrNum) ptrNum address: " << &ptrNum << endl;
    cout << "(*ptrNum) ptrNum reference: " << *ptrNum << endl;
    cout << endl;

    // 4. 對指標使用 '*' 取出 num 真實的值，此時做的修改變將會真實的修改到 num 變數
    *ptrNum = 5;
    cout << "4. modify '*ptrNum' to update 'num' value" << endl;
    cout << "(num) num value: " << num << endl;
    cout << "(*ptrNum) ptrNum reference: " << *ptrNum << endl;
    cout << endl;

    // 5. 使用 <type> &{valuable name} 宣告一個參照指標，指向某個 int 的值
    int &refNum = num;

    // 6. 對參照指標做出修改會直接影響 num 本身的值
    refNum = 80;
    cout << "6. declaring 'int &ptrNum' reference from 'num'" << endl;
    cout << "(num) num value: " << num << endl;
    cout << "(refNum) refNum value: " << refNum << endl;
    cout << endl;

    return 0;
}