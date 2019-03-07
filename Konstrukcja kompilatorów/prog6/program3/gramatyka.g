{
#include<stdio.h>
#include<stdlib.h>
int LLlval;
}
%start parse, S;
%token num;

RL(int n) : ',' num {if(n==0)printf("%d",LLlval); if (n!=-1)n--; else n=-1;} RL(n)
          |
          ;

L(int n) : num {if(n==0)printf("[%d]",LLlval);} RL(n-1)
         ;


S {int n;} : num {n=LLlval;} ':'  L(n)  
           ;
{
main(){
printf("\n");
parse();
}
LLmessage(int tk){
	if (tk == -1) printf("oczekiwany koniec pliku \n");
	else if (tk == 0){
		if (LLsymb <256) printf("Nieoczekiwany token (%c) usuniety\n",LLsymb);
		else printf("Nieoczekiwany token o nuerze (%d) usuniêty \n",LLsymb);
		}
	else printf("Oczekiwano tokenu o numerze (%d)\n",tk);
	}
}
