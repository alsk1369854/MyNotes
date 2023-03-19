#include <iostream>
using namespace std;

void hanoi(char from, char via, char to, int disc);

int main()
{
    char a = 'A', b = 'B', c = 'C';
    int discs = 0;
    cout << "Enter discs: ";
    cin >> discs;
    hanoi(a, b, c, discs);
    return 0;
}

void hanoi(char from, char via, char to, int disc)
{
    if (disc == 1)
    {
        cout << "From " << from << " to " << to << endl;
    }
    else
    {
        hanoi(from, to, via, disc - 1);
        cout << "From " << from << " to " << to << endl;
        hanoi(via, from, to, disc - 1);
    }
}