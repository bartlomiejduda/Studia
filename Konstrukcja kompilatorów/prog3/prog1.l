%{
#include <stdio.h>
int yylex(void);
int yywrap(void);
%}

%%


^[ \t]*[1-9][0-9]*[1-46-9][ \t]*/[\n]  printf(" %s (-)", yytext);
^[ \t]*[1-9][0-9]*[0-5][ \t]*/[\n] printf(" %s (+)", yytext);
^[ \t]*[0-5][ \t]*/[\n] printf(" %s (+)", yytext);
^[ \t]*[1-46-9][ \t]*/[\n] printf(" %s (-)", yytext); 

%%
int yywrap(void) {  return 1; }
int main() { return yylex(); }
