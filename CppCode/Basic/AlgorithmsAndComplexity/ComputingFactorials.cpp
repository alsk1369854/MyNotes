#include <iostream>
using namespace std;

int factorials(int n);

int main()
{
    int n = 0;

    cout << "Input number computing factorials: ";
    cin >> n;
    cout << n << " factorials is : " << factorials(n) << endl;

    return 0;
}

int factorials(int n)
{
    if (n <= 1)
        return 1;

    return factorials(n - 1) * n;
}
