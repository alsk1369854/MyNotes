#include <iostream>
#include <cstdlib>
using namespace std;

/**
 * int atoi(const char *str);
 * 字串轉 int
 *
 * double atof(const char *str);
 * 字串轉 double
 */
int main()
{
    char intStr[10] = "-123";
    int intValue = atoi(intStr);
    cout << "intValue * 2: " << intValue * 2 << endl;
    // intValue * 2: -246

    char floatStr[10] = "3.14";
    double floatValue = atof(floatStr);
    cout << "floatValue / 2: " << floatValue / 2 << endl;
    // floatValue / 2: 1.57

    return 0;
}