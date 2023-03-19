#include <iostream>
using namespace std;

int main()
{

    int grade1 = 0;
    int grade2 = 0;

    cout << "Input two grades: " << endl;
    cin >> grade1 >> grade2;

    // 使用 static_cast 將 int 相加解果轉換為 float，以利呈現平均計算精度
    float avgGrade = static_cast<float>(grade1 + grade2);
    avgGrade /= 2;

    cout << "Average grade: " << avgGrade << endl;

    return 0;
}