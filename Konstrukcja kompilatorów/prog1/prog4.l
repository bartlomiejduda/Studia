%{
#include <stdio.h>
int yylex(void);
int yywrap(void);
%}

%%

" "[Kk]ot" " {printf(" KOTECZEK ");}
^[Kk]ot" " {printf("KOTECZEK ");} 
" "[Kk]ot"\n" {printf(" KOTECZEK");}




%%
int yywrap(void) {return 1;}
int main() {return yylex();}


