#include <iostream>
#include <cmath>
#include <cstring>
using namespace std;

struct Vector
{
    int x;
    int y;
    double length()
    {
        return sqrt(x * x + y * y);
    };
    char *toString(char *str);
};

int main()
{
    Vector vector = {5, 3};
    cout << "vector.x: " << vector.x << endl;
    // vector.x: 5

    cout << "vector.y: " << vector.y << endl;
    // vector.y: 3

    cout << "vector.length(): " << vector.length() << endl;
    // vector.len(): 5.83095

    char vectorString[1000];
    vector.toString(vectorString);
    cout << vectorString;
    // Vector: {x: 5, y: 3}

    return 0;
}

char *Vector::toString(char *str)
{

    strcpy(str, "Vector: {");

    char strX[50];
    itoa(x, strX, 10);
    strcat(str, "x: ");
    strcat(str, strX);
    strcat(str, ", ");

    char strY[50];
    itoa(y, strY, 10);
    strcat(str, "y: ");
    strcat(str, strY);

    strcat(str, "}\n");

    return str;
}