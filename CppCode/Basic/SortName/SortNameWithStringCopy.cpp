#include <iostream>
#include <cstring>
using namespace std;

const int NAME_LIST_LEN = 5;
const int NAME_LEN = 10;

void strSwap(char *str1, char *str2);

int main()
{
    char nameList[NAME_LIST_LEN][NAME_LEN] = {
        {"Ming"},
        {"Han"},
        {"Mark"},
        {"JJ"},
        {"SNinjo"}};

    for (int i = 0; i < NAME_LIST_LEN; i++)
    {
        for (int j = 0; j < NAME_LIST_LEN - i - 1; j++)
        {
            if (strcmp(nameList[j], nameList[j + 1]) > 0)
            {
                strSwap(nameList[j], nameList[j + 1]);
            }
        }
    }

    for (int i = 0; i < NAME_LIST_LEN; i++)
    {
        cout << i <<  " : " << nameList[i] << endl;
    }
    /*
        0 : Han
        1 : JJ
        2 : Mark
        3 : Ming
        4 : SNinjo
    */
   
    return 0;
}

void strSwap(char *str1, char *str2)
{
    char temp[NAME_LEN];
    strcpy(temp, str1);
    strcpy(str1, str2);
    strcpy(str2, temp);
}
