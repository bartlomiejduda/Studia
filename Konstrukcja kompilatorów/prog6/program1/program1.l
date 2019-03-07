%{
#include<stdio.h>
int yywrap(void);
int yylex(void);
#include "Lpars.h"
%}
%%
0 {return '0';}
1 {return '1';}
%%
int yywrap(void){
return 1;
}
