%{
#include <ctype>
#include <stdio.h>
int yylex(void);
int yywrap(void);
%}

%s male wielkie

%%

<INITIAL>^[a-z]	ECHO; BEGIN(male);
<male>[A-Z] putchar(tolower(*yytex));
<INITIAL>^[A-Z] ECHO; BEGIN(wielkie);
<wielkie>[a-z]	putchar(toupper(*yytext));
\n	ECHO; BEGIN(INITIAL);


%%
int yywrap(void) {  return 1; }
int main() { return yylex(); }
