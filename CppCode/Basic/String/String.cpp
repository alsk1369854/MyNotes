#include <iostream>
#include <string>
using namespace std;

int main()
{

    // 讀入一行文字
    string myInputStr;
    getline(cin, myInputStr); 
    // getline(cin, myInputStr, '#'); // # 為自訂義段字符
    cout << myInputStr << endl;

    // 宣告 string 物件
    string myStr = "Hello, World!";
    cout << myStr << endl;
    char myChar = myStr[0];
    cout << myChar << endl; // H

    // 擷取子字串
    string subStr1 = myStr.substr(0, 5);
    cout << subStr1 << endl; // Hello
    string subStr2 = myStr.substr(7);
    cout << subStr2 << endl; // World!

    // 尋找文字，未找到則會回傳 npos
    size_t strIndex = myStr.find("lo");
    if (strIndex != string::npos)
    {
        cout << strIndex << endl; // 3
    }

    // 插入文字
    string insertStr = myStr.insert(5, "ABC");
    cout << insertStr << endl; // HelloABC, World!

    // 替換文字
    string replaceStr = myStr.replace(2, 3, "CBA");
    cout << replaceStr << endl; // HeCBAABC, World!

    // 刪除文字
    string eraseStr = myStr.erase(2, 2);
    cout << eraseStr << endl; // HeAABC, World!

    // string to cstring
    const char *cStr = myStr.c_str();
    cout << cStr << endl; // HeAABC, World!

    // 字串轉數字
    string intStr = "123";
    int myInt = stoi(intStr);
    cout << myInt << endl; // 123
    string floatStr = "3.14";
    float myFloat = stof(floatStr);
    cout << myFloat << endl; // 3.14
    string doubleStr = "3.1415926";
    double myDouble = stod(doubleStr);
    cout << myDouble << endl; // 3.14159

    // 數字轉字串
    double myNum = 45678.123;
    string numStr = to_string(myNum);
    cout << numStr << endl; // 45678.123000

    return 0;
}