%{
#include <stdio.h>
int yylex(void);
int yywrap(void);
int chars = 0, words = 0, lines = 0;
%}

%%



[^ \n\t]+ { ++words; chars += yyleng; } 
\n { ++chars; ++lines; } 
. { ++chars; } 



%%
int yywrap(void) {  return 1; }
int main() {
	yyout = fopen("suma.txt", "wt"); 
	yylex(); 
	fprintf(yyout, "Suma znakow: %d, slow: %d, wierszy: %d", chars, words, lines);
return yylex();  
}
