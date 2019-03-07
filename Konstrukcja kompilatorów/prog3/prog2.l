%{
#include <stdio.h>
int yylex(void);
int yywrap(void);
%}

%s gstring

%%

<gstring>\"	BEGIN 0;
<gstring>.	;
\"		BEGIN gstring;



%%
int yywrap(void) {  return 1; }
int main() { return yylex(); }
