#include <iostream>
#include <stack>
using namespace std;

int main(){
    // create stack
    stack<int> myStack;

    // push values to stack
    for(int i=0; i<10; i++){
        myStack.push(i);
    }

    // pop stack values
    // 10, 8, 7, 6, 5, 4, 3, 2, 1, 0,
    while(!myStack.empty()){
        int &value = myStack.top();
        cout << value << ", ";
        myStack.pop();
    }
    return 0;
}