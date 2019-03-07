%{
#include<stdio.h>
#include<stdlib.h>
%}
%token liczba NL;
%%

W : C NL { printf("OK"); } 
  | W C NL { printf("OK"); }
  | error C NL { printf("OK"); } 

C : '(' L ')'
  ;

L : liczba
  | L ',' liczba
  ;


%%

int yyerror()
{
 printf("error\n");
 exit(-1);
}

int main(void)
{
 printf("\n");
 yyparse();
 return 0;
}


