/* LLgen generated code from source gramatyka.g */
#include "Lpars.h"
#define LL_LEXI yylex
#define LLNOFIRSTS
#if __STDC__ || __cplusplus
#define LL_ANSI_C 1
#endif
#define LL_LEXI yylex
/* $Id: incl,v 2.13 1997/02/21 15:44:09 ceriel Exp $ */
#ifdef LL_DEBUG
#include <assert.h>
#include <stdio.h>
#define LL_assert(x)	assert(x)
#else
#define LL_assert(x)	/* nothing */
#endif

extern int LLsymb;

#define LL_SAFE(x)	/* Nothing */
#define LL_SSCANDONE(x)	if (LLsymb != x) LLsafeerror(x)
#define LL_SCANDONE(x)	if (LLsymb != x) LLerror(x)
#define LL_NOSCANDONE(x) LLscan(x)
#ifdef LL_FASTER
#define LLscan(x)	if ((LLsymb = LL_LEXI()) != x) LLerror(x)
#endif

extern unsigned int LLscnt[];
extern unsigned int LLtcnt[];
extern int LLcsymb;

#if LL_NON_CORR
extern int LLstartsymb;
#endif

#define LLsdecr(d)	{LL_assert(LLscnt[d] > 0); LLscnt[d]--;}
#define LLtdecr(d)	{LL_assert(LLtcnt[d] > 0); LLtcnt[d]--;}
#define LLsincr(d)	LLscnt[d]++
#define LLtincr(d)	LLtcnt[d]++

#if LL_ANSI_C
extern int LL_LEXI(void);
extern void LLread(void);
extern int LLskip(void);
extern int LLnext(int);
extern void LLerror(int);
extern void LLsafeerror(int);
extern void LLnewlevel(unsigned int *);
extern void LLoldlevel(unsigned int *);
#ifndef LL_FASTER
extern void LLscan(int);
#endif
#ifndef LLNOFIRSTS
extern int LLfirst(int, int);
#endif
#if LL_NON_CORR
extern void LLnc_recover(void);
#endif
#else /* not LL_ANSI_C */
extern LLread();
extern int LLskip();
extern int LLnext();
extern LLerror();
extern LLsafeerror();
extern LLnewlevel();
extern LLoldlevel();
#ifndef LL_FASTER
extern LLscan();
#endif
#ifndef LLNOFIRSTS
extern int LLfirst();
#endif
#if LL_NON_CORR
extern LLnc_recover();
#endif
#endif /* not LL_ANSI_C */
# line 1 "gramatyka.g"

#include<stdio.h>
#include<stdlib.h>

int token_val;
#if LL_ANSI_C
void LL0_S(void);
static void LL1_R(
# line 14 "gramatyka.g"
int sum) ;
#else
static LL1_R();
#endif
#if LL_ANSI_C
void
#endif
LL0_S(
#if LL_ANSI_C
void
#endif
) {
goto L_2; /* so that the label is used for certain */
L_2: ;
switch(LLcsymb) {
case /* '(' */ 4 : ;
LLtdecr(4);
LLsincr(0);
LL_SAFE('(');
LL_NOSCANDONE(num);
LL1_R(
# line 10 "gramatyka.g"
token_val);
break;
case /*  EOFILE  */ 0 : ;
goto L_3;
L_3: ;
LLtdecr(4);
break;
default: if (LLskip()) goto L_2;
goto L_3;
}
}
static
#if LL_ANSI_C
void
#endif
LL1_R(
#if LL_ANSI_C
# line 14 "gramatyka.g"
int sum)  
#else
# line 14 "gramatyka.g"
 sum) int sum;
#endif
{
LLread();
goto L_2; /* so that the label is used for certain */
L_2: ;
switch(LLcsymb) {
case /* ')' */ 5 : ;
goto L_3;
L_3: ;
LLsdecr(0);
LLtincr(4);
LL_SSCANDONE(')');
# line 14 "gramatyka.g"
{printf("suma = %d\n ", sum);}
LL_NOSCANDONE(nl);
LLread();
LL0_S();
break;
default: if (LLskip()) goto L_2;
goto L_3;
case /* ',' */ 6 : ;
LL_SAFE(',');
LL_NOSCANDONE(num);
LL1_R(
# line 15 "gramatyka.g"
sum + token_val);
break;
}
}

# line 17 "gramatyka.g"

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



