#include <iostream>
#include <algorithm>

using namespace std;

int main()
{
    int R = 3; //z polecenia
    int P[] = {11,1,7,9,30,40,12,33,39,4,34,5};
    int n = sizeof(P) / sizeof(P[0]); //liczba elementow
    int pairs = 0; //liczba par

	cout << "n: " << n << endl;
    sort(P, P+n);

    for(int i = 0; i<n; i++)
	{
        cout << P[i] << " ";
    }

    cout << endl << endl;

    int j=0;

    for(int i=0;i<n;i++)
	{
        j=i+1;

        while( j<n && ((P[j]-P[i])<R) )
		{
            cout <<P[i] << "," << P[j] << endl;
            j++;
            pairs = pairs+1;
        }
    }
    cout << "Number of pairs: " << pairs;
    return 0;

}
