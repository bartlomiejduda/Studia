{
  #include <stdio.h>
  #include <stdlib.h>
  int parse();
}

%start parse, spec;

spec : 'a'+ 'b'+ { printf("OK"); } 
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
