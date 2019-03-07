{
  #include <stdio.h>
  #include <stdlib.h>
  int parse();
}

%start parse, S;

S : 'a' {printf("1"); } A 'b' B {printf("2");}
  ;

A :
  | 'a' A  {printf("1");}
  ;

B :
  | 'b' B {printf("2");}
  ;
  


{ 
   int main(){
    printf("\n");
    parse();
    printf("\n");
    return 0;
   }

void LLmessage(int tk)
{
  printf("\n blad numer (%d) \n", tk);
  exit(1);
}

}
