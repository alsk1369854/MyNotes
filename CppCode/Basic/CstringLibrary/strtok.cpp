#include <iostream>
#include <cstring>
using namespace std;

const int INPUT_STRING_LEN = 1000;
const int WORD_LIST_LEN = 100;
const int WORD_LEN = 50;


/**
 * char *strtok(char *str, const char *delim);
 * 字串分割
 * 在給定一個字串後，若要繼續分割，就給 str 參數傳入 nullptr 這將會返回，下一個分割字串的記憶體位置。
 * 
 * char *str: 要分割的字串
 * const char *delim: 作為分割字符的字符集
 * 
 * return char*: 分割後的第一個字串記憶體地址
*/
int main()
{
    // www.chaiming.com/han\\JJ/mark.sninjo]
    char inputString[INPUT_STRING_LEN];
    cin >> inputString;
    char delim[] = "./\\";

    char wordList[WORD_LIST_LEN][WORD_LEN];

    int wordCount = 0;
    char *p = strtok(inputString, delim);
    while (p != nullptr)
    {
        strcpy(wordList[wordCount], p);
        wordCount++;
        p = strtok(nullptr, delim);
    }

    for (int i = 0; i < wordCount; i++)
    {
        cout << i << " : " << wordList[i] << endl;
    }
    /*
        0 : www
        1 : chaiming
        2 : com
        3 : han
        4 : JJ
        5 : mark
        6 : sninjo]
    */

    return 0;
}