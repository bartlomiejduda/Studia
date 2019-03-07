%{
#include<stdio.h>
int yywrap(void);
int yylex(void);
#include "Lpars.h"
extern int LLlval;
%}
%%
\, {return ',';}
\: {return ':';}
[0-9]+ {LLlval = atoi(yytext); return(num);}
" " { ; }
%%
int yywrap(void){
return 1;
}
