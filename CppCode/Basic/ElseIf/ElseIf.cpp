#include <iostream>
using namespace std;

int main(){
    int num = 0;

    cout << "Enter a number: ";
    cin >> num;

    if(num > 10){
        cout << "number > 10";
    }else if (num < 10){
        cout << "number < 10";
    }else{
        cout << "number == 10";
    }


    return 0;
}