{
  #include <stdio.h>
  #include <stdlib.h>
  int parse();
}

%start parse, S;

S : 
  | 'a' S { printf("1"); }
  | 'b' S { printf("2"); } 
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
