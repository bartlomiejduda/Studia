#include <chrono>
#include <iostream>
#include <ctime>
using namespace std;

int P[3][3] = 
{ 
	{3, 4, 6},
	{3, 5, 7},
	{3, 3, 9}
};

int R[3][3] = 
{ 
	{0, 0, 0},
	{0, 0, 0},
	{0, 0, 0}
};

int P_rand[100][100] = {0, 0, 0};
int R_rand[100][100] = {0, 0, 0};

int cost = 1;

 int cut_plate_top_down(int k, int l)
 {
        int q = P[k][l];
        int q2 = P[k][l];
        
        for(int i = 0; i < k; ++i)
		{
            q = max(q, cut_plate_top_down(i, l) + cut_plate_top_down(k-i-1, l) - cost);
        }
        
        for(int i = 0; i < l; ++i)
		{
            q2 = max(q2 , cut_plate_top_down(k, i) + cut_plate_top_down(k, l-i-1) - cost);
        }
        
        R[k][l] = max(q, q2);
        return max(q, q2);
}


int cut_plate_bottom_up(int k, int l)
{
        int q = 0;
        int q2 = 0;
        int profit = P[0][0] * (k * l) - ((k * l - 1) * cost); 
        for (int i = 0; i < k; i++)
        {
            for (int j = 0; j < l; j++)
            {
                q = P[i][j];
                //q2 = P[i][j];
                for (int x = 0; x < i; x++)
                {
                    q = max(q, P[x][j] + R[i-x][j] - cost);
                    //R[i][j] = q;
                }
                for (int y = 0; y < j; y++)
                {
                    q = max(q, P[i][y] + R[i][j-y] - cost);
                    //R[i][j] = q;
                }
                R[i][j] = q;
                //cout << "R[" << i << "][" << j << "] = " << q << endl;
            }
        }
        if(profit < q)
        {
            profit = q;
        }
        return profit;
}








int get_random(int min, int max) 
{
	srand(time(NULL)); 
	int random = rand() % max + min;
	return random;
}

void print()
{
	cout << endl << endl << "P array:" << endl;
	for (int i = 0; i < 3; i++)
	{
		for (int j = 0; j < 3; j++)
		{
			cout << P[i][j] << " ";
		}
		cout << endl;
	}
	
	cout << endl << endl << "R array:" << endl;
	for (int i = 0; i < 3; i++)
	{
		for (int j = 0; j < 3; j++)
		{
			cout << R[i][j] << " ";
		}
		cout << endl;
	}
	
}


void reset()
{
	for (int i = 0; i < 3; i++)
		for (int j = 0; j < 3; j++)
			R[i][j] = 0;
}



 int cut_plate_top_down_rand(int k, int l)
 {
        int q = P_rand[k][l];
        int q2 = P_rand[k][l];
        
        for(int i = 0; i < k; ++i)
		{
            q = max(q, cut_plate_top_down_rand(i, l) + cut_plate_top_down_rand(k-i-1, l) - cost);
        }
        
        for(int i = 0; i < l; ++i)
		{
            q2 = max(q2 , cut_plate_top_down_rand(k, i) + cut_plate_top_down_rand(k, l-i-1) - cost);
        }
        
        R_rand[k][l] = max(q, q2);
        return max(q, q2);
}

void fill_rand(int k, int l)
{
	for (int i = 0; i < k; i++)
		for (int j = 0; j < l; j++)
			P_rand[i][j] = get_random(1, 10);
}



int main ()
{
	int n = 3, m = 3, result = -1;



	result = cut_plate_top_down(m-1, n-1);
	cout << "Result top down: " << result << endl;
	print();
	reset();

	result = cut_plate_bottom_up(m, n);
	cout << "\n\n" << "Result bottom up: " << result << endl;
	reset();
    
    
    cout << "Enter n: ";
    cin >> n;
    cout << "Enter m: ";
    cin >> m;
    fill_rand(m, n);
    result = cut_plate_top_down_rand(m-1, n-1);
    cout << "Result top down rand: " << result << endl;
    
  
    getchar(); 
  	return 0;
}




