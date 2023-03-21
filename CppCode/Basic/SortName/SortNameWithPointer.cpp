#include <iostream>
#include <cstring>
using namespace std;

const int NAME_LIST_LEN = 5;
const int NAME_LEN = 10;

void strSwap(char *&str1, char *&str2);
// void strSwap2(char *str1, char *str2);

int main()
{
    char nameList[NAME_LIST_LEN][NAME_LEN] = {
        {"Ming"},
        {"Han"},
        {"Mark"},
        {"JJ"},
        {"SNinjo"}};

    char *nameListPtr[5] = {
        nameList[0],
        nameList[1],
        nameList[2],
        nameList[3],
        nameList[4]};

    for (int i = 0; i < NAME_LIST_LEN; i++)
    {
        for (int j = 0; j < NAME_LIST_LEN - i - 1; j++)
        {
            if (strcmp(nameListPtr[j], nameListPtr[j + 1]) > 0)
            {
                strSwap(nameListPtr[j], nameListPtr[j + 1]);
                // strSwap2(nameListPtr[j], nameListPtr[j + 1]);
            }
        }
    }

    for (int i = 0; i < NAME_LIST_LEN; i++)
    {
        cout << i << " : " << nameListPtr[i] << endl;
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

void strSwap(char *&str1, char *&str2)
{
    char *temp = str1;
    str1 = str2;
    str2 = temp;
}

// void strSwap2(char *str1, char *str2)
// {
//     char temp[NAME_LEN];
//     strcpy(temp, str1);
//     strcpy(str1, str2);
//     strcpy(str2, temp);
// }
