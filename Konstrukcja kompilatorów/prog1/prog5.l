%{
#include <stdio.h>
int yylex(void);
int yywrap(void);
%}

%%


" "[Kk]ot/" " {printf(" KOTECZEK4 ");}
" "[Kk]ot" " {printf(" KOTECZEK1 ");}
^[Kk]ot" " {printf("KOTECZEK2 ");} 
" "[Kk]ot"\n" {printf(" KOTECZEK3");}




%%
int yywrap(void) {return 1;}
int main() {return yylex();}


