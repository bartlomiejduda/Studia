/* LLgen generated code from source gram3.g */
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
# line 1 "gram3.g"

  #include <stdio.h>
  #include <stdlib.h>
  int parse();
#if LL_ANSI_C
static void LL1_A(void);
static void LL2_B(void);
#else
static LL1_A();
static LL2_B();
#endif
#if LL_ANSI_C
void
#endif
LL0_S(
#if LL_ANSI_C
void
#endif
) {
LLtincr(2);
LLtincr(3);
LLtincr(3);
LL_SCANDONE('a');
# line 9 "gram3.g"
{printf("1"); }
LL1_A();
LLtdecr(3);
LL_SCANDONE('b');
LL2_B();
# line 9 "gram3.g"
{printf("2");}
}
static
#if LL_ANSI_C
void
#endif
LL1_A(
#if LL_ANSI_C
void
#endif
) {
LLread();
goto L_2; /* so that the label is used for certain */
L_2: ;
switch(LLcsymb) {
case /* 'b' */ 3 : ;
goto L_3;
L_3: ;
LLtdecr(2);
break;
default: if (LLskip()) goto L_2;
goto L_3;
case /* 'a' */ 2 : ;
LL_SAFE('a');
LL1_A();
# line 13 "gram3.g"
{printf("1");}
break;
}
}
static
#if LL_ANSI_C
void
#endif
LL2_B(
#if LL_ANSI_C
void
#endif
) {
LLread();
goto L_2; /* so that the label is used for certain */
L_2: ;
switch(LLcsymb) {
case /*  EOFILE  */ 0 : ;
goto L_3;
L_3: ;
LLtdecr(3);
break;
default: if (LLskip()) goto L_2;
goto L_3;
case /* 'b' */ 3 : ;
LL_SAFE('b');
LL2_B();
# line 17 "gram3.g"
{printf("2");}
break;
}
}

# line 22 "gram3.g"
 
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


