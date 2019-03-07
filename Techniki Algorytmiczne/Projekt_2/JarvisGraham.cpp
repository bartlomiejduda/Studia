
#include <iostream>
#include <stack>
#include <stdlib.h>
#include<vector>

using namespace std;

struct Point
{
    int x;
    int y;
};

int orientation(Point p, Point q, Point r) //Graham & Jarvis
{
    int val = (q.y - p.y) * (r.x - q.x) -
              (q.x - p.x) * (r.y - q.y);

    if (val == 0) 
		return 0;
    
    int ret_val = (val > 0) ? 1: 2;
    //cout << "orientation ret_val: " << ret_val << endl;
	return ret_val;
}

void convexHullJarvis(Point points[], int n)
{
    if (n < 3) 
		return;

    vector<Point> hull;

    int l = 0;
    for (int i = 1; i < n; i++)
        if (points[i].x < points[l].x)
            l = i;

    int p = l, q;
    do
    {
        hull.push_back(points[p]);
        //cout << "hhhhh points.x=" << points[p].x << ", points.y=" << points[p].y << endl;
        //cout << "gggg points=" << points[p].x << ", " << points[p].y << endl;
        q = (p+1)%n;
        
        for (int i = 0; i < n; i++)
        {

           if (orientation(points[p], points[i], points[q]) == 2)
               q = i;
        }


        p = q;

    } while (p != l);

    for (int i = 0; i < hull.size(); i++)
        cout << "(" << hull[i].x << ", "
             << hull[i].y << ")\n";
}



//Graham
Point p0;

Point nextToTop(stack<Point> &S)
{
    Point p = S.top();
    S.pop();
    Point res = S.top();
    S.push(p);
    return res;
}

int swap(Point &p1, Point &p2)
{
    Point temp = p1;
    p1 = p2;
    p2 = temp;
}

int distSq(Point p1, Point p2)
{
    return (p1.x - p2.x)*(p1.x - p2.x) +
           (p1.y - p2.y)*(p1.y - p2.y);
}

int compare(const void *vp1, const void *vp2)
{
   Point *p1 = (Point *)vp1;
   Point *p2 = (Point *)vp2;

   int o = orientation(p0, *p1, *p2);
   
   if (o == 0)
     return (distSq(p0, *p2) >= distSq(p0, *p1))? -1 : 1;

   return (o == 2)? -1: 1;
}

void convexHullGraham(Point points[], int n)
{
   int ymin = points[0].y, min = 0;
   for (int i = 1; i < n; i++)
   {
     int y = points[i].y;

     if ( (y < ymin) || (ymin == y && points[i].x < points[min].x) )
        ymin = points[i].y, min = i;
   }

   swap(points[0], points[min]);
   p0 = points[0];
   qsort(&points[1], n-1, sizeof(Point), compare);

   int m = 1;
   for (int i=1; i<n; i++)
   {
       while (i < n-1 && orientation(p0, points[i], points[i+1]) == 0)
          i++;
       points[m] = points[i];
       m++;
   }

   if (m < 3) 
   	return;

   stack<Point> S;
   S.push(points[0]);
   S.push(points[1]);
   S.push(points[2]);

   for (int i = 3; i < m; i++)
   {

      while (orientation(nextToTop(S), S.top(), points[i]) != 2)
         S.pop();
      S.push(points[i]);
   }

   while (!S.empty())
   {
       Point p = S.top();
       cout << "(" << p.x << ", " << p.y <<")" << endl;
       S.pop();
   }
}

int main()
{
    Point points[] = {{0, 1}, {1, 2}, {3, 1}, {2, 0},
                      {3, 2}, {4, 1}, {4, 3}, {4, 0}, {2, 2}};
    int n = sizeof(points)/sizeof(points[0]);
    cout << "n: " << n << endl << endl;
    cout << "Graham:" << endl;
    convexHullGraham(points, n);
    cout << "---------" << endl;
    cout << "Jarvis:" << endl;
    convexHullJarvis(points, n);
    return 0;
}
