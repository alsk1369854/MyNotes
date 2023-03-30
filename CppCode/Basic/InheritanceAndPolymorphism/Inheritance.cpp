#include <iostream>
using namespace std;

class Animal
{
protected:
    string name;

public:
    Animal()
    {
        cout << "Animal no argument constructor" << endl;
        this->name = "Animal";
    };
    Animal(string name)
    {

        cout << "Animal single argument constructor" << endl;
        this->name = name;
    };
    Animal(const Animal &animal)
    {
        cout << "Animal Copy..." << endl;
        this->name = animal.name;
    }
    ~Animal()
    {
        cout << "Animal destructor" << endl;
    }
    void sleep()
    {
        cout << name << " do sleep..." << endl;
    };
    void print() const
    {
        cout << "Animal: " << name << endl;
    }
};

class Dog : public Animal
{
public:
    Dog()
    {
        cout << "Dog no argument constructor" << endl;
    };
    Dog(string name) : Animal(name)
    {
        cout << "Dog single argument constructor" << endl;
    };
    Dog(const Dog &dog) : Animal(dog)
    {
        cout << "Dog Copy..." << endl;
    };
    ~Dog()
    {
        cout << "Dog destructor" << endl;
    }
    void run()
    {
        cout << name << " do run..." << endl;
    }
    void print() { Animal::print(); };
    void print() const
    {
        cout << "Dog: ";
        Animal::print();
    };
};

void testFunc(Dog dog) {}

int main()
{
    // Dog dog1 = Dog();
    /*
    Animal no argument constructor
    Dog no argument constructor
    Dog destructor
    Animal destructor
    */

    // Dog dog2 = Dog("bemo");
    /*
    Animal single argument constructor
    Dog single argument constructor
    Dog destructor
    Animal destructor
    */

    // Dog dog3 = Dog("marley");
    // dog3.sleep();
    // dog3.run();
    /*
    Animal single argument constructor
    Dog single argument constructor
    marley do sleep...
    marley do run...
    Dog destructor
    Animal destructor
    */

    // Dog dog4 = Dog();
    // testFunc(dog4);
    /*
    Animal no argument constructor
    Dog no argument constructor
    Animal Copy...
    Dog Copy...
    Dog destructor
    Animal destructor
    Dog destructor
    Animal destructor
    */

   
    const Dog dog5 = Dog("dog5");
    Dog dog6 = Dog("dog6");
    dog5.print();
    dog6.print();
    /*
    Animal single argument constructor
    Dog single argument constructor
    Animal single argument constructor
    Dog single argument constructor
    Dog: Animal: dog5
    Animal: dog6
    Dog destructor
    Animal destructor
    Dog destructor
    Animal destructor
    */


    return 0;
}