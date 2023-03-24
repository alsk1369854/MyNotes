#include <iostream>
#include <fstream>
#include <cstdlib>
using namespace std;

int main()
{
    ifstream myFile = ifstream("Basic/File_IO/score.txt");
    if (myFile)
    {
        string name = "";
        int score = 0;
        int totalScore = 0;
        int count = 0;
        while (myFile >> name >> score)
        {
            cout << name << " : " <<score << endl;
            totalScore += score;
            count++;
        }
        cout << "Average score: " << static_cast<double>(totalScore) / count << endl;
    }
    else
    {
        cout << "File not exist !!" << endl;
        exit(1);
    }
    myFile.close();

    return 0;
}