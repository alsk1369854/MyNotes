#include <iostream>
using namespace std;

const int MAX_LEN = 10000;

bool isPrime(int num);
void ruleOutPrime(int prime, bool isPrimeArray[], int isPrimeArrayLen);

int main()
{
    int num = 0;
    cout << "Enter the largest number to check for prime, max number: " << MAX_LEN << " : ";
    cin >> num;
    cout << endl;

    // 印出小於輸入數字的所有質數
    for (int i = 0; i <= num; i++)
    {
        if (isPrime(i))
        {
            cout << i << " is prime." << endl;
        }
    }

    return 0;
}

bool isPrime(int num)
{
    // 初始化設定
    static bool isFirstCall = true;
    static bool isPrimeArray[MAX_LEN] = {0};

    // initialized 首次進入初始化 isPrimeArray 陣列
    if (isFirstCall)
    {
        cout << "isPrime function init." << endl;
        for (int i = 2; i < MAX_LEN; i++)
        {
            isPrimeArray[i] = true;
        }
        isFirstCall = false;
    }

    // 遍歷所有小於 num 的數檢查是否是質數
    for (int i = 2; i <= num; i++)
    {
        // 若是質數，將質數的倍數給拿掉
        if (isPrimeArray[i] == true)
        {
            ruleOutPrime(i, isPrimeArray, num);
        }
    }

    return isPrimeArray[num];
}

void ruleOutPrime(int prime, bool isPrimeArray[], int isPrimeArrayLen)
{
    for (int i = 2; i * prime <= isPrimeArrayLen; i++)
    {
        isPrimeArray[i * prime] = false;
        // cout << "do out prime: " << i * prime << endl;
    }
}
