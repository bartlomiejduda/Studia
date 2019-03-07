#include <iostream>
using namespace std;

class Shared_Pointer
{
    int *value;
    mutable int *counter;

    public:
        Shared_Pointer();
        Shared_Pointer(int value);
        virtual ~Shared_Pointer();
        Shared_Pointer(Shared_Pointer& myPointer); //konst kopiujacy
        Shared_Pointer& operator=(Shared_Pointer& myPointer);
        Shared_Pointer & operator=(Shared_Pointer &&myPointer); //operator przypisania przenoszacy
        Shared_Pointer(Shared_Pointer &&myPointer); //konst przenoszacy

        int get_val();
        int get_counter();
        void setCounter(int counterFromReference);
        void increment_counter();
        void decrement_counter();
};

Shared_Pointer::Shared_Pointer()
{
	//cout << "sp0" << endl;
    this->value = new int(10);
    this->counter = new int(1);
}

Shared_Pointer::Shared_Pointer(int value)
{
	//cout << "sp1" << endl;
    this-> value = new int(value);
    this-> counter = new int(1);
}

Shared_Pointer::Shared_Pointer(Shared_Pointer& myPointer)
{
	//cout << "sp2" << endl;
    this -> value = myPointer.value;
    this -> counter = myPointer.counter;
    myPointer.increment_counter();
}

Shared_Pointer& Shared_Pointer::operator=(Shared_Pointer& myPointer)
{
	//cout << "sp3" << endl;
    decrement_counter();
    if(counter == 0)
	{
        delete value;
        delete counter;
    }
    this -> value = myPointer.value;
    this -> counter = myPointer.counter;
    myPointer.increment_counter();
    return *this;
}

Shared_Pointer::Shared_Pointer(Shared_Pointer &&myPointer)
{
	//cout << "sp4" << endl;
    /*if(counter == 0)
	{
        delete value;
        delete counter;
    }*/
    this -> value = myPointer.value;
    this -> counter = myPointer.counter;
    //myPointer.increment_counter();
    myPointer.value = NULL;
    //myPointer.counter = NULL;
}

Shared_Pointer& Shared_Pointer::operator=(Shared_Pointer &&myPointer)
{
	//cout << "sp5" << endl;
    decrement_counter();
    if(counter == 0)
	{
        delete value;
        delete counter;
    }
    this -> value = myPointer.value;
    this -> counter = myPointer.counter;
    //myPointer.increment_counter();
    return *this;
}


Shared_Pointer::~Shared_Pointer()
{
	cout << "Shared_Pointer destructor..." << endl;
    decrement_counter();
    if(counter == 0)
	{
        delete value;
        delete counter;
    }
    
}


int Shared_Pointer::get_val()
{
    return *this->value;
}

int Shared_Pointer::get_counter()
{
    return *this->counter;
}

void Shared_Pointer::increment_counter()
{
    *counter = *counter+1;
}

void Shared_Pointer::decrement_counter()
{
    *counter = *counter-1;
}

void Shared_Pointer::setCounter(int count_ref) //not used
{
    *counter = count_ref;
}

int main()
{
	
	cout << "## Shared Pointer Implementation ##" << endl;
	Shared_Pointer Shared_Pointer1(17);
    cout << "Shared_Pointer1 counter: " << Shared_Pointer1.get_counter() << endl;
    cout << "Shared_Pointer1 value: " << Shared_Pointer1.get_val() << endl;
    Shared_Pointer Shared_Pointer2(Shared_Pointer1);
    cout << "Shared_Pointer1 counter: " << Shared_Pointer1.get_counter() << endl;
    cout << "Shared_Pointer2 counter: " << Shared_Pointer1.get_counter() << endl;
    cout << "Shared_Pointer2 value: " << Shared_Pointer2.get_val() << endl;
    Shared_Pointer Shared_Pointer3(move(Shared_Pointer1)); //przy move jestesmy pewni, ze to r-wartosc
    cout << "Shared_Pointer1 counter: " << Shared_Pointer1.get_counter() << endl;
    cout << "Shared_Pointer2 counter: " << Shared_Pointer2.get_counter() << endl;
    cout << "Shared_Pointer3 counter: " << Shared_Pointer3.get_counter() << endl;
    cout << "Shared_Pointer3 value: " << Shared_Pointer3.get_val() << endl;
    Shared_Pointer Shared_Pointer4(3);
    cout << "Shared_Pointer4 counter: " << Shared_Pointer4.get_counter() << endl;
    Shared_Pointer2 = move(Shared_Pointer4);
    cout << "Shared_Pointer1 counter: " << Shared_Pointer1.get_counter() << endl;
    cout << "Shared_Pointer2 counter: " << Shared_Pointer2.get_counter() << endl;
    cout << "Shared_Pointer3 counter: " << Shared_Pointer3.get_counter() << endl;
    cout << "Shared_Pointer4 counter: " << Shared_Pointer4.get_counter() << endl;
    cout << "Shared_Pointer4 value: " << Shared_Pointer4.get_val() << endl;

	getchar();
    return 0;
}
