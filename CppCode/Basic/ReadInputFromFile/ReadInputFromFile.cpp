#include <iostream>
using namespace std;

/**
 * 使用命令介面輸入下方命令，讀取對應文件作為輸入參數。
 * $ ReadInputFromFile.exe < InputNumber.txt
*/
int main()
{
    int inputNum = 0;
    int sum = 0;

    /*
    cin 也有回傳值，當 InputNumber.txt 讀到最後沒有值時就會轉譯成 false
    while(cin >> inputNum) 條件隱含了三個步驟 
        1. istream& is = (cin >> inputNum);
        2. bool b = static_cast<bool>(is);
        3. while(b)
    */
    while(cin >> inputNum){
        sum += inputNum;
    }

    cout << "Sum number: " << sum << endl;
    cout << "program exit!!!" << endl;

    return 0;
}