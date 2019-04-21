

// Version  Name              Date			Comment
// 1.0      Bartlomiej Duda   21.04.2019	Initial version

#include <iostream>
#include <ctime>
#include <conio.h>
#include <algorithm>
using namespace std;


#define N_MINILOTEK 5
#define FIRST_MINILOTEK 1
#define LAST_MINILOTEK 42
#define ARR_MINILOTEK 5


#define N_LOTEK 6
#define FIRST_LOTEK 1
#define LAST_LOTEK 49
#define ARR_LOTEK 6

static bool first = true;

int random(int min, int max)
{

   if (first == true)
   {
      srand( time(NULL) );
      first = false;
   }
   return min + rand() % (( max + 1 ) - min);
}


void start_the_draw(int first_num, int last_num, int arr_size, int num_of_draws)
{
	
	int result;
	
	int *arr = new int [arr_size];
	
	for (int i=0; i < num_of_draws; i++)
	{
		while (1)  //loop for removing duplicates
		{
			result = random(first_num, last_num);	
			bool is_duplicate = false;
			for (int k = 0; k < arr_size; k++)
			{
				if (arr[k] == result)
					is_duplicate = true;
			}
			
			if (is_duplicate == false)
			{
				arr[i] = result;
				break;
			}
				
		}
	}
	
	sort(arr, arr+num_of_draws); //sorting results
	
	for (int i=0; i < num_of_draws; i++) // printing results
	{
		cout << arr[i];
		
		if (i != num_of_draws-1)
			cout << ", ";
	}
	
	
	
}


	

int main()
{
   int game_type, how_many_times;
   cout << "## LOTTO DRAW MACHINE ##" << endl;
   cout << "1) MINILOTEK" << endl;
   cout << "2) LOTEK" << endl;
   cout << "Choose game type: ";
   cin >> game_type;
   cout << "Choose how many times: ";
   cin >> how_many_times;
   
   if (game_type == 1)
   	{
   		for (int i=0; i < how_many_times; i++)
   		{
   			cout << i+1 << ") ";
   			start_the_draw(FIRST_MINILOTEK, LAST_MINILOTEK, ARR_MINILOTEK, N_MINILOTEK);
   			cout << endl;
		}
   		
	}
	else if (game_type == 2)
   	{
   		for (int i=0; i < how_many_times; i++)
   		{
   			cout << i+1 << ") ";
   			start_the_draw(FIRST_LOTEK, LAST_LOTEK, ARR_LOTEK, N_LOTEK);
   			cout << endl;
   		}
	}
	else
	{
		cout << "No game type chosen." << endl;
	}
	
	getch();
	return 0;
}










