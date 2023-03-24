#include <iostream>
#include <fstream>
#include <cstdlib>
using namespace std;

int main(){
    /*
    Write file options
    ios::out (default) The window starts at location(); remove existing data.
    ios::app The window starts at the end; never modify existing data.
    ios::ate The window starts at the end; can modify existing data.
    */
    ofstream myFile = ofstream("Basic/File_IO/temp.txt", ios::out);
    // myFile.open("Basic/File_IO/temp.txt", ios::out);
    cout << static_cast<bool>(myFile) << endl; // 1
    if(!myFile){
        exit(1);
    }
    myFile << "Hello, world" << endl;
    myFile << "Pi: " << 3.14 << endl;
    myFile.close();
    return 0;
}