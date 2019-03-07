%{
#include <stdio.h>
#include <stdlib.h>
%}

X : S F {puts("OK");}
  | S F error { yyerror(); }
  ;

S : 'a' S 'b'
  | 'a' 'b'
  ;

F : 'c' F 'd'
  |
  ;


%%


int yyerror()
{ 
  printf("ERROR\n");
  exit(-1);
}

int main(void)
{
  printf("\n");
  yyparse();
  return 0;
}


