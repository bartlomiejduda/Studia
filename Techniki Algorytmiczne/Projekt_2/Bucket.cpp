#include <iostream>
#include <vector>
#include <map>
#include <math.h>

using namespace std;

int main()
{
   map<int, vector<int>> M;
   int R = 3;
   int P[] = {11,1,7,9,30,40,12,33,39,4,34,5};
   int n = sizeof(P) / sizeof(P[0]);
   int pairs=0;

    for(int i=0;i<n;i++)
	{
        cout << "Number " <<P[i] << " is in bucket " << floor(P[i]/R) << endl;
        M[floor(P[i]/R)].push_back(i);
    }

    for(auto &a:M )
	{
        if(a.second.size()>1)
		{
        	//cout<<"Kubelek "<<a.first<<" zawiera: "<<endl;
            /*for (vector<int>::iterator it = a.second.begin(); it != a.second.end(); ++it)
			{

                //cout <<P[*it]<<" "<<endl;
            }*/
            //cout<<"ilosc par: "<<(a.second.size()*(a.second.size()-1))/2<<endl;
            pairs=pairs+((a.second.size()*(a.second.size()-1))/2);
            //cout << "pairs: " << pairs << endl;
        }
    }


    for(auto &a:M)
	{
        map<int, vector<int>>::iterator it1 = M.upper_bound(a.first);

        if(it1 != M.end())
		{
            for (vector<int>::iterator it = a.second.begin() ; it !=  a.second.end(); ++it)
			{
                for (vector<int>::iterator it2 = it1->second.begin() ; it2 !=  it1->second.end(); ++it2)
				{
                    if((abs(P[*it]-P[*it2])<R) && (abs(P[*it]-P[*it2])>0))
					{
                        //cout<<P[*it]<<" , "<<P[*it2]<<endl;
                        pairs++;
                    }
                }
            }
        }
    }
    cout << "Number of pairs:" << pairs << endl << endl;
    /*
        for(int i=0;i<n;i++)
	{
        cout << "Number " <<P[i] << " is in bucket " << floor(P[i]/R) << endl;
        M[floor(P[i]/R)].push_back(i);
    }*/
    

}
