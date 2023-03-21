#include <iostream>
#include <ctime>
using namespace std;

int main()
{

    clock_t startClock = clock();
    for (int i = 0; i < 999999999; i++)
    {
        ;
    }
    clock_t finishClock = clock();
    
    cout << "startClock: " << startClock << endl;
    cout << "finishClock: " << finishClock << endl;
    cout << "Program running seconds: " << static_cast<double>(finishClock - startClock) / CLOCKS_PER_SEC << endl;
    /*
    startClock: 0
    finishClock: 1430
    Program running seconds: 1.43
    */

    return 0;
}