#include <iostream>
using namespace std;

const int MAX_LEN = 10000;

int findFibonacci(int n);

int main()
{
    int n = 0;

    cout << "Enter want to find fibonacci number index: ";
    cin >> n;
    for (int i = 0; i <= n; i++)
    {
        cout << findFibonacci(i) << ", ";
    }
    cout << endl;

    return 0;
}

int findFibonacci(int n)
{
    static int db[MAX_LEN] = {0, 1};

    if ((n == 0) || (db[n] != 0))
    {
        return db[n];
    }

    db[n] = findFibonacci(n - 2) + findFibonacci(n - 1);
    return db[n];
}