%{
#include <stdio.h>
int yylex(void);
int yywrap(void);
float suma = 0;
%}

%%


-?[0-9]+(\.[0-9]+)? { suma += atof(yytext); }
. | \n



%%
int yywrap(void) { printf("suma = %.4f", suma); return 1; }
int main() {return yylex();}
