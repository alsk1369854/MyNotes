#include <iostream>
#include <fstream>
#include <cstdlib>
#include <string>
using namespace std;

int main()
{
    ifstream myFile = ifstream("Basic/File_IO/score.txt");
    if (myFile)
    {
        while (!myFile.eof())
        {
            string lineStr;
            getline(myFile, lineStr);
            cout << lineStr << endl;
        }
    }
    else
    {
        cout << "File not exist !!" << endl;
        exit(1);
    }
    myFile.close();

    return 0;
}