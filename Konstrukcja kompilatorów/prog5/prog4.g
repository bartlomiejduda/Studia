{
#include<stdio.h>
#include<stdlib.h>
int parse();
void LLmessage(int tk);
int writeln_digit;
}

%start parse, S;

S : '0' {writeln_digit=0;} R
  | '1' {writeln_digit=1;} R
  ;

R : %if {writeln_digit==0} {puts("even");}
  |			   {puts("odd");}
  ;
{
main()
{
  parse();
}

void LLmessage(int tk)
{
 printf("LLmessage: ");
  switch(tk)
   {
