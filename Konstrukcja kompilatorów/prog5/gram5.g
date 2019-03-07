{
#include<stdio.h>
#include<stdlib.h>
int token_val;
}

%token slowo, nom, pieniadz, nl;
%start parse, S;

S :
  | slowo pieniadz nom nl S {printf("OK, wartosc kwoty: %d", token_val);}
  ;

{
int main(void){
printf("\n");
parse();
puts("\nOK\n")
printf("\n");
return 0;
}
LLmessage(int tk)
{
printf("blad (%d)", tk);
exit(1);
}
}
