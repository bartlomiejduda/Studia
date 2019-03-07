#include<iostream>
#include<conio.h>
#include<fstream>
#include<stdio.h>
#include<stdlib.h>
#include <string.h>

using namespace std;

int main ()
{
	ofstream ft;
	char dest_content[100], dest_path[100];
	cout<<"Enter content of dest file: ";
	gets(dest_content);
	
	
	for (int i = 0; i < strlen(dest_content); i++)
	{
		if (i == 7)
		{
			dest_content[i] = 'B';
		}
		
		if (i == strlen(dest_content) - 6)
		{
			dest_content[i] = 'X';
		}
		
	}
	
	
	cout << "Dest content: " << dest_content << endl;
		
	cout<<"Enter destination file name: ";
	gets(dest_path);
	ft.open(dest_path);
	if(!ft)
	{
		cout<<"Error in opening destination file!";
		getch();
		exit(2);
	}

	ft << dest_content;
	cout<<"File saved successfully!";
	ft.close();
	getch();

  return 0;
}




