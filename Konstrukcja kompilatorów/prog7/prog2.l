%{
#include "y.tab.h";
#include<stdio.h>;
int yylex(void);
int yywrap(void);

%}
%%
[0-9]+ { return liczba; }
"\n" { return NL; }
\( { return '\('; }
\) { return '\)'; }
\, { return ','; } 
%%

int yywrap(void)
{
 return 1;
}
