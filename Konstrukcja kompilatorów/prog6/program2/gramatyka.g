{
#include<stdio.h>
#include<stdlib.h>

int token_val;
}
%start parse, S;
%token num,nl;

S  :  '('  num  R(token_val)
   |
   ;
	
R(int sum) : ')' {printf("suma = %d\n ", sum);} nl S
           | ',' num R(sum + token_val) 
           ;
{
main(){

parse();
}
//LLmessage(int tk){
//printf("Blad %d",tk);
//}
void LLmessage(int tk)
  {
    printf("LLmessage: ");
    switch(tk)
    {
      case -1 : if(isprint(LLsymb))printf("expected EOF not encountered, unexpected token (%c) found, skipping extra input\n", LLsymb);
                else printf("expected EOF not encountered, unexpected token (%d) found, skipping extra input\n", LLsymb);
                break;
      case 0  : if(isprint(LLsymb))printf("unexpected token (%c) deleted\n",LLsymb);
                else printf("unexpected token (%d) deleted\n",LLsymb);
                exit(-1);
      default : if(isprint(tk))printf("expected token (%c) not found, ", tk);
                else printf("expected token (%d) not found, ", tk);
                if(isprint(LLsymb))printf("token (%c) encountered\n", LLsymb);
                else printf("token (%d) encountered\n", LLsymb);
                exit(-1);
    }
  }


}
