#include <iostream>
using namespace std;

/**
 * int argc: 參數數量
 * char *argv[]: 參數字串
 */
int main(int argc, char *argv[])
{
    for (int i = 0; i < argc; i++)
    {
        cout << "argument " << i + 1 << " : " << argv[i] << endl;
    }
    /*
    Input:
        {path/Program.exe} hello, world!
    
    Output:
        argument 1 : C:\Users\ChiaMing\Desktop\CppCode\Basic\ProgramArguments\ProgramArguments.exe
        argument 2 : hello
        argument 3 : world!
    */
    return 0;
}