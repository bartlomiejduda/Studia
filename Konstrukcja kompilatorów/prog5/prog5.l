%{
#include<stdio.h>
int yywrap(void);
int yylex(void);
extern int token_val;
#include "Lpars.h"
%}

%%

kwota {return slowo;}
" "[1-9][0-9]*(\,[0-9][0-9])? {token_val=atof(yytext); return pieniadz;}
PLN {return nom;}
\n {return nl;}

%%
int yywrap(void) {return 1;}

