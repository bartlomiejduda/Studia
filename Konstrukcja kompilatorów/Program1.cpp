#include<iostream>
#include<conio.h>
#include<fstream>
#include<stdio.h>
#include<stdlib.h>

using namespace std;

int main ()
{
	ifstream fs;
	ofstream ft;
	char ch, source_path[100], dest_path[100];
	cout<<"Enter source file name: ";
	gets(source_path);
	
	fs.open(source_path);
	if(!fs)
	{
		cout<<"Error in opening source file..!!";
		getch();
		exit(1);
	}
	cout<<"Enter destination file name: ";
	gets(dest_path);
	ft.open(dest_path);
	if(!ft)
	{
		cout<<"Error in opening destination file!";
		fs.close();
		getch();
		exit(2);
	}
	while(fs.eof()==0)
	{
		fs>>ch;
		ft<<ch;
	}
	cout<<"File copied successfully!";
	fs.close();
	ft.close();
	getch();

  return 0;
}




