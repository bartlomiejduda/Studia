{
#include<stdio.h>
#include<stdlib.h>
}
%start parse, S;

S {int len;} : L(&len) {printf("\nDlugosc ciagu wynosi [%d] \n",len);}
              ;
	
L(int *length) : '0' L(length) { (*length)++; }
               | '1' L(length) { (*length)++; }
	       |               {*length=0;    }
	       ;
{
main(){
printf("\n");
parse();
}
LLmessage(int tk){
printf("Blad %d",tk);
}
}
