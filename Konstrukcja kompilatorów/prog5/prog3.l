%{
#include<stdio.h>
int yywrap(void);
int yylex(void);
#include "Lpars.h"
%}

%%

a { return 'a'; }
b { return 'b'; }
c { return 'c'; }
(bb)*bc 
