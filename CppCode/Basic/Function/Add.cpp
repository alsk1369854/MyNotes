#include <iostream>
using namespace std;

/**
 * Function 實作
 * 1. 宣告 Function header/prototype "int add(int, int)"
 * 2. 實作 Function body "int add(int num1, int num2){ ... return ...}"
*/

// 1. 宣告 Function
int add(int num1, int num2);

int main()
{
    int input1 = 0, input2 = 0;
    cout << "Input two numbers: ";
    cin >> input1 >> input2;
    
    int result = add(input1, input2);
    cout << "Two number total: " << result;

    return 0;
}

// 2. 實作 Function body
int add(int num1, int num2)
{
    return num1 + num2;
}