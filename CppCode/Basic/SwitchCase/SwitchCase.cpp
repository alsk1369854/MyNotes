#include <iostream>
using namespace std;

// 判斷月分天數，一般2月會有28天，但在"閏年"2月會有29天
// 閏年條件 (year % 400 == 0) or ((year % 4) == 0) && (year % 100 != 0))
int main()
{
    int year = 0, month = 0;

    cout << "Input Year and Month: ";
    cin >> year >> month;

    int day = 0;
    bool doPrint = true;
    switch (month)
    {
    // 大月 31 天
    case 1:
    case 3:
    case 5:
    case 7:
    case 8:
    case 10:
    case 12:
        day = 31;
        break;
    // 小月 30 天
    case 4:
    case 6:
    case 9:
    case 11:
        day = 30;
        break;
    // 2 月檢查使否是閏年
    case 2:
        if ((year % 400 == 0) ||
            (year % 4 == 0) && (year % 100 != 0))
        {
            day = 29;
        }
        else
        {
            day = 28;
        }
    // 最後兜底的處理邏輯，以上 case 都未匹配成功，就會走到這一步。
    default:
        doPrint = false;
        cout << "This is the default block, " << month << " does not match any months." << endl;
    }

    if (doPrint)
    {
        cout << "The month day is: " << day;
    }

    return 0;
}