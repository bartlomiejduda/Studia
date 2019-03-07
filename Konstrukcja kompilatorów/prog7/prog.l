%{
#include<stdio.h>
int yywrap(void);
int yylex(void);

%}
%%
a {return 'a';}
b {return 'b';}
c {return 'c';}
d {return 'd';}
%%
int yywrap(void){
return 1;
}
