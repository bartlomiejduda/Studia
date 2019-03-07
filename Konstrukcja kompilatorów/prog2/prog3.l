%{
#include <stdio.h>
int yylex(void);
int yywrap(void);
%}

%%

[a-zA-Z]+ { printf(" SLOWO "); }
[+-][1-9][0-9]*","[0-9]* { printf(" liczba3 dl: %d", yyleng-2); }
[+-][1-9][0-9]* { printf(" liczba2 dl: %d ", yyleng-1); }
[1-9][0-9]*  { printf(" liczba dl: %d ", yyleng); }
. ;

%%
int yywrap(void) {return 1;}
int main() {return yylex();}
