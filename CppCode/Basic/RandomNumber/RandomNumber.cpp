#include <iostream>
#include <ctime>
#include <cstdlib>
using namespace std;

int main()
{
    // newR = (943285761 * oldR + 18763571) mod 32767
    // 設定隨機亂數的參數為時間
    srand(time(0));
    for (int i = 0; i < 10; i++)
    {
        // 100 ~ 199 的隨機數
        int randNumber = (rand() % 100) + 100;
        cout << randNumber << endl;
    }
    return 0;
}