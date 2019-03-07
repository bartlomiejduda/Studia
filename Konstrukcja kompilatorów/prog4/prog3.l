%{
#include<stdio.h>
int yywrap(void);
int yylex(void);
#include "Lpars.h"
%}


%%

a  { return 'a'; }
b  { return 'b'; }
" " { return 'c'; } 
[^(a|b)]  ;

%%
     
int yywrap(void) {  return 1; }

