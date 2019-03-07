%{
#include <iostream>

using namespace std;

int yylex(void);
int yywrap(void);
%}

%%

^" "*  { cout << "test"; }


%%
int yywrap(void) {return 1;}
int main() {return yylex();}
