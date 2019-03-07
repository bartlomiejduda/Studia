%{
#include <stdio.h>
int yylex(void);
int yywrap(void);
%}

%%

^" "*   ;


%%
int yywrap(void) {return 1;}
int main() {return yylex();}
