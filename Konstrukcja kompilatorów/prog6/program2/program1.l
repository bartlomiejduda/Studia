%{
#include<stdio.h>
extern int token_val;
int yywrap(void);
#include "Lpars.h"
%}
%%
\( {return '(';}
\) {return ')';}
\, {return ',';}
\n {return nl;}
" " {return ' ';}
[0-9]+ {token_val=atoi(yytext); return num;}
%%
int yywrap(void){
return 1;
}
