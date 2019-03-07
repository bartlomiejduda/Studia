#include <cstdio>
#include <iostream>
#include <fstream>
#include <cstring>

using namespace std;

int ASCII_to_int(char* ch) 
{
    return (int)ch[0] - 96;
}

char int_to_ASCII(int i)
{
	return (char)(i + 96);
}


int main()  
{
    int Num_of_states, Size_of_input_alphabet, Num_of_final_states, X, state, i, j;
    char* p;
    int Y, A;
    char AA;
    char c;
    int symbol;
    int const MAX_DAS_STATES = 10;
    int const MAX_ALPHABET_SIZE = 10;
    
    int transitions[MAX_DAS_STATES][MAX_ALPHABET_SIZE];
	bool finalStates[MAX_DAS_STATES];
	char input_string[100000];


	//string file_name = "DAS2.txt";
	//string file_name = "punkt_a.txt";
	string file_name = "punkt_b.txt";
	
    cout << "Reading from file: " << file_name << endl;
    ifstream fin(file_name);
    
    fin >> Num_of_states >> Size_of_input_alphabet >> Num_of_final_states;
    cout << "Final states: ";
    for(i=0; i<Num_of_final_states; i++)  
	{
        fin >> X;
        finalStates[X] = true;
        cout << "S" << X << " ";
    }
    cout << endl;
    
    
    memset(transitions, -1, MAX_DAS_STATES * MAX_ALPHABET_SIZE * sizeof(int));
    while(!fin.eof())   {
        fin >> X;
		fin.get(c); fin.get(AA); fin.get(c);
		fin >> Y;
        A = (int)AA - 96;
        transitions[X][A] = Y;
        //cout << "transitions[" << X << "][" << A << "] = " << Y << endl;
        cout << "S" << X << "--" << AA << "-->" << "S" << Y << endl;
    }
    fin.close();
    

    for(i=0; i<Num_of_states; i++)
        for(j=1; j<=Size_of_input_alphabet; j++)
            if(transitions[i][j] < 0 || transitions[i][j] >= Num_of_states) 
			{
                printf("DAS not defined properly.\n");
                return -1;
            }

    printf("Enter a string (Type 'q' to exit) : ");
    scanf("%[^\n]%*c", input_string);
    while(input_string[0] != 'q')   
	{
        state = 0;
        p = strtok(input_string, " ");
        while(p)    
		{
            symbol = ASCII_to_int(p);
            if(symbol <= 0 || symbol > Size_of_input_alphabet)   
			{
                printf("Invalid input symbol %c!\n", int_to_ASCII(symbol));
                return -1;
            } else 
			{
                state = transitions[state][symbol];
            }
            p = strtok(NULL, " ");
        }
        if(finalStates[state])
            printf("String ACCEPTED.\n");
        else
            printf("String REJECTED.\n");

        printf("Enter a string ('q' to exit) : ");
        scanf("%[^\n]%*c", input_string);
    }

    return 0;
}
